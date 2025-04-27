package core.parameterized;

import me.kzv.core.parameterized.WordCounter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.EnumSource.Mode.EXCLUDE;

class ParameterizedWithEnumSourceTest {
    private final WordCounter wordCounter = new WordCounter();

    @ParameterizedTest
    @EnumSource(Sentences.class)
    void testWordsInSentence(Sentences sentence) {
        assertEquals(3, wordCounter.countWords(sentence.value()));
    }

    // name 에서 지정한 대상에 대해 테스트를 진행한다.
    @ParameterizedTest
    @EnumSource(value = Sentences.class, names = {"JUNIT_IN_ACTION", "THREE_PARAMETERS"})
    void testSelectedWordsInSentence(Sentences sentence) {
        assertEquals(3, wordCounter.countWords(sentence.value()));
    }

    // name 에서 지정한 대상을 제외한 나머지에 대해 테스트를 진행한다.
    @ParameterizedTest
    @EnumSource(value = Sentences.class, mode = EXCLUDE, names = {"THREE_PARAMETERS"})
    void testExcludedWordsInSentence(Sentences sentence) {
        assertEquals(3, wordCounter.countWords(sentence.value()));
    }

    enum Sentences {
        JUNIT_IN_ACTION("JUnit in Action"),
        SOME_PARAMETERS("Check some parameters"),
        THREE_PARAMETERS("Check three parameters");

        private final String sentence;

        Sentences(String sentence) {
            this.sentence = sentence;
        }

        public String value() {
            return sentence;
        }
    }
}
