## 모의 객체

**모의 객체란?**
> 일종의 격리된 상태에서 해당 부분만 테스트 할 수 있게 해준다.
> 테스트 대상 메서드가 다른 객체를 호출해 발생하는 문제를 모의 객체가 해결해주므로 테스트 하고자 하는 메서드에만 집중할 수 있다.

**스텁과의 차이**
스텁은 사전에 정의되어 런타임에 따라 달라지지 않고 해당 동작만 수행한다.
반면 모의 객체는 사전에 정의된 동작은 존재하지 않으며, 대신 테스트를 실행하는 중에 모의 객체가 수행할 행동을 기대할 수 있다.

<br/>

**모의 객체 테스트 과정**
모의 객체 초기화 > 기대 설정 > 테스트 실행 > 단언문 검증

<br/>
<br/>
<br/>

모의 객체를 작성할 때 가장 중요한 규칙은 모의 객체가 비즈니스 로직을 가져서는 안된다는 것이다.

## mock 라이브러리

**Mock**
```java
@ExtendWith(MockitoExtension.class)
class WebSocketTest {

    @Mock
    MyWebSocketHandler webSocketHandler;

    @Mock
    private UserRepository userRepository;

    @InjectMocks // 유저 서비스 안에서 사용 중인 유저 리포지토리 자리에 모의 객체를 주입한다.
    private UserService userService;
}
```
SpringContext 없이 실행되는거라 빠르다. (빠른 단위 테스트 가능)

```java
@SpringBootTest
class WebSocketTest {

    @MockBean
    MyWebSocketHandler handler;
}
```
스프링에서 Mock 객체 일부만 사용하고 싶을 때 (스프링 어플리케이션의 전체 구성 테스트 가능, 일부만 mock 대체)

