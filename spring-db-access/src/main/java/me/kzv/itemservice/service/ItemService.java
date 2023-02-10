package me.kzv.itemservice.service;

import me.kzv.itemservice.domain.Item;
import me.kzv.itemservice.repository.ItemSearchCond;
import me.kzv.itemservice.repository.ItemUpdateDto;

import java.util.List;
import java.util.Optional;

public interface ItemService {

    Item save(Item item);

    void update(Long itemId, ItemUpdateDto updateParam);

    Optional<Item> findById(Long id);

    List<Item> findItems(ItemSearchCond itemSearch);
}
