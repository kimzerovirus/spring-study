package me.kzv.ecommerce.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import me.kzv.ecommerce.constant.ItemSellStatus;
import me.kzv.ecommerce.entity.Item;
import me.kzv.ecommerce.entity.QItem;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.awt.print.Pageable;
import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    public void 상품_저장_테스트() throws Exception {
        Item item = new Item();
        item.setItemNm("상품");
        item.setPrice(1000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setStockNumber(100);
        item.setItemSellStatus(ItemSellStatus.SELL);
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

    @Test
    public void querydslTest() throws Exception {
        this.상품_저장_테스트();
        var queryFactory = new JPAQueryFactory(em);
        QItem qItem = QItem.item;
        var query = queryFactory.selectFrom(qItem)
                .where(qItem.itemSellStatus.eq(ItemSellStatus.SELL))
                .where(qItem.itemDetail.like("%" + "상품" + "%"))
                .orderBy(qItem.price.desc());

        var itemList = query.fetch();
        for (Item item : itemList) {
            System.out.println(item.toString());
        }
    }

    /**
     * QuerydslPredicateExecutor
     */
    public void createItemList2() {
        IntStream.rangeClosed(1, 10).forEach((i) -> {
            Item item = new Item();
            item.setItemNm("상품" + i);
            item.setPrice(10000 + i);
            item.setItemDetail("테스트 상품 상세 설명" + i);
            if(i<6){
                item.setItemSellStatus(ItemSellStatus.SELL);
                item.setStockNumber(100);
            } else{
                item.setItemSellStatus(ItemSellStatus.SOLD_OUT);
                item.setStockNumber(0);
            }
            Item savedItem = itemRepository.save(item);
            System.out.println(savedItem);
        });
    }

    @Test
    public void querydsl2() throws Exception {
        //given
        this.createItemList2();

        //when
        var booleanBuilder = new BooleanBuilder();
        QItem qItem = QItem.item;
        String itemDetail = "테스트 상품 상세 설명";
        int price = 10003;
        String itemSellStatus = "SELL";

        booleanBuilder.and(qItem.itemDetail.like("%" + itemDetail + "%"));
        booleanBuilder.and(qItem.price.gt(price));

        if (StringUtils.equals(itemSellStatus, ItemSellStatus.SELL)) {
            booleanBuilder.and(qItem.itemSellStatus.eq(ItemSellStatus.SELL));
        }

        PageRequest pageable = PageRequest.of(0, 5);
        Page<Item> itemPagingResult = itemRepository.findAll(booleanBuilder, pageable); // 	Page<T> findAll(Predicate predicate, Pageable pageable);
        /**
         * - BooleanBuilder 는 쿼리에 들어갈 조건을 만들어주는 빌더 Predicate  ~chain
         * - 필요한 상품을 조회하는데 필요한 조건을 "and" 로 추가
         * - 페이징 처리
         * - QueryDslPredicateExecutor 를 통해 인터페이스에 정의한 findAll() 메소드를 이용
         */

        //then
        System.out.println("total elements : " + itemPagingResult.getTotalElements());
        List<Item> content = itemPagingResult.getContent();
        for (Item item : content) {
            System.out.println(item.toString());
        }

        /**
         * Hibernate:
         *     insert
         *     into
         *         item
         *         (reg_time, update_time, created_by, modified_by, item_detail, item_nm, item_sell_status, price, stock_number, item_id)
         *     values
         *         (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
         * Hibernate:
         *     select
         *         item0_.item_id as item_id1_2_,
         *         item0_.reg_time as reg_time2_2_,
         *         item0_.update_time as update_t3_2_,
         *         item0_.created_by as created_4_2_,
         *         item0_.modified_by as modified5_2_,
         *         item0_.item_detail as item_det6_2_,
         *         item0_.item_nm as item_nm7_2_,
         *         item0_.item_sell_status as item_sel8_2_,
         *         item0_.price as price9_2_,
         *         item0_.stock_number as stock_n10_2_
         *     from
         *         item item0_
         *     where
         *         (
         *             item0_.item_detail like ? escape '!'
         *         )
         *         and item0_.price>?
         *         and item0_.item_sell_status=? limit ?
         */
    }
}