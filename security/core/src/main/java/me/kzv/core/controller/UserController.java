package me.kzv.core.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.kzv.core.domain.Account;
import me.kzv.core.domain.AccountDto;
import me.kzv.core.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Log4j2
@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/mypage")
    public String myPage() throws Exception {
        return "user/mypage";
    }

    // 회원가입 페이지
    @GetMapping("/users")
    public String createUser() {
        return "user/login/register";
    }

    @PostMapping("/users")
    public String createUser(AccountDto dto) {

        ModelMapper modelMapper = new ModelMapper();
        Account account = modelMapper.map(dto, Account.class);
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        account.setRole("USER");
        userService.createUser(account);

        return "redirect:/";
    }

    @GetMapping("/messages")
    public String messages() {
        return "messages";
    }

}
