package me.kzv.tdd.application.todo;

import me.kzv.tdd.supports.test.BaseControllerTest;

public class TodoControllerTest extends BaseControllerTest {
    private final TodoService todoService;

    public TodoControllerTest(TodoService todoService) {
        this.todoService = todoService;
    }

}
