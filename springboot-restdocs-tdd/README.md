**Tech Stack**

- JPA
- JUnit
- Mockito
- AssertJ
- Rest Docs

# TDD

**단위 테스트를 작성하는 이유**
- 코드를 수정하거나 기능을 추가할 때 수시로 빠르게 검증할 수 있다.

**TDD의 문제점**
- 테스트에 대한 정답을 개발자가 작성하므로 논리가 처음부터 틀렸다면
테스트의 성공여부와 상관없이 실제로는 의도와 다르게 동작할 수 있다는 문제점이 있음..

## TDD(Test-Driven Development, 테스트 주도 개발) 방법 및 순서

1. 기능 단위 실패/성공, 모든 케이스를 테스트 코드 작성
2. 테스트 코드에 대한 프로덕션 코드 작성
3. 무한 반복...

## Spring Boot + TDD

강의나 자료를 찾아보면 controller -> service -> repository 순으로 진행되는 경우가 많다. 
하지만 개인적으로 나는 역순으로 하는게 더 효율적이라고 생각한다. 
가장 먼저 엔티티 설계를 하고 해당 엔티티의 기능에 대한 테스트 코드를 작성하고 리포지토리의 쿼리를 검증하는 식으로 진행하는게 개발이 더 편리하다고 느꼈다.
<br/>

**내가 생각하는 Spring Data JPA 환경에서의 TDD 방법 및 순서**

1. 엔티티 설계
2. 해당 엔티티에 대한 기능 메서드 작성 및 테스트 코드 작성
3. 리포지토리 테스트를 통해 쿼리 검증 (필수x)
4. 서비스 테스트 코드 및 프로덕션 코드 작성
5. 컨트롤러 테스트 코드 및 프로덕션 코드 작성

## AssertJ
> `assertThat(검증대상)`메서드를 사용하여, 체이닝을 통해 검증 메서드를 연쇄적으로 사용할 수 있다.

### 기본 검증 메서드

- `isEqualTo`: 검증대상과 동일한 값인지 비교한다.
- `isSameAs`: 검증대상과 값을 ==으로 비교한다.
- `isNotNull`: 검증대상이 Not Null인지 확인한다.
- `isNull`: 검증대상이 Null인지 확인한다.
- `isNotEmpty`: 검증대상이 Not Empty인지 확인한다.
- `isEmpty`: 검증대상이 Empty인지 확인한다.

### 문자열 검증 메서드

- `contains`: 검증대상에 값이 포함되어있는지 확인한다.
- `startsWith`: 검증대상이 시작값이 값과 동일한지 비교한다.
- `endWith`:	검증대상의 마지막값이 값과 동일한지 비교한다.

### 숫자 검증 메서드

- `isPositive`: 검증대상이 양수인지 확인한다.
- `isNegative`: 검증대상이 음수인지 확인한다.
- `isZero`: 검증대상이 0인지 확인한다.
- `isGreaterThan`: 검증대상이 값을 초과한지 확인하다.
- `isLessThan`: 검증대상이 값보다 미만인지 확인한다.
- `isGreaterThanOrEqualTo`: 검증대상이 값 이상인지 확인한다.
- `isLessThanOrEqualTo`: 검증대상이 값 이하인지 확인한다.

### 필터링 및 추출 메서드

> `Iterable`, `Array` 객체들의 값을 필터링하여 값을 검증한다.

- `filteredOn`
- `filteredOnNull`
- `filteredOnAssertions`
- `extracting`

```java
    @Test
    void test() {
        List<String> langs = List.of("java", "kotlin", "javascript", "typescript");

        assertThat(langs).filteredOn(lang -> lang.contains("java"))
                         .hasSize(1);
    }
```

프로퍼티 접근 예시
```java
    @Test
    void test() {
        Pair java1 = new Pair("java", 1)
        Pair java2 = new Pair("java", 2)
        Pair kotlin1 = new Pair("kotlin", 1)
        Pair kotlin2 = new Pair("kotlin", 2)
            
        List<Pair> langs = List.of(java1, java2, kotlin1, kotlin2);
        
        assertThat(langs).filteredOn("second", 1)
            .containsOnly(java1, kotlin1);

        assertThat(langs).filteredOn("first", not("java"))
            .containsOnly(kotlin1, kotlin2);

        assertThat(langs).filteredOn("first", in("java"))
            .containsOnly(java1, java2);

        assertThat(langs).filteredOn("second", notIn(2))
            .containsOnly(java1, kotlin1);
        
        assertThat().extracting("first", String.class)
            .contains("java", "kotlin");

        assertThat().extracting("first", "second")
            .contains(tuple("java", 1), tuple("java", 2), tuple("kotlin", 1), tuple("kotlin", 2));
    }
```

### 예외 처리

- `assertThatThrownBy`
- `assertThatNullPointerException`
- `assertThatIllegalArgumentException`
- `assertThatIllegalStateException`

```java
    @Test
    void exceptionTest() {
        assertThatThrownBy(() -> exampleService.makeNullPointException())
                .isInstanceOf(NullPointerException.class)
                .hasMessage("null point exception");
                
        assertThatNullPointerException()
                .isThrownBy(() -> exampleService.makeNullPointException());

        assertThatIllegalArgumentException()
                .isThrownBy(() -> exampleService.makeIllegalArgumentException());
    }
```

## Hamcrest
> `AssertJ` 외에도 많이 사용되는 테스팅 프레임워크인 `Hamcrest`가 있다. <br/>
> `Hamcrest`는 `Matcher` 라이브러리를 사용하여 표현식을 자연스럽고 가독성 좋게 만들어준다.

```java
    @Test
    void test() {
        String email = "kimzerovirus@gmail.com";
        String id = email.substring(0, email.indexOf("@"));
        String domainName = "gmail";
        int idMaxLength = 10;
            
        assertThat(email, not(email));
        assertThat(email, containsString(domainName));
        assertThat(id.length(), lessThanOrEqualTo(idMaxLength));
    }
```
개인적으로 모든 함수를 import 해줘야하는 `Hamcrest`와 달리 체이닝 방식인 `AssertJ`가 더 가독성 좋고 편한것 같다.