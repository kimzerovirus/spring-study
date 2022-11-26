## H2 Database

```shell
brew install h2
cd /opt/homebrew/opt/h2/bin - homebrew로 설치한 h2위치
chmod 755 h2 - h2 권한 변경
h2 - h2실행
```

- server모드는 파일에 저장하는 모드여서 jdbc:h2~/test로 실행 한번 해줘야 파일 생성됨 그 뒤로는 jdbc:h2:tcp://localhost~/test 로 접속하면 된다.

- embeded모드는 인메모리 휘발성 모드

## mysql

### MySQL을 데몬으로 실행하기 
(데몬, Daemon: 운영체제의 백그라운드 상태에서 계속 실행되는 프로그램)
```
brew services start mysql
```

### 서비스 재시작하기

```
brew services restart mysql
```
### 데몬으로 실행되고 있는 프로그램 목록 확인하기
```
brew services list
```

### 데몬 형태로 실행되고 있는 MySQL 종료하기
```
brew services stop mysql
```

### brew 로 설치된 프로그램 리스트 확인
```
brew list
```

## 그레이들 래퍼

```
├── gradle
│   └── wrapper 
│       ├── gradle-wrapper.jar
│       └── gradle-wrapper.properties
├── gradlew
└── gradle.bat
```

- gradlew : 리눅스 및 맥OS용 셸 스크립트
- gradlew.bat : 윈도우용 배치 스크립트
- gradle/wrapper/gradle-wrapper.jar : Wrapper JAR
- gradle/wrapper/gradle-wrapper.properties : 그레이들 설정 정보 프로퍼티 파일(버전 정보 등)

리눅스 계열 그레이들 실행 권한이 없을 경우

```
chmod 755 gradlew
```

chmode values

- r (Read) : 4
- w (Write) : 2
- x (Excute) : 1
- 세자리 값의 순서는 Owner Group Others 순이다.

## Batch

### Job

Job은 배치 처리 과정을 하나의 단위로 표현한 객체이다. Job 객체를 만드는 빌더는 여러개가 잇고 이를 통합처리하는 JobBuilderFactory가 있다.
