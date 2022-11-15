*BACKEND*  (SPRING BOOT)

- [x] Spring Data JPA
- [x] Spring Security
- [ ] OAuth2 Client
- [ ] Redis
- [ ] Spring Boot Batch

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

## Simple-board 엔티티 관계도

### Board

| board_idx (PK)    | 게시글 인덱스 |
| ----------------- | ------------- |
| title             | 제목          |
| sub_title         | 부제목        |
| content           | 내용          |
| board_type        | 게시판 타입   |
| created_date      | 생성 날짜     |
| modified_date     | 수정 날짜     |
| **user_idx (FK)** | 회원 인덱스   |

### User

| user_idx (PK) | 회원 인덱스   |
| ------------- | ------------- |
| name          | 회원 이름     |
| password      | 회원 패스워드 |
| email         | 회원 이메일   |
| created_date  | 생성 날짜     |
| modified_date | 수정 날짜     |

## Batch

### Job

Job은 배치 처리 과정을 하나의 단위로 표현한 객체이다. Job 객체를 만드는 빌더는 여러개가 잇고 이를 통합처리하는 JobBuilderFactory가 있다.
