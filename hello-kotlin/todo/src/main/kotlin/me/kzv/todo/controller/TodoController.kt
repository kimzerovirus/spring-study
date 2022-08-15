package me.kzv.todo.controller

import me.kzv.todo.controller.dto.TodoListResponseDto
import me.kzv.todo.controller.dto.TodoRequestDto
import me.kzv.todo.controller.dto.TodoResponseDto
import me.kzv.todo.service.TodoService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.noContent
import org.springframework.http.ResponseEntity.ok
import org.springframework.web.bind.annotation.*

@RestController
class TodoController(
    private val todoService: TodoService
) {
    @GetMapping
    fun getAll() = ok(TodoListResponseDto.of(todoService.findAll())) // ResponseEntity.ok

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long) = ok(todoService.findById(id))

    @PostMapping
    fun create(@RequestBody requestDto: TodoRequestDto) = ok(TodoResponseDto.of(todoService.create(requestDto)))

    @PostMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody requestDto: TodoRequestDto) =
        ok(TodoResponseDto.of(todoService.update(id, requestDto)))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Unit> {
        todoService.delete(id)
        return noContent().build()
    }
}