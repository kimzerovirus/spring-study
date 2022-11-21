package me.kzv.step01;

/**
 * 이 모듈들은 몇 가지 문제점이 있다.
 * - 변경이 용이하지 않다.
 * - 코드의 가독성이 좋은 편이 아니다.
 *
 * 무엇보다도 관람객과 판매원이 소극장의 통제를 받는 수동적인 존재라는 문제점이 있다. -> 소극장이 마음대로 관객의 소지품을 볼 수 있다는 의미!
 * v1은 절차적 프로그래밍이다. - 필요한 모든 데이터에 의존한다는 문제점이 있다!
 * v2는 객체지향적으로 하나의 클래스에서 작업이 끝
 *
 * v1과 v2 의 근본적 차이점은 책임의 이동
 * v1은 Theater 에 책임이 집중되어 있지만
 * v2는 개별 객체가 책임을 지고 있다.
 *
 */
public class Theater {
    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    public void enterV1(Audience audience) {
        if (audience.getBag().hasInvitation()) {
            // 관객이 초대장을 가지고 있는 경우
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().setTicket(ticket);
        } else {
            // 관객이 초대장을 가지고 있지 않는 경우
            Ticket ticket = ticketSeller.getTicketOffice().getTicket();
            audience.getBag().minusAmount(ticket.getFee()); // 관객 - 지출, 소지품에서 현금 감소
            ticketSeller.getTicketOffice().plusAmount(ticket.getFee()); // 티켓 오피스 - 매상: 현금 증가
            audience.getBag().setTicket(ticket); // 관객 - 소지품에 티켓이 추가
        }

    }

//    TODO TicketSeller 내부로 숨겨 Theater 가 많은 정보를 볼 수 없게 만들자
    public void enterV2(Audience audience) {
        ticketSeller.sellToV1(audience);
    }

}
