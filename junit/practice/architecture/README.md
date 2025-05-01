# JUnit Architecture
> 모놀리식이었던 JUnit4에서 JUnit5로 바뀌며 모듈화가 되었다.
> 전체 테스트 프레임워크를 불러올 필요 없이 필요한 특정 모듈만 부르면 시간과 메모리를 절약할 수 있다.

JUnit5는 `JUnit Vintage`를 통해 기존 레거시 프로젝트 즉, 이전 JUnit4 코드와 함께 동작할 수 있도록 설계되어 있다.

## JUnit4 Architecture
- 모놀리식 아키텍처 : JUnit4의 모든 기능은 jar 파일 하나에 있다. - 2006년 출시함..
- runner : JUnit 테스트의 실행을 담당, runner는 리플렉션을 통해서만 확장할 수 있다. 하지만 이는 캡슐화를 저행하는 요소 중 하나이다.
- rule : 테스트 메소드를 가로채는 인터셉터 역할을 한다.

**JUnit5 환경에서 JUnit4 같이 사용하기**
<br/>
@RunWith는 JUnit 4에서 사용하는 어노테이션. JUnit 5는 기본적으로 이를 인식하지 못하며, junit-vintage-engine이 이를 브릿지 역할로 실행할 수 있도록 해줌.
하지만 junit-vintage-engine만으로는 부족하고, JUnit 4 자체도 의존성으로 필요함.