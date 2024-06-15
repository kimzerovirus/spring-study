package me.kzv.springkafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kzv.springkafka.domain.Member;
import me.kzv.springkafka.domain.MemberRepository;
import me.kzv.springkafka.dto.JoinMemberDto;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final KafkaTemplate<String, String> joinMemberKafkaTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);

    private static final String JOIN_COMPLETE_TOPIC_NAME = "member.join.complete.v1";

    @Transactional
    public Member join(JoinMemberDto dto) {
        Member savedMember = memberRepository.save(dto.toMember());
        sendToKafka(savedMember);
        return savedMember;
    }

    private void sendToKafka(Member member) {
        try {
            String jsonString = objectMapper.writeValueAsString(member);
            joinMemberKafkaTemplate.send(JOIN_COMPLETE_TOPIC_NAME, jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
