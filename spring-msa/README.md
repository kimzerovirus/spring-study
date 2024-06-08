**학습에 사용한 기술**
- gateway: spring-cloud-gateway
- service discovery: netflix-eureka
- authorization: keycloak, oauth2
- cache: redis
- log pipeline: elasticsearch, logstash, spring-cloud-sleuth, zipkin

**multi module 설정시 이슈**
```
Task 'wrapper' not found in project
```
위와 같은 에러가 발생하는 이유는 root project gradle에서 subproject를 통해 공통으로 설정을 관리하고 있는데 개별 모듈별로 빌드를 시도해서 발생한 에러이다.
gradle task 패널에서 개별로 등록된 모듈들을 삭제하고 root 모듈 하나만 남기면 된다.

<br/>

**docker-compose**
```
docker compose -p spring-msa -f ./docker/docker-compose.yml up -d
```

# MSA

## 마이크로서비스 개발시 고려 사항

- 서비스 세분화
  - 서비스의 적정 책임 수준에 따라 서비스 분리 고려
    - 각 서비스 도메인의 책임과 중첩되지 않게 적절하게 서비스 분리 필요
    - 서비스를 너무 분해하여 복잡도를 높여 유지 관리 비용이 높아지는 문제가 발생할 수 있음
      - 별 다른 비즈니스 처리 로직이 없이 단순히 데이터 저장소에 엑세스만 하는 비용만 증가시키는 서비스가 될 수 있음
- 통신 프로토콜
  - 동기 프로토콜: XML, JSON 등 HTTP 기반 통신
  - 비동기 프로토콜: RabbitMQ, Apache Kafka, Amazon SQS 등 메시지 브로커 AMQP (Advanced Message Queuing Protocol)
- 인터페이스 설계
  - 서비스 엔드포인트를 클라이언트에 노출할 방식 고려
- 각 서비스 환경 구성 관리
- 이벤트 처리

## 마이크로서비스 라우팅
- 서비스 디스커버리
  - 서비스 물리적 위치를 추상화
  - 비정상 서비스 제거
  - netflix-eureka, etcd, consul, apache zookeeper 등
- 서비스 라우팅
  - 단일 엔드포인트, 진입점을 제공하므로 클라이언트는 수 많은 서비스의 엔드포인트를 알 필요가 없다.
  - 권한 부여, 인증 등 서비스 지입의 관문 역할
  - spring-cloud-gateway, ingress 등

## 마이크로서비스 보안
- 인증: 신원 확인
- 인가: 권한 부여
- 자격 증명 관리와 전파
  - 한 트랜잭션에서 여러 서비스를 호출할 때, 호출시 마다 계속해서 자격 증명을 제시하지 않는 기법
  - JWT, OAuth2

## 마이크로서비스 회복성

MSA에서는 하나의 문제가 연쇄적으로 다른 서비스에 문제가 전파될 수 있다. 따라서 이를 막아줄 클라이언트 회복성 패턴을 적용한다.

- 클라이언트 부하 분산 : 마이크로서비스의 여러 인스턴스에 대한 호출이 정상 인스턴스에 부하 분산 되도록 서비스 인스턴스 위치를 캐싱하는 기법
- circuit breaker 패턴 : 실패 또는 성능 문제가 있는 서비스 인스턴스를 계속 호출하지 않도록 하는 기법
- fallback 패턴 : 서비스 호출이 실패하면 다른 서비스 클라이언트가 작업을 대체하여 조치하는 기법
- bulkhead 패턴 : 한 서비스의 실패가 다른 서비스에 영향을 미치지 않도록 호출을 격리하는 기법