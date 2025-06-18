package me.kzv.tdd.application.todo;

import com.google.common.base.Preconditions;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.kzv.tdd.supports.converter.BooleanToYNConverter;
import me.kzv.tdd.supports.exception.TodoError;

import java.time.LocalDate;

/**
 * 할일
 */
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Todo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** 할일 명 */
    private String text;

    /** 기한 */
    private LocalDate deadline;

    /** 완료 여부 */
    @Convert(converter = BooleanToYNConverter.class)
    private boolean completed;

    public static Todo create(String text, LocalDate deadline) {
        validateDeadline(deadline);

        return Todo.builder()
                .text(text)
                .deadline(deadline)
                .completed(false)
                .build();
    }

    private static void validateDeadline(LocalDate deadline) {
        LocalDate today = LocalDate.now();
        Preconditions.checkArgument(deadline.isAfter(today) || deadline.isEqual(today), TodoError.INVALID_DEADLINE.getMessage());
    }

    public void updateText(String text) {
        this.text = text;
    }
    
    

    public void complete() {
        Preconditions.checkState(!this.completed, TodoError.ALREADY_COMPLETED.getMessage());

        this.completed = true;
    }

    public void cancelCompleted() {
        Preconditions.checkState(this.completed, TodoError.NOT_COMPLETED_YET.getMessage());

        this.completed = false;
    }

    public void updateDeadline(LocalDate deadline) {
        validateDeadline(deadline);

        this.deadline = deadline;
    }
}
