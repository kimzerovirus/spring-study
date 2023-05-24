package me.kzv.ecommercejpatdd.payment;

import me.kzv.ecommercejpatdd.ApiTest;
import me.kzv.ecommercejpatdd.order.OrderSteps;
import me.kzv.ecommercejpatdd.product.ProductSteps;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PaymentApiTest extends ApiTest {

    @Test
    void 주문결제() {
        ProductSteps.상품등록요청(ProductSteps.상품등록요청_생성());
        OrderSteps.상품주문요청(OrderSteps.상품주문요청_생성());
        final var request = PaymentSteps.주문결제요청_생성();

        final var response = PaymentSteps.주문결제요청(request);

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

}
