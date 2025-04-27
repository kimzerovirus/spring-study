package core.hamcrest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.anyOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HamcrestListTest {

    private List<String> values;

    @BeforeEach
    public void setUp() {
        values = new ArrayList<>();
        values.add("John");
        values.add("Michael");
        values.add("Edwin");
    }

    @Test
    @DisplayName("Hamcrest를 사용하지 않아 실패 정보를 자세히 표현하지 못하는 테스트")
    public void testListWithoutHamcrest() {
        assertEquals(3, values.size());
        assertTrue(values.contains("Oliver") || values.contains("Jack") || values.contains("Harry"));
    }

    // hamcrest 패키지를 사용하면 실패 정보를 상세하게 로그에 찍어 준다.
    //

    @Test
    @DisplayName("Hamcrest를 사용해서 자세한 실패 정보를 나타내는 테스트")
    public void testListWithHamcrest() {
        assertThat(values, hasSize(3));
        assertThat(values, hasItem(anyOf(equalTo("Oliver"), equalTo("Jack"),
                equalTo("Harry"))));
        assertThat("리스트의 순서에 맞게 객체를 포함하고 있는지 검증", values, contains("Oliver", "Jack", "Harry"));
        assertThat("리스트의 순서에 상관없이 객체를 포함하고 있는지 검증", values, containsInAnyOrder("Jack", "Harry", "Oliver"));
    }
}
