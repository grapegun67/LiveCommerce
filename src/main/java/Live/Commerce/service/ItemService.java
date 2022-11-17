package Live.Commerce.service;

import Live.Commerce.domain.Item;
import Live.Commerce.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void itemSave(Item item) {
        itemRepository.save(item);
    }

    public Item itemFindOne(Long id){
        return itemRepository.findOne(id);
    }

    public List<Item> itemFindAll() {
        return itemRepository.findAll();
    }

    public void itemCountRemove(Item item, int count) {
        item.setQuantity(item.getQuantity() - count);
    }
}