DROP DATABASE IF EXISTS multi_tenancy;
CREATE DATABASE multi_tenancy;

\c multi_tenancy postgres;
-- \c multi_tenancy  -- mysql user database 랑 같은 듯 <-- init.sql 로 실행 불가능 <-- user가 누군지 지정해주면 되는듯?

DROP SCHEMA IF EXISTS tenant_postgres;
CREATE SCHEMA tenant_postgres;

DROP USER IF EXISTS tenant_user;
CREATE USER tenant_user PASSWORD '1234';

GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA tenant_postgres TO tenant_user;
ALTER ROLE tenant_user SET SEARCH_PATH = tenant_postgres