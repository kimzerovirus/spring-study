package me.kzv.ecommercejpatdd.payment.adapter;

import me.kzv.ecommercejpatdd.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

interface PaymentRepository extends JpaRepository<Payment, Long> {
}
