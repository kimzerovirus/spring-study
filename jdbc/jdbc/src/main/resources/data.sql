set autocommit false; -- 수동 커밋 모드 = 트랜잭션의 시작이라고 볼 수 있음(원하는 범위만큼을 관리 할 수 있기 때문에)
-- 수동 커밋을 설정하면 commit 혹은 rollback이 이루어져야함 일반적인 DB에서는 아무것도 하지 않았을 경우 일정시간이 지난 후에 rollback 시킨다.
-- 커밋이 이루어져야 다른 세션에서도 새로 저장된 데이터를 확인할 수 있다.
