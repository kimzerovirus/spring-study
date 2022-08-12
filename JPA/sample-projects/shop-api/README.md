쿼리 방식 권장 순서
1. 엔티티 -> DTO 변환 방법
2. Fetch Join 사용
3. DTO를 사용한 조회
4. 최후의 방법으로 네이티브SQL 또는 JDBC Template 등을 이용하여 직접 SQL을 작성한다.

1대다 - 컬렉션 조회 최적화