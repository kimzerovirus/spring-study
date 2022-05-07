package me.kzv.olle.account;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@RequiredArgsConstructor
@Component
public class SignUpFormValidator implements Validator {

    private final AccountRepository accountRepository;

    /*
        public SignUpFormValidator(AccountRepository accountRepository){
            this.accountRepository = accountRepository;
        }

        private final로 만든 변수는
        요런 생성자를 만들어주는 @RequiredArgsConstructor <- 스프링 빈을 주입받기 위해 필요
    */

    @Override
    public boolean supports(Class<?> clazz) {
        // 어떤 타입의 인스턴스를 검증할 것인가? = SignUpForm
        return clazz.isAssignableFrom(SignUpForm.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        // 이메일 중복 여부를 검사한다
        SignUpForm signUpForm = (SignUpForm) target;
        if (accountRepository.existsByEmail(signUpForm.getEmail())) {
            errors.rejectValue("email", "invalid.email", new Object[]{signUpForm.getEmail()}, "이미 사용중인 이메일입니다.");
        }

        if (accountRepository.existsByNickname(signUpForm.getNickname())) {
            errors.rejectValue("nickname", "invalid.nickname", new Object[]{signUpForm.getNickname()}, "이미 사용중인 닉네임입니다.");
        }
    }
}
