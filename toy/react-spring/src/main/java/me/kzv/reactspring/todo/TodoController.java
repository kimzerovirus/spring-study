package me.kzv.reactspring.todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.kzv.reactspring.common.dto.PageRequest;
import me.kzv.reactspring.common.dto.PageResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@Log4j2
@RequestMapping("/api/todo")
public class TodoController {
    private final TodoService todoService;

    @GetMapping("/{tno}")
    public TodoDto get(@PathVariable Long tno) {
        return todoService.get(tno);
    }

    @GetMapping("/list")
    public PageResponse<TodoDto> list(PageRequest request) {
        log.info(request);
        return todoService.list(request);
    }

    @PostMapping("/")
    public Map<String, Long> register(@RequestBody TodoDto dto) {
        log.info("TodoDTO: " + dto);
        var id = todoService.register(dto);

        return Map.of("id", id);
    }

    @PutMapping("/{tno}")
    public Map<String, String> modify(@PathVariable Long tno, @RequestBody TodoDto dto) {
        dto.setId(tno);
        todoService.modify(dto);
        return Map.of("result", "SUCCESS");
    }

    @DeleteMapping("/{tno}")
    public Map<String, String> remove(@PathVariable Long tno) {
        todoService.remove(tno);

        return Map.of("result", "SUCCESS");
    }
}
