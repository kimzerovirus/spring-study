# 스프링부트 JPA TDD개발
>
>  - SpringBoot 2.x
>  - lombok
>  - junit4
>  - thymeleaf
>  - h2 Database
>

### 1. @Getter, @Setter
 - 선언된 모든 필드의 get메서드 생성
 - 선언된 모든 필드의 set메서드 생성

### 2. @Data
 - 클래스에 선언된 모든 private에 대해 @Getter와 @Setter를 적용

### 3. @RequiredArgsConstructor
 - 선언된 모든 초기화 되지 않은 final필드 또는 @NonNull이 붙은 필드에 대해 생성자를 생성해 준다(final이 없는 필드는 생성x).

### 4. @Entity
 - DB의 테이블과 링크될 클래스임을 선언
 - 기본값으로 클래스(자바)의 카멜케이스 = 데이터베이스의 언더스코어 네이밍(언더바)과 매칭된다.

### 5. @Id
 - Primary Key, 즉 PK임을 의미함

### 6. @GeneratedValue
 - PK규칙을 나타낸다
 - 스프링부트 2.0의 경우 GenerationType.IDENTITY옵션을 추가해야 auto_increment사용가능

### 7. @Column
 - 테이블의 칼럼으로 선언하지 않아도 해당 클래스의 모든 필드는 칼럼이 된다.
 - 기본값 등 추가설정을 할 경우 선언해야된다.
```
    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;
```

### 8. @NoArgsConstructor
 - 기본 생성자 자동추가

### 9. @Builder
 - 해당 클래스의 빌더 패턴 클래스 생성
 - 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
