package me.kzv.tdd.persistence;

import me.kzv.tdd.supports.exception.TodoError;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.stream.Stream;

import static me.kzv.tdd.supports.fixture.TodoFixture.*;
import static org.assertj.core.api.Assertions.*;

class TodoTest {

    @ParameterizedTest
    @MethodSource("deadlineSource")
    @DisplayName("할일 생성할 때 기한은 오늘이거나 오늘보다 이후 날짜여야 한다.")
    public void create(LocalDate deadline) {
        // given
        String text = "출근하기";

        // when
        Todo todo = Todo.create(text, deadline);

        // then
        assertThat(todo.getText()).isEqualTo(text);
        assertThat(todo.getDeadline()).isEqualTo(deadline);
        assertThat(todo.isCompleted()).isFalse();
    }

    private static Stream<Arguments> deadlineSource() {
        LocalDate today = LocalDate.now();

        return Stream.of(
                Arguments.of(today),
                Arguments.of(today.plusDays(5))
        );
    }

    @Test
    @DisplayName("기한이 오늘 보다 이전인 할 일은 생성할 수 없다.")
    public void create_deadline_before_today() {
        String text = "출근하기";
        LocalDate deadline = LocalDate.now().minusDays(1);

        assertThatThrownBy(() -> Todo.create(text, deadline))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(TodoError.INVALID_DEADLINE.getMessage());
    }

    @Test
    @DisplayName("할 일 명을 변경한다.")
    public void updateText() {
        String text = "출근하기";
        Todo todo = createTodo(text);

        String newText = "퇴근하기";
        todo.updateText(newText);

        assertThat(todo.getText()).isEqualTo(newText);
    }

    @Test
    @DisplayName("할 일을 완료시킨다.")
    public void complete() {
        boolean completed = false;
        Todo todo = createTodo(completed);

        todo.complete();

        assertThat(todo.isCompleted()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("deadlineSource")
    @DisplayName("기한을 변경한다.")
    public void updateDeadline(LocalDate deadline) {
        LocalDate today = LocalDate.now();
        Todo todo = createTodo(today);

        todo.updateDeadline(deadline);

        assertThat(todo.getDeadline()).isEqualTo(deadline);
    }

    @Test
    @DisplayName("오늘 날짜 이전으로 기한을 변경할 수 없다.")
    public void cannot_update_deadline_before_today(){
        LocalDate today = LocalDate.now();
        Todo todo = createTodo(today);

        LocalDate deadline = LocalDate.now().minusDays(1);
        assertThatThrownBy(() -> todo.updateDeadline(deadline))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(TodoError.INVALID_DEADLINE.getMessage());
    }

    @Test
    @DisplayName("이미 완료된 할 일은 완료시킬 수 없다.")
    public void already_completed() {
        boolean completed = true;
        Todo todo = createTodo(completed);

        Throwable thrown = catchThrowable(todo::complete);

        assertThat(thrown)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(TodoError.ALREADY_COMPLETED.getMessage()); // IllegalStateException 등을 사용하는게 나은지 아니면 CustomException 하나 만들어서 그거 하나로 퉁칠지..
    }

    @Test
    @DisplayName("완료된 할 일을 완료 취소 한다.")
    public void cancelCompleted() {
        boolean completed = true;
        Todo todo = createTodo(completed);

        todo.cancelCompleted();

        assertThat(todo.isCompleted()).isFalse();
    }

    @Test
    @DisplayName("아직 완료되지 않은 할 일은 완료 취소할 수 없다.")
    public void cannot_cancelCompleted_not_yet_completed() {
        boolean completed = false;
        Todo todo = createTodo(completed);

        assertThatThrownBy(todo::cancelCompleted)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(TodoError.NOT_COMPLETED_YET.getMessage());
    }
}