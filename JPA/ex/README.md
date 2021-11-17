# Spring Data JPA

### 1. 스프링 ~ DB 흐름

>
> - Spring Data JPA → Hiernate → JDBC → DB
>
>

### 2. @Entity

>
> - 해당 클래스가 엔티티를 위한 클래스임을 명시하고, 해당 클래스의 인스턴스들이 JPA로 관리되는 객체임을 의미한다.
>

### 3. @Table(name="테이블명")

> - DB에서 어떤 테이블인지 표기

### 4. @ToString

> - 클래스 상단에 써주면 변수 값들을 리턴해주는 toString메서드를 자동 생성해준다.

### 5. @Id

> - Primary Key

### 6. @GeneratedValue(strategy = GenerationType.IDENTITY)

> - 번호를 자동으로 생성
> - 오라클DB는 번호를 생성하기 위한 별도의 테이블을 생성해야한다.
> - MySQL계열은 auto_increment를 기본으로 사용하기 때문에 새로운 레코드가 기록될 때 다른 번호로 저장된다.
> - AUTO : JPA(스프링의 HIBERNATE)가 생성 방식을 결정한다.
> - IDENTITY : MySQL계열은 auto_increment방식을 사용한다.
> - SEQUENCE : DB의 시퀀스를 이요하여 키를 생성한다.( @SequenceGenerator와 같이 사용)
> - TABLE : 키 생성 전용 테이블을 생성한다. (@TableGenerator와 같이 사용)

### 7. @Builder 패턴

> - @Builder패턴 사용시 @AllArgsConstructor와 @NoArgsConstructor를 함께 처리해줘야 컴파일 에러가 발생하지 않는다.

### 8. @Column(columnDefinition = "varchar(200) default 'hello'")

### 9. JpaRepository

> - JpaRepository는 인터페이스로 이것을 레포지토리(저장소)에서 상속만 해주면 된다.
> - `public interface XXXRepository extends JpaRepository<엔티티클래스, ID타입>`
> - Spring Data JPA는 위의 상속 선언만으로 자동으로 스프링에 빈으로 등록된다.
