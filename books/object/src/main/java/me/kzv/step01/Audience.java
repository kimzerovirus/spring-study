package me.kzv.step01;

/**
 * 관객
 * - 소지품
 */
public class Audience {
    private Bag bag;

    public Audience(Bag bag) {
        this.bag = bag;
    }

    // --------- 서비스 개선하기 ---------
    public Bag getBag() { // -- getter 삭제하여 접근 막긴
        return bag;
    }

    // 추가
    public Long buyV1(Ticket ticket) {
        if (bag.hasInvitation()) {
            bag.setTicket(ticket);
            return 0L;
        }else{
            bag.setTicket(ticket);
            bag.minusAmount(ticket.getFee());
            return ticket.getFee();
        }
    }

    public Long buyV2(Ticket ticket) {
        return bag.hold(ticket);
    }
}
