package me.kzv.ecommerce.service;

import lombok.RequiredArgsConstructor;
import me.kzv.ecommerce.dto.OrderDto;
import me.kzv.ecommerce.entity.Item;
import me.kzv.ecommerce.entity.Member;
import me.kzv.ecommerce.entity.Order;
import me.kzv.ecommerce.entity.OrderItem;
import me.kzv.ecommerce.repository.ItemRepository;
import me.kzv.ecommerce.repository.MemberRepository;
import me.kzv.ecommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;

    public Long order(OrderDto orderDto, String email) {
        Item item = itemRepository.findById(orderDto.getItemId()).orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);

        List<OrderItem> orderItemList = new ArrayList<>();
        OrderItem orderItem = OrderItem.createOrderItem(item, orderDto.getCount());
        orderItemList.add(orderItem);

        Order order = Order.createOrder(member, orderItemList);
        orderRepository.save(order);

        return order.getId();
    }

    public void cancelOrder(Long orderId) {

    }
}
