package me.kzv.ecommerce.repository;

import me.kzv.ecommerce.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
