### Log Level
1. TRACE - 가장 세부적인 수준의 로그로 코드의 실행 경로를 추적할 때
2. DEBUG - 디버깅 목적, 개발 과정에서 코드의 상태나 흐름을 이해하기 위한 용도
3. INFO - 시스템 운영상에서 중요한 이벤트나 상태 변화 등의 정보성 로그
4. WARN - 경고성 로그, 잠재적 문제가 될 여지가 있는 상황이지만, 시스템 운영상에는 즉각적인 영향을 주지 않을 떄
5. ERROR - 문제가 발생하여 복구해야하거나 추적해야할 때
    - 개발자의 직접적인 개입이 필요할 정도일 때 사용하는게 옳을 것 같다.
6. FATAL - 시스템적으로 심각한 에러가 발생하여 어플리케이션 작동이 불가능할 때

```shell
docker compose -p log-pipeline up -d
```

[logstash config example](https://github.com/elastic/logstash/tree/main/config)


**TODO**
- AOP로 로그 찍기
- Logging Interceptor 추가해보기