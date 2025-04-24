> Spring Event란? <br><br>
> 스프링 프레임워크를 사용할 때 내부에서 데이터를 전달하는 방법 중 하나이다.<br> 
> 이를 사용하면 각각의 코드의 관심사를 분리할 수 있다. <br>
> 스프링 이벤트 기능은 이벤트를 발생시키고(publish) 이벤트를 수신하는(subscribe)하는 로직을 분리해서 작성할 수 있다

유연한 확장력, 응집력을 갖추고 결합도를 낮출 수 있다.

##### Application Event Publisher
- publishEvent()

##### Application Event MultiCaster
- multicastEvent()

##### Application Event Listener

<br/>
<br/>
<br/>

**참고**<br/>
- https://www.baeldung.com/spring-events
- https://www.youtube.com/watch?v=2Hq9htCE0vA
- https://f-lab.kr/insight/spring-event-listener-20240522
- https://www.nextree.io/spring-event/