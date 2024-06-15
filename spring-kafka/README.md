# Kafka
> bitnami kafka 3.7

- **broker** : 카프카 어플리케이션이 설치된 서버 또는 노드를 의미함.
- **producer** : 카프카로 메시지를 보내는 역할을 하는 클라이언트의 총칭.
- **consumer** : 카프카에서 메시지를 가져가는 역할을 하는 클라이언트의 총칭.
- **topic** : 카프카는 메시지 피드들을 토픽으로 구분하고, 각 토픽의 이름은 카프카 내에서 고유해야 한다. 따라서 토픽 이름을 잘 알아 볼 수 있게 설정해야 함.
- **partition** : 고성능을 얻기 위해 하나의 토픽을 여러개로 나눠 병렬 처리가 가능하게 만든것. 하나를 여러개로 나누어 놓았으므로 분산 처리가 가능해지며, 파티션 수 만큼 컨슈머를 연결할 수 있음.
- **segment** : 프로듀서가 전송한 실제 메시지가 브로커의 로컬 디스크에 저장되는 데이터(파일)를 의미함.
- **message / record** : 프로듀서가 브로커로 전송하거나 컨슈머가 읽어가는 데이터를 말함.

## Kafka command

##### Run Kafka

```sh
docker compose -p spring-kafka -p -f ./docker/docker-compose up -d
```

##### Create Topic

```sh
bin/kafka-topics.sh --create --topic topic1 --bootstrap-server=localhost:9092
```

##### Produce Topic

```sh
bin/kafka-console-producer.sh --topic topic1 --bootstrap-server=localhost:9092

bin/kafka-console-consumer.sh --bootstrap-server=localhost:9092 --topic topic1 --group test-group --from-beginning
```

카프카는 그룹명에 기반하여 오프셋을 관리한다. 따라서 오프셋을 확인하기 위해서는 반드시 그룹명을 지정해 줘야 한다.

##### Check current offset

```sh
bin/kafka-consumer-groups.sh --bootstrap-server=localhost:9092 --group test-group --describe
```
