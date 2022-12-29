package me.kzv.ecommerce.entity;

import me.kzv.ecommerce.constant.ItemSellStatus;
import me.kzv.ecommerce.repository.ItemRepository;
import me.kzv.ecommerce.repository.MemberRepository;
import me.kzv.ecommerce.repository.OrderItemRepository;
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
    MemberRepository memberRepository;
    @Autowired
    OrderItemRepository orderItemRepository;

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
    public void 영속성_전이_테스트_저장() throws Exception {
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

    @Test
    public void 영속성_전이_테스트_삭제() throws Exception {
        //given
        Order savedOrder = createOrder();

        //when
        savedOrder.getOrderItems().remove(0);
        em.flush();

        //then
        assertEquals(2, savedOrder.getOrderItems().size());
    }


    @Test
    public void 지연_로딩_테스트() throws Exception {
        //given - 주문 데이터 저장
        var order = createOrder();
        Long orderItemId = order.getOrderItems().get(0).getId();
        em.flush();
        em.clear();

        //when
        // - 영속성 컨텍스트의 상태 초기화 후 order 엔티티에 저장했던 주문 상품 아이디를 이용하여 orderItem을 데이터베이스에서 다시 조회합니다.
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(EntityNotFoundException::new);
        /**
         *  조회 eager - 전부 join 해서 가져온다
         *  orderItem, item, order, member 가 전부 연관되어 조회된다.
         *
         * select
         *         orderitem0_.order_item_id as order_it1_5_0_,
         *         orderitem0_.reg_time as reg_time2_5_0_,
         *         orderitem0_.update_time as update_t3_5_0_,
         *         orderitem0_.created_by as created_4_5_0_,
         *         orderitem0_.modified_by as modified5_5_0_,
         *         orderitem0_.count as count6_5_0_,
         *         orderitem0_.item_id as item_id8_5_0_,
         *         orderitem0_.order_id as order_id9_5_0_,
         *         orderitem0_.order_price as order_pr7_5_0_,
         *         item1_.item_id as item_id1_2_1_,
         *         item1_.reg_time as reg_time2_2_1_,
         *         item1_.update_time as update_t3_2_1_,
         *         item1_.created_by as created_4_2_1_,
         *         item1_.modified_by as modified5_2_1_,
         *         item1_.item_detail as item_det6_2_1_,
         *         item1_.item_nm as item_nm7_2_1_,
         *         item1_.item_sell_status as item_sel8_2_1_,
         *         item1_.price as price9_2_1_,
         *         item1_.stock_number as stock_n10_2_1_,
         *         order2_.order_id as order_id1_6_2_,
         *         order2_.reg_time as reg_time2_6_2_,
         *         order2_.update_time as update_t3_6_2_,
         *         order2_.created_by as created_4_6_2_,
         *         order2_.modified_by as modified5_6_2_,
         *         order2_.member_id as member_i8_6_2_,
         *         order2_.order_date as order_da6_6_2_,
         *         order2_.order_status as order_st7_6_2_
         *     from
         *         order_item orderitem0_
         *     left outer join
         *         item item1_
         *             on orderitem0_.item_id=item1_.item_id
         *     left outer join
         *         orders order2_
         *             on orderitem0_.order_id=order2_.order_id
         *     where
         *         orderitem0_.order_item_id=? - order 클래스에 member는 lazy로 설정 되어 있어서 안가져와짐
         *
         *
         *  조회 lazy
         *      select
         *         orderitem0_.order_item_id as order_it1_5_0_,
         *         orderitem0_.reg_time as reg_time2_5_0_,
         *         orderitem0_.update_time as update_t3_5_0_,
         *         orderitem0_.created_by as created_4_5_0_,
         *         orderitem0_.modified_by as modified5_5_0_,
         *         orderitem0_.count as count6_5_0_,
         *         orderitem0_.item_id as item_id8_5_0_,
         *         orderitem0_.order_id as order_id9_5_0_,
         *         orderitem0_.order_price as order_pr7_5_0_
         *     from
         *         order_item orderitem0_
         *     where
         *         orderitem0_.order_item_id=?
         */

        // - orderItem 엔티티에 있는 order 객체의 클래스를 출력합니다. Order 클래스가 출력되는 것을 확인할 수 있습니다.
        System.out.println("Order class : "+ orderItem.getOrder().getClass());

        //then
    }

    private Order createOrder() {
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
        Member member = new Member();
        memberRepository.save(member);

        order.setMember(member);
        Order savedOrder = orderRepository.save(order);
        return savedOrder;
    }

}