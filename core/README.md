# core

## 역할과 구현을 분리
- 자바의 다형성을 이용
    - 역할 = 인터페이스
    - 구현 = 해당 인터페이스를 구현한 클래스
- 변경에 대해 유여한게 대처 가능
    - 클라이언트는 대상의 역할(인터페이스)만 알면 된다.
    - 클라이언트는 구현 대상의 내부 구조를 몰라도 된다.
    - 클라이언트는 구현 대상의 내부 구조가 변경 되어도 영향을 받지 않는다.

## SOLID
- SRP (Single responsibility principle) : 단일 책임 원칙
    - 한 클래스는 하나의 책임만 가져야 한다.

- OCP (Open/Closed principle) : 개방-폐쇄 원칙
    - 소프트웨어는 확장에는 열려 있어야 하지만 변경에는 닫혀 있어야 한다.
    - 다형성 활용
    - 인터페이스를 구현하는 클래스를 새로 하나 만들어서 새로운 기능 구현
    - 소프트웨어 요소를 새롭게 확장해도 사용 영역의 변경은 닫혀 있다.

- LCP (Liskov substitution principle) : 리스코프 치환 원칙
    - 프로그램 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다.
    - 다형성에서 하위 클래스는 인터페이스 규약을 다 지켜야 한다는 것으로 다형성을 지원하기 위한 원칙, 인터페이스를 구현한 구현체는 믿고 사용하려면, 이 원칙이 필요하다.

- ISP (Interface segregation principle) : 인터페이스 분리 원칙
    - 특정 클라이언트를 위한 인터페이스 여러개가 범용 인터페이스 한 개 보다 낫다.

- DIP (Dependency inversion principle) : 의존관계 역전 원칙
    - 추상화 > 구체화 (클라이언트가 구현클래스를 바라보기 보다는 인터페이스를 바라보는게 좋음)
    - 역할에 의존하라는 의미 (이렇게 해야 유연하게 구현체를 변경하기가 쉬워진다)

## 스프링?
- 스프링은 다음 기술을 통해 다형성과 OCP, DIP를 가능하게 해준다.
    - DI (Dependency Injection) : 의존관계, 의존성 주입
    - DI 컨테이너 제공

- 관심사의 분리 : 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리됨

## IOC : Inversion of Control
기존 프로그램들은 구현 객체가 직접 프로그램을 제어했었다. 반면 `AppConfig`를 사용하였을 때는 구현 객체는 자신의 로직을 실행하는 역할만 담당하고 이러한 역할을 직접 실행시키는 제어의 영역은 `AppConfig`가 담당하게 된다.
따라서 프로그램의 제어권을 구현하는 객체가 직접 관리하는게 아닌 외부에서 관리하는 것을 제어의 역전이라고 한다.

## 스프링 컨테이너
```
ApplicationContext ac = new AnnotationConfigApplication(AppConfig.class);
```
- 의존관계 주입을 대신 객체를 생성하고 관리하면서 의존관계를 연결해주는 것 (IOC 컨테이너 또는 DI 컨테이너)
- `ApplicationContext`를 스프링 컨테이라고 하며 인터페이스이다.
- `ApplicationContext` 인터페이스의 구현체 `AnnotationConfigApplication`
- 이렇게 자바로 설정하는게 일반적이지만 xml로도 설정 가능하다.

## 싱글톤 패턴
- 클래스의 인스턴스가 **1**개만 생성되도록 보장하는 디자인 패턴이다.
- private 생성자를 사용하여 new 키워드를 통해 인스턴스를 계속 만드는 것을 방지한다.
- 싱글톤 패턴 전략
    1. static 영역에 객체 instacne를 미리 하나 생성해서 올려둔다.
    2. 이 객체 인스턴스가 필요하면 `new` 키워드를 통한 생성이 아닌 오직 `getInstance` 메서드를 통해서 조회한다. (이 메서드를 호출하면 같은 인스턴스를 반환한다.)
    3. 인스턴스가 1개만 존재해야 하므로, 생성자를 `private` 레벨로 설정한다.

## 싱글톤 방식의 주의점
- 상태를 유지하게 설계하면 안된다!
- 무상태(stateless)로 설계해야 한다!
    - 특정 클라이언트에 의존적인 필드가 존재하면 안된다.
    - 특정 클라이언트가 값을 변경할 수 있는 필드가 존재하면 안된다.
    - 필드 대신에 자바에서 공유되지 않는 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
