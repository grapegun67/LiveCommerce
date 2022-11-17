package Live.Commerce.service;

import Live.Commerce.domain.Item;
import Live.Commerce.domain.Member;
import Live.Commerce.domain.Order;
import Live.Commerce.domain.OrderItem;
import Live.Commerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final MemberService memberService;
    private final ItemService itemService;
    private final OrderRepository orderRepository;

    @Transactional
    public void saveOrder(Order order) {
        orderRepository.save(order);
    }

    public Order findOrder(Long id) {
        return orderRepository.findOne(id);
    }

    public List<Order> findOrderAll() {
        return orderRepository.findAll();
    }

    public Order createOrder(Long memberId, Long itemId, int count) {
        Member member = memberService.findOne(memberId);
        Item item = itemService.itemFindOne(itemId);

        Order order = new Order();
        order.setMember(member);

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(item.getPrice());
        orderItem.setCount(count);

        // 삭제가 이렇게 만들어도 되려나.. 도메인 주도 개발을 적용해야하는데 이건 좀 보완이 필요함
        itemService.itemCountRemove(itemId);

        // 매핑 양쪽에 값을 다 넣고 persist해줘야하는구나 이것도 이 위치에 소스를 넣는 것은 별로다
        orderItem.setOrder(order);
        order.getOrderItems().add(orderItem);

        log.info("debug2: price {}", order.getOrderItems().get(0).getOrderPrice());

        return order;
    }

}