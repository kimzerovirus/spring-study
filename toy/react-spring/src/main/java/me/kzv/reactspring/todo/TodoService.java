package me.kzv.reactspring.todo;

import lombok.RequiredArgsConstructor;
import me.kzv.reactspring.common.PageRequest;
import me.kzv.reactspring.common.PageResponse;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
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

    public PageResponse<TodoDto> list(PageRequest dto) {
        var pageable = org.springframework.data.domain.PageRequest.of(dto.getPage() - 1, dto.getSize(), Sort.by("id").descending());
        var result = todoRepository.findAll(pageable);
        var dtoList = result.getContent().stream()
                .map(todo -> modelMapper.map(todo, TodoDto.class))
                .toList();

        var totalContent = result.getTotalElements();

        return PageResponse.<TodoDto>withAll()
                .dtoList(dtoList)
                .request(dto)
                .totalCount(totalContent)
                .build();
    }

    @Transactional
    public void modify(TodoDto dto) {
        Todo todo = todoRepository.findById(dto.getId()).orElseThrow();
        todo.changeTitle(dto.getTitle());
        todo.changeDueDate(dto.getDueDate());
        todo.changeComplete(dto.isComplete());
    }
}
