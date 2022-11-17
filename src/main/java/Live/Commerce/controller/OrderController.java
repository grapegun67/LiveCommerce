package Live.Commerce.controller;

import Live.Commerce.domain.Item;
import Live.Commerce.domain.Member;
import Live.Commerce.domain.Order;
import Live.Commerce.service.ItemService;
import Live.Commerce.service.MemberService;
import Live.Commerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;

    @GetMapping("/order")
    public String create(Model model) {
        List<Member> members = memberService.findAll();
        List<Item> items = itemService.itemFindAll();

        model.addAttribute("members", members);
        model.addAttribute("items", items);

        return "order/orderForm";
    }

    @PostMapping("/order")
    public String createOrder(@RequestParam("memberId") Long memberId,
                              @RequestParam("itemId") Long itemId,
                              @RequestParam("count") int count) {

        Order order = orderService.createOrder(memberId, itemId, count);
        orderService.saveOrder(order);

        return "redirect:/";
    }

    @GetMapping("/orders")
    public String orderList(Model model) {
        List<Order> orders = orderService.findOrderAll();
        model.addAttribute("orders", orders);

        return "order/orderList";
    }

}