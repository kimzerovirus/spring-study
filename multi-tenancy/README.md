# 멀티 테넌시
> 멀티 테넌시는 소프트웨어 어플리케이션의 단일 인스턴스가 여러 고객에게 서비스를 제공하는 아키텍처를 말한다.
> <br/>
> 멀티 테넌시는 소프트웨어 개발과 유지보수 비용을 공유하기 때문에 경제적이다.

Multi tenant - Same Application, Separate Database<br/>
예를 들면, 카카오와 같은 회사는 여러 개의 계열사가 존재한다.
따라서 각각의 계열사 별로 DB를 만들어 주고 어플리케이션은 하나로 관리하는 경우 적합한 아키텍처이다.

### 멀티 테넌시 아키텍처

- 테넌트를 데이터베이스 단위로 분리
  - 트래픽이 몰리는 데이터베이스만 영향 
  - 여러 데이터베이스로 인한 관리 비용 상승
- 테넌트를 스키마 단위로 분리
  - 하나의 테넌트에 트래픽이 몰리더라도 나머지 테넌트에 영향이 있을 수 있음
- 테넌트를 단일 테이블에서 테넌트 PK 또는 테넌트 코드 등으로 데이터 분리

### 구현

- `AbstractRoutingDataSource`를 상속받아서 구현
- jpa에서는 `MultiTenantConnectionProvider`와 `CurrentTenantIdentifierResolver`를 통해 구현

### 모듈 구조

1. tenancy manager : 데이터베이스 벤더, 테넌트와 유저의 권한 등을 생성 및 관리한다.
2. tenancy application : 비즈니스를 담당한다.

## Multi-Tenancy
- tenancy-manager 모듈은 접속 할 DB의 정보를 관리하는 모듈이다.
  - mysql DB를 사용한다.
- tenancy-application 은 tenancy-manager 로 부터 얻은 DB 커넥션 정보를 사용해 어플리케이션을 작동한다.
  - mysql 과 postgresql 을 사용한다.

### docker run
```shell
docker compose -p multi-tenancy -f ./docker/docker-compose.yml up -d
```
mysql
```shell
mysql -u ${user_name} -p
```
```mysql
show databases;

-- 유저 
use mysql;
select user, host from user;
```

psql
```shell
psql -U ${user_name} -d ${database_name} # 명령어 대소문자 구분함.. -U -d
```
```postgresql
select datname from pg_catalog.pg_database;
select schema_name from information_schema.schemata;
select table_schema, table_name from information_schema.tables where table_schema not in ('pg_catalog', 'information-schema');
select table_schema, table_name as view_name from information_schema.views where table_schema not in ('pg_catalog', 'information-schema');
select table_schema, table_name, column_name, data_type from information_schema.columns where table_schema = 'schemaname' and table_name = 'tablename';
```




###### 참고
- https://spring.io/blog/2022/07/31/how-to-integrate-hibernates-multitenant-feature-with-spring-data-jpa-in-a-spring-boot-application
- https://www.baeldung.com/hibernate-6-multitenancy
- https://www.baeldung.com/multitenancy-with-spring-data-jpa
- https://m.blog.naver.com/ki630808/221778753901
- https://velog.io/@jhkim105/Multi-Tenancy-in-Spring-Data-JPA
- https://jaimemin.tistory.com/2270