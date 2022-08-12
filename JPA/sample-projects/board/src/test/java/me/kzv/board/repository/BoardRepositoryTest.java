package me.kzv.board.repository;

import me.kzv.board.entity.Board;
import me.kzv.board.entity.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertBoard() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Member member = Member.builder()
                    .email("user" + i + "@naver.com")
                    .build();

            Board board = Board.builder()
                    .title("Sample Title" + i)
                    .content("Sample Content" + i)
                    .writer(member)
                    .build();

            boardRepository.save(board);
        });
    }

}