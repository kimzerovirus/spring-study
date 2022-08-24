package me.kzv.core1.spring.order;

import me.kzv.core1.spring.discount.DiscountPolicy;
import me.kzv.core1.spring.member.Member;
import me.kzv.core1.spring.member.MemberRepository;

public class OrderServiceImpl implements OrderService {
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final DiscountPolicy discountPolicy; // 구현 클래스를 의존하지 않고 인터페이스만 의존하게 만들기
    // TODO 다만 이렇게 되면 누군가가 DiscountPolicy 의 구현 객체를 대신 생성하고 주입해 줘야 한다!!!
    // -> 생성자로 구현 객체를 주입 받아서 해결!!!

    private final MemberRepository memberRepository;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    /** 싱글톤 테스트 용도 */
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
