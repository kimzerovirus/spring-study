package me.kzv.tdd.supports.assertion;

import me.kzv.tdd.supports.exception.TodoError;
import me.kzv.tdd.supports.exception.TodoException;
import org.junit.jupiter.api.function.Executable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * sample
 */
public class TodoAssertions {
    public static void assertTodoExceptionThrows(TodoError error, Executable executable) {
        TodoException todoException = assertThrows(TodoException.class, executable);

        assertThat(todoException.getMessage()).isEqualTo(error.getMessage());
    }
}
