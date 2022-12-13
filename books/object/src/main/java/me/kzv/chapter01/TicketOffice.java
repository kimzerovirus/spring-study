package me.kzv.chapter01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 티켓 판매처
 * - 현금
 * - 판매할 티켓
 */
public class TicketOffice {
    private Long amount;
    private List<Ticket> tickets = new ArrayList<>();

    public TicketOffice(Long amount, Ticket... tickets) {
        this.amount = amount;
        this.tickets.addAll(Arrays.asList(tickets));
    }

    public Ticket getTicket(){ // private
        return tickets.remove(0);
    }

    public void minusAmount(Long amount) {
        this.amount -= amount;
    }

    public void plusAmount(Long amount) { // private
        this.amount += amount;
    }

    // --------- 서비스 개선하기 ---------
    /**
     *  이 변경점은 기존에 plusAmount 를 public 으로 사용했을 때와는 달리 ticketOffice 에 audience 의존성이 추가 되는 문제점이 있다.
     *  따라서 선택해야 된다. 의존성을 제거하고 갈것인지 아니면 결합하여 자율적인 클래스가 되도록 할 것인지
     *
     *  즉, 훌륭한 객체지향 설계란
     *  협력하는 객체 사이의 의존성을 적절하게 관리하는 설계라는 것이다.
     *  결합도가 높을 수록 변경하기 어려운 코드가 되므로 각 객체 간의 의존성을 적절히 타협해서 설계해야 한다.
     */
    public void sellTicketTo(Audience audience) {
        plusAmount(audience.buyV2(getTicket()));
    }
}
