package me.kzv.ecommerce.repository;

import me.kzv.ecommerce.entity.Item;
import me.kzv.ecommerce.entity.ItemImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemImgRepository extends JpaRepository<ItemImg, Long> {
      List<ItemImg> findByItemIdOrderByIdAsc(Long itemId);


      ItemImg findByItemIdAndRepimgYn(Long ItemId, String repimgYn);
}