- 스프링 빈의 필드에 공유 값을 설정하면 큰 장애가 발생할 수 있다!!!

## 컴포넌트 스캔
 스프링 부트를 사용하게 되면 `@SpringBootApplication` 어노테이션 안에 `@ComponentScan` 이 들어있고 이 클래스가 프로젝트의 최상단에 위치하므로 `@ComponentScan` 을 만들지 않아도 자동으로 등록되게 되는것이다.
- `@Component` : 컴포넌트 스캔에서 사용
- `@Controller` : 스프링 MVC 컨트롤러에서 사용 (웹과 관련된 부분)
- `@Service` : 스프링 비즈니스 로직에서 사용 (웹 기술에 종속되지 않는 순수한 로직으로 나누면 좋다)
- `@Repository` : 스프링 테이터 접근 계층에서 사용
- `@Configuration` : 스프링 설정 정보에서 사용

## 의존관계 주입 방법
- 생성자 주입
- 수정자 주입(setter 주입)
- 필드 주입
- 일반 메서드 주입

#### 생성자 주입
- 생성자 호출시점에 딱 1한번만 호출된다.
- **`불변`** **`필수`** 의존관계에 사용
- 생성자가 하나만 있을 경우 `@Autowired` 를 생략해도 자동 주입 된다.
```
@Service
public class CustomService {
    private final CustomRepository customRepository;
 
    @Autowired // 생성자가 하나이므로 생략 가능
    public CustomService(CustomRepository customRepository){
        this.CustomRepository = customRepository;
    }
}
```

#### 수정자 주입
- `setter` (필드의 값을 변경(수정) 할 수 있는 메서드)를 이용한 의존관계 주입방법
- **`선택`** **`변경`** 가능성이 있는 의존관계에 사용
- 자바빈 프로퍼티 규약

```
@Service
public class CustomService {
    private CustomRepository customRepository;

    @Autowired(required = false) // required 선택적으로 주입 가능
    public void setCustomService(CustomRepository, customRepository){
        this.CustomRepository = customRepository;
    }
}
```

#### 필드 주입
- 가장 간결하지만 몇가지 문제점이 있다.
    - 외부에서 변경이 불가능해서 테스트 하기가 힘들다.
    - DI 프레임워크가 없으면 아무것도 할 수가 없댜.
    - 순수 자바 환경에서 바로 사용시 NPE -> setter 주입으로 넣어줘야 그나마 테스트가 가능하다.
- 사용해도 문제가 안 생기는 경우
    - 어플리케이션과 상관없는 테스트 코드를 짤 때
    - 스프링 안에서만 돌아가는 Config 설정
```
@Service
public class CustomService {
    @Autowired private CustomRepository customRepository;
}
```

#### 일반 메서드 주입
- 한번에 여러 필드를 주입 받을 수 있으나 잘 사용하지는 않는다.
```
@Service
public class CustomService {
    private final CustomRepository customRepository;
 
    @Autowired
    public void init(CustomRepository customRepository){
        this.CustomRepository = customRepository;
    }
} 
``` 

## 스프링 빈 라이프 사이클
1. 스프링 컨테이너 생성
2. 스프링 빈 생성
3. 의존관계 주입
4. 초기화 콜백 : 빈 생성 후, 빈의 의존관계 주입이 완료된 후 호출
5. 로직 ~
6. 소멸전 콜백 : 빈이 소멸 되기 직전에 호출
7. 스프링 종료 

## 빈 스코프
스프링 빈은 스프링 컨테이너의 시작과 함께 생성되어서 스프링 컨테이너가 종료될 때 까지 유지 되는데 이는 스프링 빈이 기본적으로 싱글톤 스코프로 생성되었기 때문이다. 따라서 이 빈 스코프는 이러한 빈이 존재할 수 있는 범위를 나타낸다.
- *싱글톤 스코프* : 스프링의 기본 스코프로 스프링 컨테이너의 시작 ~ 종료까지 유지되는 스코프이다.
- *프로토타입 스코프* : 스프링 컨테이너는 프로토타입 빈의 생성과 의존관계 주입까지만 관여하고 더는 관리하지 않는 짧은 범위의 스코프이다.
- *웹 관련 스코프*
    - `request` : 웹 요청이 들어오고 나갈때 까지 유지되는 스코프
    - `session` : 웹 세션의 생성 ~ 종료까지 유지되는 스코프
    - `application` : 서블릿 컨텍스트와 같은 범위로 유지되는 스코프