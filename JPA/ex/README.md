# Spring Data JPA

### 1. 스프링 ~ DB 흐름

>
> - Spring Data JPA → Hibernate → JDBC → DB
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
> - `AUTO` : JPA(스프링의 HIBERNATE)가 생성 방식을 결정한다.
> - `IDENTITY` : MySQL계열은 auto_increment방식을 사용한다. (MySQL, MariaDB, PostgreSQL, SQL Server, DB2)
> - `SEQUENCE` : DB의 시퀀스를 이용하여 키를 생성한다.( @SequenceGenerator와 같이 사용... PostgreSQL, DB2, H2 DB) ... 시퀀스는 유일한 값을 순서대로 생성하는 DB오브젝트이다.
> - `TABLE` : 키 생성 전용 테이블을 생성한다. (@TableGenerator와 같이 사용)
> - [@GeneratedValue의 옵션에 따른 문제점에 관한 글](https://hyeonic.tistory.com/m/196)

### 7. @Builder 패턴

> - @Builder패턴 사용시 @AllArgsConstructor와 @NoArgsConstructor를 함께 처리해줘야 컴파일 에러가 발생하지 않는다.

### 8. @Column(columnDefinition = "varchar(200) default 'hello'")

> - `name` : 필드와 맵핑할 테이블의 컬럼 이름을 지정한다.
> - `insertable` : 엔티티 저장시 선언된 필드도 같이 저장한다. 만약 false로 설정하면 데이터베이스에 저장되지 않으므로 읽기 전용일때 사용한다.
> - `updateable` : 엔티티 수정시 이 필드를 함께 수정한다. 위와 동일하지만 수정일 경우이다.
> - `table` : 지정한 필드를 다른 테이블에 맵핑한다.
> - `nullable` : NULL을 허용할지, 허용하지 않을지 결정한다.
> - `unique` : 유니크 제약조건을 걸 때 사용한다. 만약 2개 이상 걸고 싶다면 클래스 레벨에서 @Table.uniqueConstraints를 사용해야 한다.
> - `columnDefinition` : DB 컬럼 정보를 직접적으로 지정할 때 사용한다.
> - `length` : varchar의 길이를 조정하고 기본값으로 255가 입력된다.
> - `precsion` : BigInteger, BigDecimal 타입에서 사용하며, 소수점 포함 전체 자릿수를 의미한다.(double과 float타입은 적용X)
> - `scale` : BigInteger, BigDecimal 타입에서 사용하며, 소수의 자릿수를 의미한다.

### 9. JpaRepository

> - JpaRepository는 인터페이스로 이것을 레포지토리(저장소)에서 상속만 해주면 된다.
> - `public interface XXXRepository extends JpaRepository<엔티티클래스, ID타입>`
> - Spring Data JPA는 위의 상속 선언만으로 자동으로 스프링에 빈으로 등록된다.

### 10. JPA에서의 CRUD

> - `INSERT` : save(엔티티 객체)
> - `SELECT` : findById(키 타입), getOne(키 타입, deprecated )
> - `UPDATE` : save(엔티티 객체)
> - `DELETE` : deleteById(키 타입), delete(엔티티 객체)

 - insert와 update는 메서드가 동일하지만 JPA의 구현체가 메모리상에서 객체를 비교하여 새로운 객체면 insert작업을, 존재한다면 update 작업을 수행한다.

### 11. @RequiredArgsConstructor, @NoArgsConstructor와 @AllArgsConstructor
#### 11.1 @RequiredArgsConstructor
> - 선언된 모든 초기화 되지 않은 final필드 또는 @NonNull이 붙은 필드를 파라미터로 받는 생성자를 생성해 준다(final이 없는 필드는 생성x, 모든 멤버 변수를 초기화시키는 생성자).
#### 11.2 @NoArgsConstructor
> - 파라미터가 없는 기본 생성자를 생성해준다.
#### 11.3 @AllArgsConstructor
> - 모든 필드 값을 파라미터로 받는 생성자를 만들어준다.
- @AllArgsConstructor와 @NoArgsConstructor는 항상 같이 처리해야 컴파일 에러가 발생하지 않는다.
