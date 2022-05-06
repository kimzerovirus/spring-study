package me.kzv.olle.account;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AccountController {

    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
//        model.addAttribute("signUpForm", new SignUpForm()); // 클래스 이름의 카멜케이스를 name으로 사용하는 경우 생략 가능
        model.addAttribute(new SignUpForm());
        return "account/sign-up";
    }
}
