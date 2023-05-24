package me.kzv.ecommercejpatdd.payment.adapter;

interface PaymentGateway {
    void execute(int totalPrice, String cardNumber);
}
