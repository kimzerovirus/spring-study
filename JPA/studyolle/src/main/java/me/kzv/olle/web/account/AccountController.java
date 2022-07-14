package me.kzv.olle.web.account;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kzv.olle.web.account.dto.SignUpRequestDto;
import me.kzv.olle.web.account.validator.SignUpRequestDtoValidator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final SignUpRequestDtoValidator signUpRequestDtoValidator;
    private final AccountService accountService;
    private final AccountRepository accountRepository;

    @InitBinder("signUpRequestDto") // 객체명의 카멜케이스로 바인딩되어 데이터가 들어올때 이걸 실행해줌
    public void initBinder(WebDataBinder data) {
        data.addValidators(signUpRequestDtoValidator);
    }

    @PostMapping("/sign-up")
    public ResponseEntity signUpRequestDto(@Valid @RequestBody SignUpRequestDto signUpRequestDto) {
        log.info(signUpRequestDto.toString());

        Account account = accountService.processNewAccount(signUpRequestDto);
        accountService.login(account);

        return ResponseEntity.ok("회원 가입 성공");
    }


    /*
        어노테이션 생략시?
        어노테이션을 생략하고 간단하게 받을 수도 있다.
        대신 이경우에는 변수명과 동일한 파라미터값만 받을 수 있다.
        또 String, Long 타입은 @RequestParam 으로 취급하지만 이외에는 @ModelAttribute 로 취급한다.
     */
    @GetMapping("/check-email-token")
    public ResponseEntity checkEmailToken(String token, String email) {
        Optional<Account> isAccount = accountRepository.findByEmail(email);
        Account account = isAccount.get();

        if(!isAccount.isPresent()){
            // 이메일 에러
            return null;
        }

        if (!account.isValidToken(token)) {
            // 토큰 에러
            return null;
        }

        account.completeSignUp();
        accountRepository.save(account);

        return ResponseEntity.ok(accountRepository.count() + "/" + account.getNickname());
    }
}
