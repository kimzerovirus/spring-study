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

    public TodoDto get(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        return modelMapper.map(todo, TodoDto.class);
    }

    @Transactional
    public void modify(TodoDto dto) {
        Todo todo = todoRepository.findById(dto.getId()).orElseThrow();
        todo.changeTitle(dto.getTitle());
        todo.changeDueDate(dto.getDueDate());
        todo.changeComplete(dto.isComplete());
    }
}
