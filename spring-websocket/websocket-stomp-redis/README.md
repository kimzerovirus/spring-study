## STOMP
웹소켓 프로토콜은 두 가지(텍스트, 바이너리) 유형의 메시지를 정의하고 있지만 메시지의 내용까지는 정의하고 있지 않는다. 
Stomp는 웹소켓에서 메시징 전송을 효율적으로 하기 위해 나온 프로토콜이다.
pub/sub 구조로 메시지를 발송하고, 메시지를 수신하여 처리하는 부분이 확실하므로 개발자 입장에서 명확하게 이를 인지하고 개발할 수 있다는 장점이 있다.
또한 통신 메시지의 헤더에 값을 세팅할 수 있어, 헤더 값을 기반으로 인증 처리 등을 구현하는 것도 가능하다.

- 채팅방을 생성한다 = pub/sub 구현을 위한 Topic이 생성된다.
- 채팅방에 입장한다 = Topic을 구독한다.
- 채팅방에서 메시지를 보내고 받는다 = 해당 Topic으로 메시지를 발송하거나(pub) 메시지를 받는다(sub) // 메시지 브로커 역할

Stomp는 기본적으로 In-Memory Message Broker를 사용한다.
이로인해 생기는 문제를 해결하기 위해 RabbitMQ, Kafka, Active MQ와 같은 외부 브로커를 사용함으로서 문제를 해결 가능하다.

https://www.daddyprogrammer.org/post/4691/spring-websocket-chatting-server-stomp-server/

## Sock JS Fallback
websocket 프로토콜을 지원하지 않는 경우 polling 방식을 이용해 연결을 유지시키는 라이브러리

<br/>
stomp -> pub/sub <br/>
sock JS -> websocket, polling 등 프로토콜 지원