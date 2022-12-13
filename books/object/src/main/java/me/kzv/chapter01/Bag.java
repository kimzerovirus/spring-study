package me.kzv.chapter01;

/**
 * 소지품
 * - 초대장 (티켓과 교환)
 * - 현금 (티켓을 구매)
 * - 티켓
 */
public class Bag {
    private Long amount; // 현금
    private Invitation invitation; // 초대장
    private Ticket ticket; // 티켓

    // 초대장 없이 현금만 소지한 경우
    public Bag(Long amount) {
        this(amount, null);
    }

    // 초대장과 현금 둘다 소지한 경우
    public Bag(Long amount, Invitation invitation) {
        this.amount = amount;
        this.invitation = invitation;
    }

    public boolean hasInvitation(){
        return invitation != null;
    }

    public boolean hasTicket(){
        return ticket != null;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    public void plusAmount(Long amount) {
        this.amount += amount;
    }


    public Long hold(Ticket ticket) {
        if (hasInvitation()) {
            setTicket(ticket);
            return 0L;
        }else{
            setTicket(ticket);
            minusAmount(ticket.getFee());
            return ticket.getFee();
        }
    }
}
