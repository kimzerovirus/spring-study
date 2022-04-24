package me.kzv.board.repository;

import me.kzv.board.entity.Board;
import me.kzv.board.entity.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // 게시물 삭제
    @Modifying
    @Query("delete from Reply r where r.board.bno =:bno ")
    void deleteByBno(Long bno);

    //게시물로 댓글 목록 가져오기
    List<Reply> getRepliesByBoardOrderByRno(Board board);
}

/*
    게시물을 삭제하려면 FK로 게시물을 참조하고 있는 reply 테이블까지 같이 삭제 해야된다.
    1) 해당 게시물의 모든 댓글을 삭제
    2) 해당 게시물 삭제
    2가지 작업이 동시에 실행되어야 한다. <- 트랜잭션으로 처리
 */
