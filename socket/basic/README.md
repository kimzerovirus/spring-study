클라이언트가 서버에 접속하면 개별의 WEBSOCKET SESSION을 가진다.
채팅방에 입장시 클라이언트들의 WEBSOCKET SESSION정보를 채팅방에 맵핑시켜서 보관하고 있으면 
서버에 전달된 메시지를 특정 방의 WEBSOCKET SESSION으로 보낼 수 있으므로 개별 채팅방이 구현 가능하다.  