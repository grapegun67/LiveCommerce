package Live.Commerce.service;

import Live.Commerce.domain.Item;
import Live.Commerce.domain.Member;
import Live.Commerce.domain.Order;
import Live.Commerce.domain.OrderItem;
import Live.Commerce.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
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

        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(item.getPrice());
        orderItem.setCount(count);

        // 삭제가 이렇게 만들어도 되려나.. 도메인 주도 개발을 적용해야하는데 이건 좀 보완이 필요함
        itemService.itemCountRemove(itemId);

        List<OrderItem> orderItems = new ArrayList<>();
        orderItems.add(orderItem);

        Order order = new Order();
        order.setMember(member);
        order.setOrderItems(orderItems);

        return order;
    }


}
