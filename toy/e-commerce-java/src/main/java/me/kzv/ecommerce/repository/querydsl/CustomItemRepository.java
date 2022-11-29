package me.kzv.ecommerce.repository.querydsl;

import me.kzv.ecommerce.dto.ItemSearchDto;
import me.kzv.ecommerce.dto.MainItemDto;
import me.kzv.ecommerce.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomItemRepository {
    Page<Item> getAdminItemPage(ItemSearchDto dto, Pageable pageable);

    Page<MainItemDto> getMainItemPage(ItemSearchDto dto, Pageable pageable);
}
