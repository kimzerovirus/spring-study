package me.kzv.demo.controller;

import lombok.RequiredArgsConstructor;
import me.kzv.demo.security.dto.NoteDto;
import me.kzv.demo.security.service.NoteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes/")
@RequiredArgsConstructor
public class NoteApiController {
    private final NoteService noteService;

    @PostMapping(value = "")
    public ResponseEntity<Long> register(@RequestBody NoteDto dto) {
        Long num = noteService.register(dto);

        return new ResponseEntity<>(num, HttpStatus.OK);
    }

    // http://localhost:8080/notes/2
    @GetMapping(value = "/{num}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<NoteDto> read(@PathVariable("num") Long num) {
        return new ResponseEntity<>(noteService.get(num), HttpStatus.OK);
    }

    // http://localhost:8080/notes/all?email=user@aaa.com
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NoteDto>> getList(String email) {
        return new ResponseEntity<>(noteService.getAllWithWriter(email), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{num}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> remove(@PathVariable("num") Long num) {
        noteService.remove(num);
        return new ResponseEntity<>("removed", HttpStatus.OK);
    }

    @PutMapping(value = "/{num}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> modify(@RequestBody NoteDto dto) {
        noteService.modify(dto);
        return new ResponseEntity<>("modified", HttpStatus.OK);
    }
}
