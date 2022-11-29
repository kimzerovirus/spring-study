package me.kzv.ecommerce.entity;

import lombok.Data;
import me.kzv.ecommerce.constant.OrderStatus;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "\"order\"")
@Data
@Entity
public class Order extends BaseEntity {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderItem> orderItems = new ArrayList<>();

    /**
     * cascade type
     * PERSIST 부모 엔티티가 영속화될 때 자식 엔티티도 영속화
     * MERGE   부모 엔티티가 병합될 때 자식 엔티티도 병합
     * REMOVE  부모 엔티티가 삭제될 때 연관된 자식 엔티티도 삭제
     * REFRESH 부모 엔티티가 refresh 되면 연관된 자식 엔티티도 refresh
     * DETACH  부모 엔티티가 부모 엔티티가 detach 되면 연관된 자식 엔티티도 detach 상태로 변경
     * ALL     부모 엔티티가 영속성 상태 변화를 자식 엔티티에 모두 전이
     * <p>
     * 보통 1:N 관계 테이블 설정할때 저렇게 옵션을 추가해준다.
     * 자식 엔티티의 변경이 있다면
     * JPA 에서 자식엔티티의 수정은 insert update update delete 순으로 이어지는데
     * 변경된 자식을 먼저 insert 하고
     * 기존의 자식을 NULL 로 update 한다.
     * 그리고 orphanRemoval 옵션을 true 로 하면 기존 NULL 처리된 자식을 DELETE 한다.
     * PK(JoinColumn)값이 NULL 로 변한 자식은 고아객체라고 하여 연결된 점이 없는 객체이다.
     * orphanRemoval 옵션은 바로 이 고아객체를 삭제해주는 역활을 한다.
     */

    public void addOrderItem(OrderItem orderItem) {
        // 양방향 참조 관게이므로
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public static Order createOrder(Member member, List<OrderItem> orderItemList) {
        Order order = new Order();
        order.setMember(member);
        for (OrderItem orderItem : orderItemList) {
            order.addOrderItem(orderItem);
        }

        order.setOrderStatus(OrderStatus.ORDER); // 주문상태 ORDER
        order.setOrderDate(LocalDateTime.now());
        return order;
    }

    public int getTotalPrice(){
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getTotalPrice();
        }

        return totalPrice;
    }

    public void cancelOrder(){
        // 주문 취소는 상품 (Item) -> 주문 (OrderItem) -> 주문목록 (Order) 3개의 객체가 연관되어 있다.
        this.orderStatus = OrderStatus.CANCEL;

        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }
    }
}
