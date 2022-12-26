package me.kzv.ecommerce.repository;

import me.kzv.ecommerce.entity.Item;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    public void 상품_저장_테스트() throws Exception {
        Item item = new Item();
        item.setItemNm("상품");
        item.setPrice(1000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setStockNumber(100);
        Item savedItem = itemRepository.save(item);
        System.out.println(savedItem);
    }

    @Test
    public void 상품명_조회_테스트() throws Exception {
        this.상품_저장_테스트();

        List<Item> itemList = itemRepository.findByItemNm("상품");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    @Test
    public void 상품명_상세설명_조회() throws Exception {
        this.상품_저장_테스트();
        var itemList = itemRepository.findByItemNmOrItemDetail("상품", "테스트 상품 상세 설명");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------");
    }

    @Test
    public void 가격이_더_싼것_조회() throws Exception {
        this.상품_저장_테스트();
        var itemList = itemRepository.findByPriceLessThan(10000);
        System.out.println("------------------------------------------------------------------------------------------------------------");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------");
    }

    @Test
    public void 가격이_더_싼것_조회_정렬_바꾸기_내림차순() throws Exception {
        this.상품_저장_테스트();
        var itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10000);
        System.out.println("------------------------------------------------------------------------------------------------------------");
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------");
    }

    @Test
    public void 상세설명_조회_정렬_바꾸기_내림차순_Query어노테이션() throws Exception {
        this.상품_저장_테스트();
        var itemList = itemRepository.findByItemDetail("상품");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println(itemList.size());
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------");

        Assertions.assertThat(itemList.size()).isGreaterThan(0);
    }
    
    @Test
    public void 상세설명_조회_정렬_바꾸기_내림차순_네이티브_쿼리() throws Exception {
        this.상품_저장_테스트();
        var itemList = itemRepository.findByItemDetailByNativeQuery("상품");
        System.out.println("------------------------------------------------------------------------------------------------------------");
        System.out.println(itemList.size());
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
        System.out.println("------------------------------------------------------------------------------------------------------------");

        Assertions.assertThat(itemList.size()).isGreaterThan(0);
    }
}