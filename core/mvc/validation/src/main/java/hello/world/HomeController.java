package hello.world;

import hello.world.domain.member.Member;
import hello.world.domain.member.MemberRepository;
import hello.world.web.SessionConst;
import hello.world.web.argumentresolver.Login;
import hello.world.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class HomeController {

    private final MemberRepository memberRepository;
    private final SessionManager sessionManager;

    //    @GetMapping("/")
    public String homeLoginV1(@CookieValue(name = "memberId", required = false) Long memberId, Model model) {
        if (memberId == null) {
            return "home";
        }
        // 로그인
        Member loginMember = memberRepository.findById(memberId);
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    //    @GetMapping("/")
    public String homeLoginV2(HttpServletRequest request, Model model) {

        // 세션 관리자에 저장된 회원 정보 조회
        Member loginMember = (Member) sessionManager.getSession(request);

        // 로그인
        if (loginMember == null) {
            return "home";
        }

        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    //    @GetMapping("/")
    public String homeLoginV3(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return "home";
        }
        Member loginMember = (Member) session.getAttribute(SessionConst.LOGIN_MEMBER);

        // 세션 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "home";
        }

        // 세션 유지되면 로그인홈으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

//    @GetMapping("/")
    public String homeLoginV3Spring(@SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) Member loginMember, Model model) {

        // 세션 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "home";
        }

        // 세션 유지되면 로그인홈으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }

    @GetMapping("/")
    public String homeLoginV3ArgumentResolver(@Login Member loginMember, Model model) {

        // 세션 회원 데이터가 없으면 home
        if (loginMember == null) {
            return "home";
        }

        // 세션 유지되면 로그인홈으로 이동
        model.addAttribute("member", loginMember);
        return "loginHome";
    }
}