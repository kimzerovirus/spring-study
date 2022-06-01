### JDBC : Java Database Connectivity
JDBC 표준 인터페이스에 맞춰진 각 db의 드라이버를 사용해 쉽게 db와 연결할 수 있다.
다만, sql dialect(방언)은 각 db마다 다르기 때문에 sql을 다시 작성해야 되는 문제가 있다. ex) 페이징 => JPA를 사용시 이러한 문제점도 일부 해결 가능하긴 하다.

### SQL Mapper
- JdbcTemplate
- MyBatis

sql에서 에러나면 찾기 너무 힘듦...

### ORM
- JPA
  - 하이버네이트
  - 이클립스링크

객체와 db테이블과 연결해주는 기술