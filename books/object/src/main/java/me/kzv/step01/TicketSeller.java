package me.kzv.step01;

/**
 * 판매원
 * - 판매원이 일하고 있는 판매지점 - 매표소에서 초대장을 티켓으로 교환해주거나 티켓을 판매하는 일을 한다.
 */
public class TicketSeller {
    private TicketOffice ticketOffice;

    public TicketSeller(TicketOffice ticketOffice) {
        this.ticketOffice = ticketOffice;
    }

    // --------- 서비스 개선하기 ---------

    // getter 삭제하여 접근 못하게 하기
    public TicketOffice getTicketOffice() {
        return ticketOffice;
    }

    /**
     * ticketSeller 에서 ticketOffice 를 가져오던 기존 코드에서 변경
     * 캡슐화
     * - 객체 내부로 접근을 제한하여 객체와 객체 사이의 결합도를 낮추기
     */

    public void sellToV1(Audience audience) {
        if (audience.getBag().hasInvitation()) {
            // 관객이 초대장을 가지고 있는 경우
            Ticket ticket = ticketOffice.getTicket();
            audience.getBag().setTicket(ticket);
        } else {
            // 관객이 초대장을 가지고 있지 않는 경우
            Ticket ticket = ticketOffice.getTicket();
            audience.getBag().minusAmount(ticket.getFee()); // 관객 - 지출, 소지품에서 현금 감소
            ticketOffice.plusAmount(ticket.getFee()); // 티켓 오피스 - 매상: 현금 증가
            audience.getBag().setTicket(ticket); // 관객 - 소지품에 티켓이 추가
        }
    }

    // TODO 티켓 판매원이 관객 인터페이스만 의존하도록 수정
    public void sellToV2(Audience audience) {
//        ticketOffice.plusAmount(audience.buyV2(ticketOffice.getTicket()));
        ticketOffice.sellTicketTo(audience);
    }


}
