package me.kzv.tdd.supports.fixture;

import me.kzv.tdd.persistence.Todo;

import java.time.LocalDate;

/**
 * default parameter 가 없는 자바에서는 아래와 같이 메서드 오버라이딩을 통해 만들어야 하는데
 * 코드 작업 할게 너무 많다.. fixture를 만드는게 효율적이지는 않은듯하다.
 */
public class TodoFixture {
    public static Todo createTodo(String todo, LocalDate deadline, Boolean completed){
        return Todo.builder()
                .text(todo)
                .deadline(deadline)
                .completed(completed)
                .build();
    }

    public static Todo createTodo(String todo, LocalDate deadline){
        return createTodo(todo, deadline, false);
    }

    public static Todo createTodo(String todo, boolean completed){
        return createTodo(todo, LocalDate.now(), completed);
    }

    public static Todo createTodo(String todo){
        return createTodo(todo, false);
    }

    public static Todo createTodo(LocalDate deadline){
        return createTodo("todo", deadline);
    }

    public static Todo createTodo(boolean completed){
        return createTodo("todo", completed);
    }

    public static Todo createTodo() {
        return createTodo("test todo");
    }
}
