package me.kzv.shopapi;

import lombok.RequiredArgsConstructor;
import me.kzv.shopapi.domain.*;
import me.kzv.shopapi.domain.item.Book;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final InitService initService;

    @PostConstruct // 스프링 빈이 다 올라오고 나면 스프링이 호출해줌
    public void init(){
        initService.dbInit1(); // 이 안에서 Transaction 이나 이런 로직을 작성하기 보다는 별도의 빈으로 등록 후 가져오는게 좋다.
        initService.dbInit2();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager entityManager;

        public void dbInit1() {
            Member member = createMember("userA","서울", "1", "1111");
            entityManager.persist(member);

            Book book1 = createBook("JPA BOOK1", 10000, 100);
            entityManager.persist(book1);

            Book book2 = createBook("JPA BOOK2", 20000 , 200);
            entityManager.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 10000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 20000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());

            Order.createOrder(member, delivery, orderItem1, orderItem2);
        }

        private Book createBook(String name, int price, int quantity) {
            Book book1 = new Book();
            book1.setName(name);
            book1.setPrice(price);
            book1.setStockQuantity(quantity);
            return book1;
        }


        public void dbInit2() {
            Member member = createMember("userB","부산", "2", "2222");
            entityManager.persist(member);

            Book book1 = createBook("SPRING BOOK1", 15000 , 100);
            entityManager.persist(book1);

            Book book2 = createBook("SPRING BOOK2", 30000 , 200);
            entityManager.persist(book2);

            OrderItem orderItem1 = OrderItem.createOrderItem(book1, 15000, 1);
            OrderItem orderItem2 = OrderItem.createOrderItem(book2, 30000, 2);

            Delivery delivery = new Delivery();
            delivery.setAddress(member.getAddress());

            Order.createOrder(member, delivery, orderItem1, orderItem2);
        }

        private Member createMember(String name, String city, String street, String zipcode) {
            Member member = new Member();
            member.setName(name);
            member.setAddress(new Address(city, street, zipcode));
            return member;
        }

    }
}
