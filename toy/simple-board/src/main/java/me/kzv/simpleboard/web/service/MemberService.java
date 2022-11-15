package me.kzv.simpleboard.web.service;

import lombok.RequiredArgsConstructor;
import me.kzv.simpleboard.web.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

}
