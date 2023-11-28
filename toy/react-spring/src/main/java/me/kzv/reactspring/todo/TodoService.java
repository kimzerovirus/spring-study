package me.kzv.reactspring.todo;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TodoService {

    private final ModelMapper modelMapper;

    private final TodoRepository todoRepository;

    public Long register(TodoDto dto) {
        Todo todo = modelMapper.map(dto, Todo.class);
        Todo savedTodo = todoRepository.save(todo);
        return savedTodo.getId();
    }
}
