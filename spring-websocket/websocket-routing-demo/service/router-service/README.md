- ExternalWebSocketClient:
   - 외부 웹소켓 서버와의 연결을 관리한다.
   - connect() 메서드를 통해 외부 서버에 연결하고, 연결 성공, 메시지 수신, 연결 종료 등의 이벤트에 대한 콜백 핸들러를 정의한다.
   - sendMessage() 메서드를 통해 외부 서버로 메시지를 전송한다.
   - disconnect() 메서드를 통해 외부 서버와의 연결을 종료한다.
   - sessions 맵을 사용하여 활성 외부 웹소켓 세션을 관리한다.
   - TextMessageHandler 인터페이스를 통해 연결 이벤트, 메시지 수신, 연결 종료, 에러 발생 시의 로직을 외부에서 정의할 수 있다.

- InternalWebSocketHandler:
  - 프론트엔드와 웹소켓 통신을 담당하는 핸들러
  - internalSessions 맵을 사용하여 연결된 내부 웹소켓 세션을 관리한다.
  - sessionMapping 맵을 사용하여 내부 웹소켓 세션 ID와 외부 웹소켓 세션 ID를 매핑한다.
  - afterConnectionEstablished(): 프론트엔드와 연결이 수립되면, 연결 URL에서 externalId 쿼리 파라미터를 추출하여 해당 외부 세션이 활성화되어 있는지 확인하고 매핑한다.
  - handleTextMessage(): 프론트엔드로부터 메시지를 받으면, 해당 내부 세션 ID에 매핑된 외부 세션 ID를 찾아 외부 웹소켓 서버로 메시지를 전송한다.
  - afterConnectionClosed(): 프론트엔드와의 연결이 끊어지면, 내부 세션과 매핑 정보를 제거한다.
  - getInternalSession() 및 removeInternalSession(): 외부 세션 ID를 기반으로 내부 세션을 찾거나 제거하는 유틸리티 메서드

