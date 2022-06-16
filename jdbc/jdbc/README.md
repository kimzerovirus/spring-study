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

### Connection Pool
사용자가 있을때마다 커넥션을 연결하고 끊으려면 시간이 오래 걸리므로 대기해두고 있다가 접속하면 연결해주는것
스프링부트2.0 부터는 HikariCP가 기본으로 채택되어 있음 
<br/>따라서 커넥션풀은 같은 커넥션을 재사용할 수 있다는것이다.

### Transaction - 거래
db관점에서 봤을떄 하나의 거래를 안전하게 처리한다는 의미.

```
A의 계좌에서 5000원을 B의 계좌로 5000원을 송금했을 경우
A -5000
B +5000
이러한 작업이 안전하게 다 이루어져야 됨
```
- 원자성: 트랜잭션 내에서 실행된 작업은 모두 성공 or 모두 실패해야함. (하나의 작업처럼 취급)
- 일관성: 모든 트랜잭션은 일관성있는 데이터베이스의 상태를 유지해야함.
- 격리성: 동시에 실행되는 트랜잭션이 서로에게 영향을 끼치지 아니해야함. (격리 수준 설정 가능: Isolation Level)
  1. READ UNCOMMITTED (커밋되지 않은 읽기) 
  2. READ COMMITTED (커밋된 읽기)
  3. REPEATABLE READ (반복 가능한 읽기)
  4. SERIALIZABLE (직렬화 가능)
  - *순서대로 성능이 빠르나 안전하지가 않음* 보통은 2,3번을 사용함
  
- 지속성: 트랜잭션이 성공적으로 끝났을때 그 결과를 기록(로그 남기기)하여 복구 할 수 있게 해야함.

DB락

트랜잭션을 시작하고 데이터를 수정하고 커밋을 하지 않은 상태에서 다른 사람이 같은 데이터를 동시에 수정하면 발생. 마치 깃 머지할 때 충돌 나는것 같은 느낌?
원자성이 깨진 상태이다. 따라서 트랜잭션이 시작되고 커밋 또는 롤백 되기 전까는 다른 사람이 수정하지 못하도록 막아야 한다.