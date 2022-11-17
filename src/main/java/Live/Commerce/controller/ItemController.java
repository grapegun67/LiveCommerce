package Live.Commerce.controller;

import Live.Commerce.domain.Item;
import Live.Commerce.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/items/new")
    public String newBody(Model model) {
        model.addAttribute("itemForm", new ItemForm());
        return "items/createItemForm";
    }

    @PostMapping("/items/new")
    public String sendBody(ItemForm itemForm) {
        Item item = new Item();
        item.setName(itemForm.getName());
        item.setPrice(itemForm.getPrice());
        item.setQuantity(itemForm.getQuantity());

        itemService.itemSave(item);
        return "redirect:/";
    }

    @GetMapping("/items")
    public String itemList(Model model) {
        List<Item> itemList = itemService.itemFindAll();
        model.addAttribute("items", itemList);

        return "items/itemList";
    }

}