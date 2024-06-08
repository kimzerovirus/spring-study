### vault
하시코프에서 개발한 시크릿 키 관리 애플리케이션이다.
하시코프의 제품으로는 테라폼, 콘술 등이 있다.

## spring cloud config
dev, prod
- private git repository
- secure vault
- secure file storage

```
    #server
    implementation 'org.springframework.cloud:spring-cloud-config-server'

    # client
    implementation 'org.springframework.cloud:spring-cloud-starter-config'
    implementation 'org.springframework.cloud:spring-cloud-starter-bootstrap' # config에서 분리되었다.
```
bootstrap.yml 파일에 설정된 값이 application.yml 보다 우선 순위를 갖나봄
따라서 spring-cloud-config를 사용할거면 bootstrap.yml이 우선 순위를 갖게해주자

### 변경 configuration 적용 방법

- 서버 재기동
- Actuator refresh
- spring cloud bus