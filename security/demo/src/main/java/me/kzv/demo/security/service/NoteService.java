package me.kzv.demo.security.service;

import lombok.RequiredArgsConstructor;
import me.kzv.demo.web.domain.ClubMember;
import me.kzv.demo.web.domain.Note;
import me.kzv.demo.web.repository.NoteRepository;
import me.kzv.demo.web.dto.NoteDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class NoteService {

    private final NoteRepository repository;

    public Long register(NoteDto dto) {
        Note note = dtoToEntity(dto);
        repository.save(note);

        return note.getNum();
    }

    public NoteDto get(Long num) {
        Optional<Note> result = repository.getWithWriter(num);

        if (result.isPresent()) {
            return entityToDto(result.get());
        }

        return null;
    }

    public void modify(NoteDto dto) {
        Long num = dto.getNum();
        Optional<Note> result = repository.findById(num);

        if (result.isPresent()) {
            Note note = result.get();
            note.changeTitle(dto.getTitle());
            note.changeContent(dto.getContent());
            repository.save(note);
        }
    }

    public void remove(Long num) {
        repository.deleteById(num);
    }

    public List<NoteDto> getAllWithWriter(String writerEmail) {
        List<Note> noteList = repository.getList(writerEmail);

        return noteList.stream().map(note -> entityToDto(note)).collect(Collectors.toList());
    }

    private Note dtoToEntity(NoteDto dto) {
        return Note.builder()
                .num(dto.getNum())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(ClubMember.builder().email(dto.getWriterEmail()).build())
                .build();
    }

    private NoteDto entityToDto(Note note) {
        return NoteDto.builder()
                .num(note.getNum())
                .title(note.getTitle())
                .content(note.getContent())
                .writerEmail(note.getWriter().getEmail())
                .regDate(note.getRegDate())
                .modDate(note.getModDate())
                .build();
    }

}
