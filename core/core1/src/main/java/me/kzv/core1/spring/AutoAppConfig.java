package me.kzv.core1.spring;

import me.kzv.core1.spring.member.MemberRepository;
import me.kzv.core1.spring.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        basePackages = "me.kzv.core1.spring", // 탐색할 위치 지정 , 지정하지 않으면 이 패키지를 기준으로 탐색 따라서 프로젝트 최상단에 놓으면 해당 프로젝트를 다 스캔하게 된다.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    /** 빈 이름이 겹칠 경우 수동 등록 빈이 자동 등록 빈 보다 우선권을 가진다 */ // -> 다만 이렇게 겹쳐지는 경우에는 의도하지 않은 버그가 발생할 수 있므로 상황이 나오지 않게 만들자 ... 어차피 스프링 실행시 경고 문구가 나온다
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
/**
 *     스프링 부트를 사용하게 되면 @SpringBootApplication 어노테이션 안에 ComponentScan 이 들어있고 이 클래스가 프로젝트의 최상단에 위치하므로 ComponentScan 을 만들지 않아도 자동으로 등록되게 되는것이다.
 *
 * @Component : 컴포넌트 스캔에서 사용
 * @Controller : 스프링 MVC 컨트롤러에서 사용
 * @Service : 스프링 비즈니스 로직에서 사용
 * @Repository : 스프링 테이터 접근 계층에서 사용
 * @Configuration : 스프링 설정 정보에서 사용
 *
 * 어노테이션은 자바 언어에서 지원하는게 아니라 스프링에서 지원하는 기능이다.
 */