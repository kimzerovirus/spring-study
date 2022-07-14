package me.kzv.demo.security.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.kzv.demo.web.domain.ClubMember;
import me.kzv.demo.web.domain.ClubMemberRole;
import me.kzv.demo.web.repository.ClubMemberRepository;
import me.kzv.demo.security.dto.ClubAuthMemberDto;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@RequiredArgsConstructor
@Service
public class CustomOAuthUserDetailsService extends DefaultOAuth2UserService {

    private final ClubMemberRepository repository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        // OAuth2UserRequest 객체를 통해 어떤 서비스로 로그인이 이루어졌는지 알아내고 전달된 값들을 Map<String, Object>형태로 추출할 수 있다.
        log.info("----------------");
        log.info("userRequest: " + userRequest);

        String clientName = userRequest.getClientRegistration().getClientName();

        log.info("clientName: " + clientName);
        log.info(userRequest.getAdditionalParameters());

        OAuth2User oAuth2User = super.loadUser(userRequest);

        oAuth2User.getAttributes().forEach((k, v) -> {
            log.info(k + ":" + v); // sub, pictgure, email, email_verified, EMAIL등이 출력된다.
        });

        String email = null;

        if (clientName.equals("Google")) {
            email = oAuth2User.getAttribute("email");
        }

        ClubMember member = saveSocialMember(email);

        ClubAuthMemberDto clubAuthMember = new ClubAuthMemberDto(
                member.getEmail(),
                member.getPassword(),
                true,
                member.getRoleSet().stream().map(
                        role -> new SimpleGrantedAuthority(("ROLE_" + role.name()))
                ).collect(Collectors.toList()),
                oAuth2User.getAttributes()
        );

        clubAuthMember.setName(member.getName());

        return clubAuthMember;
    }

    private ClubMember saveSocialMember(String email) {

        Optional<ClubMember> result = repository.findByEmail(email, true);

        if (result.isPresent()) {
            return result.get();
        }

        ClubMember clubMember = ClubMember.builder().
                email(email)
                .name(email)
                .password(passwordEncoder.encode("1111"))
                .fromSocial(true)
                .build();

        clubMember.addMemberRole(ClubMemberRole.USER);

        repository.save(clubMember);

        return clubMember;
    }

}
