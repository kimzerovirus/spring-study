package me.kzv.guestbook.service;

import lombok.RequiredArgsConstructor;
import me.kzv.guestbook.dto.GuestbookDTO;
import me.kzv.guestbook.dto.PageRequestDTO;
import me.kzv.guestbook.dto.PageResultDTO;
import me.kzv.guestbook.entity.Guestbook;
import me.kzv.guestbook.repository.GuestbookRepository;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import static org.junit.jupiter.api.Assertions.*;

@Service
@RequiredArgsConstructor // 의존성 자동 주입
public class GuestbookServiceImplTest implements GuestbookService {
    private final GuestbookRepository repository; //  final로 선언

    @Override
    public Long register(GuestbookDTO dto) {

        Guestbook entity = dtoToEntity(dto);

        repository.save(entity);

        return entity.getGno();
    }

    @Override
    public PageResultDTO<GuestbookDTO, Guestbook> getList(PageRequestDTO requestDTO) {
        return null;
    }

    @Override
    public GuestbookDTO read(Long gno) {
        return null;
    }

    @Override
    public void remove(Long gno) {

    }

    @Override
    public void modify(GuestbookDTO dto) {

    }

}