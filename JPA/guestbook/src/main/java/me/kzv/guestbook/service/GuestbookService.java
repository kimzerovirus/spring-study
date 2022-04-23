package me.kzv.guestbook.service;

import me.kzv.guestbook.dto.GuestbookDTO;
import me.kzv.guestbook.dto.PageRequestDTO;
import me.kzv.guestbook.dto.PageResultDTO;
import me.kzv.guestbook.entity.Guestbook;

public interface GuestbookService {
    Long register(GuestbookDTO dto);
    PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO);

    default Guestbook dtoToEntity(GuestbookDTO dto){
        Guestbook entity = Guestbook.builder()
                .gno(dto.getGno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(dto.getWriter())
                .build();

        return entity;
    }

    default GuestbookDTO entityToDto(Guestbook entity){
        GuestbookDTO dto = GuestbookDTO.builder()
                .gno(entity.getGno())
                .title(entity.getTitle())
                .content(entity.getContent())
                .writer(entity.getWriter())
                .build();

        return dto;
    }

    GuestbookDTO read(Long gno);
    void remove(Long gno);
    void modify(GuestbookDTO dto);
}
