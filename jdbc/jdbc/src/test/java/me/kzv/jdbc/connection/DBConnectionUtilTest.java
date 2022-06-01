package me.kzv.jdbc.connection;


import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Connection;

@SpringBootTest
class DBConnectionUtilTest {

    @Test
    void connection(){
        Connection conn = DBConnectionUtil.getConnection();
        Assertions.assertThat(conn).isNotNull();
    }
}
