package me.kzv.board.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.kzv.board.dto.ReplyDTO;
import me.kzv.board.entity.Board;
import me.kzv.board.entity.Reply;
import me.kzv.board.repository.ReplyRepository;
import me.kzv.board.service.ReplyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReplyServiceImple implements ReplyService {

    private final ReplyRepository replyRepository;

    @Override
    public Long register(ReplyDTO dto){
        Reply reply = dtoToEntity(dto);

        replyRepository.save(reply);

        return reply.getRno();
    }

    @Override
    public List<ReplyDTO> getList(Long bno){
        List<Reply> result = replyRepository.getRepliesByBoardOrderByRno(Board.builder().bno(bno).build());

        return result.stream().map(reply -> entityToDTO(reply)).collect(Collectors.toList()); // collect(Collectors.toList()) .toSet() 스트림을 List 또는 Set객체로 변환
    }

    @Override
    public void modify(ReplyDTO dto) {
        Reply reply = dtoToEntity(dto);

        replyRepository.save(reply);
    }

    @Override
    public void remove(Long rno){
        replyRepository.deleteById(rno);
    }
}
