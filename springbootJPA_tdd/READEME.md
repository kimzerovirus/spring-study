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