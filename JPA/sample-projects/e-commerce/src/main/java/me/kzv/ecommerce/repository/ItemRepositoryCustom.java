package me.kzv.ecommerce.repository;

import me.kzv.ecommerce.dto.ItemSearchDto;
import me.kzv.ecommerce.dto.MainItemDto;
import me.kzv.ecommerce.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {
    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
