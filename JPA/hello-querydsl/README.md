```
./gradlew compileQuerydsl

gradle -> other -> compileQuerydsl
```

## 비교 표현식 (gt, lt, ge, le, eq, ne)

| 구분 | 표현식 | 비교 |
|-----|--------|-------|
| 크다 | a gt b | a > b |
| 작다 | a lt b | a < b |
| 크거나 같다 | a ge b | a >= b |
| 작거나 같다 | a le b  | a <= b |
| 같다 | a eq b | a == b |
| 같지 않다 | a ne b | a !== b |

## 사용자 정의 리포지토리
1. 사용자 정의 인터페이스 작성
2. 사용자 정의 인터페이스 구현
3. 스프링 데이터 JPA 리포지토리에 사용자 정의 인터페이스 상속