package me.kzv.olle.account;

import lombok.RequiredArgsConstructor;
import me.kzv.olle.domain.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
/*
    boot 2.3 이후 따로 dependency 추가해야함
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-validation</artifactId>
    </dependency>
*/

@RequiredArgsConstructor
@Controller
public class AccountController {

    private final SignUpFormValidator signUpFormValidator;
    private final AccountService accountService;

    @InitBinder("signUpForm")
    public void initBinder(WebDataBinder data) {
        // 객체명의 카멜케이스로 바인딩되어 데이터가 들어올때 이걸 실행해줌
        data.addValidators(signUpFormValidator);
    }


    @GetMapping("/sign-up")
    public String signUpForm(Model model) {
//        model.addAttribute("signUpForm", new SignUpForm()); // 클래스 이름의 카멜케이스를 name으로 사용하는 경우 생략 가능
        model.addAttribute(new SignUpForm());
        return "account/sign-up";
    }

    @PostMapping("/sign-up")
    public String signUpSubmit(@Valid SignUpForm signUpForm, Errors errors){
        if (errors.hasErrors()) {
            return "account/sign-up";
        }

        // 이하 initBinder로 처리
        // signUpFormValidator.validate(signUpForm, errors);
        // if (errors.hasErrors()) {
        //     return "account/sign-up";
        // }

        accountService.processNewAccount(signUpForm);

        // TODO 회원 가입 처리
        return "redirect:/";
    }

}
