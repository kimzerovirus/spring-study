package me.kzv.chapter02.movie.step02;

public class Reservation {
    private Customer customer;
    private Screening screening;
    private Money fee;
    private int audienceCount; // 관객 수

    public Reservation(Customer customer, Screening screening, Money fee, int audienceCount) {
        this.customer = customer;
        this.screening = screening;
        this.fee = fee;
        this.audienceCount = audienceCount;
    }


}