package me.kzv.batch.example.processor;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class PercentTest {

    @Test
    public void 짝수를_숫자2로_퍼센트로_나누면_0이된다() {
        //given
        long id = 2;

        //when
        long remain = id % 2;

        //then
        assertThat(remain, is(0L));
    }
}
