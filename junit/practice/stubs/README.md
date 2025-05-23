**stub이란?**
> 실제 혹은 아직 구현되지 않은 코드의 동작을 가장하기 위한 장치이다. 테스트(런타임) 시점에 실제 코드 대신 동작시키는 것이다.<br/>
> stub은 실제 구현 코드를 단순한 방식으로 간단하게 모사하는 것이다. 따라서 비즈니스 로직이 복잡하면 stub을 사용하는 것이 적절치 않다고 할 수도 있다. 

개발 중인 어플리케이션이 타사의 서비스와 HTTP 통신을 해야 될 경우, 개발 환경에서는 그런 서버 통신을 하는 것이 불가능하다.
실제 서비스와 통신이 없어도 소스 코드에 대한 테스트를 지속적으로 작성하고 실행 할 수 있도록 타사의 서비스를 모사할 필요가 있다.
이 경우 스텁과 모의 객체(mock)를 만들어 넣어서 테스트를 하면 되는데 스텁은 사전에 정의된 동작만 수행한다. 일반적으로 하드코딩한 값을 반환한다.
반면 모의 객체는 사전에 정의된 동작을 수행하는 것이 아닌 테스트 실행 중에 모의 객체가 수행할 행동을 기대할 수 있다.(이에 대한 자세한 설명은 mock 모듈 참고)

**stub을 사용하기 좋은 경우**

- 기존 시스템이 너무 복잡하여 수정이 어려울 때
- 소스 코드에 통제할 수 없는 외부 요인이 있을 때
  - 파일 시스템, 서버, 데이터베이스 등

**stub을 사용하기 어려운 경우**

- 실패의 원인을 밝힐 수 있는 정확한 에러 메시지를 확인하고자 할 때
- 코드 전체가 아닌 일부만 격리하여 테스트를 수행해야 할 때

위 상황에서는 stub보다는 mock을 사용하는 것이 선호된다.
