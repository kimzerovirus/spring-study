package me.kzv.ecommerce.repository;

import me.kzv.ecommerce.dto.CartDetailDto;
import me.kzv.ecommerce.entity.Cart;
import me.kzv.ecommerce.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByMemberId(Long memberId);
}
