package me.kzv.ecommerce.dto;

import lombok.Data;
import me.kzv.ecommerce.entity.ItemImg;
import org.modelmapper.ModelMapper;

@Data
public class ItemImgDto {
    private Long id;
    private String imgName;
    private String imgUrl;
    private String repImgYn;

    public static ItemImgDto of(ItemImg itemImg, ModelMapper modelMapper) {
        return modelMapper.map(itemImg, ItemImgDto.class);
    }
}
