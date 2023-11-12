# jenkins

```shell
docker pull jenkins/jenkins:latest # default jdk8
docker pull jenkins/jenkins:jdk11

docker images

docker run -d -p 8180:8080 -v /var/jenkins_home --name jenkins -u root jenkins/jenkins:jdk11

docker ps -a

docker rm [컨테이너 ID]

docker logs jenkins
```

### options
- `d` : detached mode 흔히 말하는 백그라운드 모드
- `p` : 호스트와 컨테이너의 포트를 연결 (포워딩)
- `v` : 호스트와 컨테이너의 디렉토리를 연결 (마운트)
– `name` : 컨테이너 이름 설정
- `u` : 실행할 사용자 지정

jenkins
```shell
http://localhost:8180

ID : kimzerovirus
PW : kk15411541
```

### Cron 표현식
- 분, 시, 일, 월, 요일 순서로 나눠져 있으며, 각각의 위치에 숫자, 별표(*), 범위(-), 리스트(,), 단계(/)
- 1분마다 실행되어야 하는 cron 작업은 위와 같이 모든 위치를 *로 설정하여 표현할 수 있음

### Jenkins 배치 잡으로 스케쥴링 돌리기
1. dashboard -> 새로운 item
2. folder 설정하거나 free style project 클릭 후
3. configure 진입하면
    - 빌드 유발 : build periodically 선택 후 크론식 입력
    - Build Steps : Execute shell 선택 후 api 호출
```shell
curl -X POST http://192.168.1.3:8080/test
```
수정 시에는 다시 구성 탭 들어가서 수정하면 됨 
