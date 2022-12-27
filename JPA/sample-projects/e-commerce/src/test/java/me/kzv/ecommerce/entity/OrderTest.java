package me.kzv.ecommerce.entity;

import me.kzv.ecommerce.constant.ItemSellStatus;
import me.kzv.ecommerce.repository.ItemRepository;
import me.kzv.ecommerce.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class OrderTest {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    @PersistenceContext
    EntityManager em;

    public Item createItem() {
        var item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("상세설명");
        item.setItemSellStatus(ItemSellStatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        return itemRepository.save(item);
    }

    @Test
    public void 영속성_전이_테스트() throws Exception {
        //given
        var order = new Order();
        IntStream.rangeClosed(1, 3).forEach(i -> {
            var item = this.createItem();
            var orderItem = new OrderItem();
            orderItem.setItem(item);
            orderItem.setCount(10);
            orderItem.setOrderPrice(1000);
            orderItem.setOrder(order);
            order.getOrderItems().add(orderItem);
        });
        orderRepository.saveAndFlush(order);
        em.clear();

        //when
        Order savedOrder = orderRepository.findById(order.getId()).orElseThrow(EntityNotFoundException::new);

        //then
        assertEquals(3, savedOrder.getOrderItems().size());
    }

}