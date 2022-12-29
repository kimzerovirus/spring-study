package me.kzv.ecommerce.dto;

import lombok.Data;
import me.kzv.ecommerce.constant.ItemSellStatus;

@Data
public class ItemSearchDto {
    private String searchDateType;
    private ItemSellStatus searchSellStatus;
    private String searchBy;
    private String searchQuery = "";
}
