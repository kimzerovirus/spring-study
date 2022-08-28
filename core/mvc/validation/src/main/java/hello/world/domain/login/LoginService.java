package hello.world.domain.login;

import hello.world.domain.member.Member;
import hello.world.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * @return null 로그인 실패
     */
    public Member login(String loginId, String password) {
        /*
        Optional<Member> findMember = memberRepository.findByLoginId(loginId);
        Member member = findMember.get();
        if (member.getPassword().equals(password)) {
            return member;
        } else {
            return null;
        }
        */

        return memberRepository.findByLoginId(loginId)
                .filter(m -> m.getPassword().equals(password))
                .orElse(null);
    }
}
