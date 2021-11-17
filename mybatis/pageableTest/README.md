# MyBatis에서 Pageable을 사용하여 페이징 처리하기


<br>

## 📒 목차

- [프로젝트 설명](#ch1)
- [기술스택](#ch2)


<br>



## 📑 프로젝트 설명 <a id="ch1"></a>

**Pageable**은 SpringDataJpa에서 제공하는 것으로 보통 MyBatis가 아닌 JPA환경에서 사용한다. 하지만 이번 프로젝트에서는 MyBatis에서 pageable 객체를 활용하여 rest통신을
해보려고 한다.
>
>  - 프로젝트 설치 방법
> <br/>&nbsp;&nbsp;1. 프로젝트 실행 후 H2 DB에 접속하여 ```resources > SQL > data.sql```에 있는 테이블 생성문을 작성한다.
> <br/>&nbsp;&nbsp;2. 프론트 의존성 패키지들은 ```front디렉토리```를 루트로 ```npm i```를 실행하여 설치를 진행하면 된다. 이 후 ```npm run serve```를 통하여 프론트 서버를 실행하면 된다.



<br>


## 📚 기술스택 <a id="ch2"></a>

| 분야           | 사용 기술                       | 비고 |
| -------------- | ------------------------------- | ---- |
| FrontEnd       | Vue, javascript, axios               |
| BackEnd        | SpringBoot, Java, MyBatis, jdbc                         |
| Database       | H2 Database              |
