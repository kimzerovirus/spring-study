# 스프링부트 JPA TDD개발

>
> 스프링에선 Bean을 주입받는 방식이 여러가지가 있다.<br/>
> 대표적으로 @Autowired, @Setter, 생성자를 통한 주입방식이 있는데, 이 프로젝트에서는 생성자를 통해 Bean을 주입하고자 한다. (@Autowired와 달리 순환참조 등의 에러를 방지 할 수 있다.)<br/>
> 또한 test driven development, 테스트 주도 개발 방식인 TDD를 적용하여 프로젝트를 진행한다.
>

|   분야        | 사용기술         | 비고 |
|--------------|-----------------|-----|
|  프레임워크    | SpringBoot 2.x  |         
|    ORM       | Spring Data Jpa |
|    라이브러리  | lombok          |
|    테스트     | junit4          |
|    템플릿     | thymeleaf, jQuery, BootStrap4      |
|  데이터베이스  | h2 Database     |

<br>

### 1. @Getter, @Setter
> - 선언된 모든 필드의 get메서드 생성
> - 선언된 모든 필드의 set메서드 생성

### 2. @Data
> - 클래스에 선언된 모든 private에 대해 @Getter와 @Setter를 적용

### 3. @RequiredArgsConstructor, @NoArgsConstructor와 @AllArgsConstructor
#### 3.1 @RequiredArgsConstructor
> - 선언된 모든 초기화 되지 않은 final필드 또는 @NonNull이 붙은 필드를 파라미터로 받는 생성자를 생성해 준다(final이 없는 필드는 생성x).
#### 3.2 @NoArgsConstructor
> - 파라미터가 없는 기본 생성자를 생성해준다.
#### 3.3 @RequiredArgsConstructor
> - 모든 필드 값을 파라미터로 받는 생성자를 만들어준다.

### 4. @Entity
> - DB의 테이블과 링크될 클래스임을 선언
> - 기본값으로 클래스(자바)의 카멜케이스 = 데이터베이스의 언더스코어 네이밍(언더바)과 매칭된다.

### 5. @Id
> - Primary Key, 즉 PK임을 의미함

### 6. @GeneratedValue
> - PK규칙을 나타낸다
> - 스프링부트 2.0의 경우 GenerationType.IDENTITY옵션을 추가해야 auto_increment사용가능

### 7. @Column
> - 테이블의 칼럼으로 선언하지 않아도 해당 클래스의 모든 필드는 칼럼이 된다.
> - 기본값 등 추가설정을 할 경우 선언해야된다.
```
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;
```

### 8. @Builder
>- 해당 클래스의 빌더 패턴 클래스 생성
> - 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함

### 9. @MappedSuperclass
> - JPA Entity 클래스들이 BaseEntity를 상속할 경우 필드들도 칼럼으로 인식하게 한다.

### 10. @EntityListeners(AuditingEntityListener.class)
> - 현재 클래스에 Auditing 기능을 포함한다.
> - Auditing기능은 Spring Data JPA에서 시간에 대해서 자동으로 값을 넣어주는 기능이다.
> - application실행 부분에 @EnableJpaAuditing으로 활성화 해줘야한다.

### 11. @CreatedDate
> - Entity가 생성되어 저장될 때 시간이 자동으로 저장된다.

### 12. @LastModifiedDate
> - 조회한 Entity의 값을 변경할 때 시간이 자동 저장된다.

### #JPA와 QueryDsl
>
> FK의 조인, 복잡한 조건 등은 Entity만으로 해결하기 어렵다. 
> 따라서 조회용 프레임워크로 MyBatis, QueryDsl 등을 추가로 이용하는 경향이 있는데
> QueryDsl은 메소드를 기반으로 쿼리를 생성하기 때문에 오타 등의 자잘한 에러를 IDE에서 걸러 낼 수 있다는 장점이 있다.