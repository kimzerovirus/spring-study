package core.parameterized;

import me.kzv.core.parameterized.WordCounter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ParameterizedWithValueSourceTest {
    private final WordCounter wordCounter = new WordCounter();

    @ParameterizedTest
    @ValueSource(strings = {"Check three parameters", "JUnit in Action"})
    void testWordsInSentence(String sentence) {
        assertEquals(3, wordCounter.countWords(sentence));
    }
}
