package sample.cafekiosk.spring.domain.stock;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class StockTest {

	/*
		테스트 코드 안에 논리구조가 있다는 것은 두가지 이상의 내용을 포함하고 있다는 반증
		무슨 테스트인지 방해할 수 있다.

		테스트 코드는 각각 독립성을 보장하자.
			- 두가지 이상의 테스트가 사용하는 공유자원이 있다면 테스트가 꺠지기 쉽다.
			- 테스트 순서에 따라 성공,실패가 갈릴 수도 있다.

		기본적으로 테스트는 순서 개념이 없어야 하며 언제 수행하든 독립적으로 실행되며 동일한 결과를 내야 한다.
	 */
	@Test
	@DisplayName("재고의 수량이 제공된 수량보다 작은지 확인한다.")
	void isQuantityLessThan() {
	    // given
		Stock stock = Stock.create("001", 1);
		int quantity = 2;

	    // when
		boolean result = stock.isQuantityLessThan(quantity);

		// then
		assertThat(result).isTrue();
	}

	@Test
	@DisplayName("주어진 갯수만큼 제고를 차감할 수 있다.")
	void deductQuantity() {
		// given
		Stock stock = Stock.create("001", 1);
		int quantity = 1;

		// when
		stock.deductQuantity(quantity);

		// then
		assertThat(stock.getQuantity()).isZero();
	}

	@Test
	@DisplayName("재고보다 많은 수량으로 차감 시도하는 경우 예외가 발생한다.")
	void deductQuantityFail() {
		// given
		Stock stock = Stock.create("001", 1);
		int quantity = 2;

		// when & then
		assertThatThrownBy(() -> stock.deductQuantity(quantity))
			.isInstanceOf(IllegalArgumentException.class)
			.hasMessage("차감할 재고 수량이 없습니다.");
	}
}