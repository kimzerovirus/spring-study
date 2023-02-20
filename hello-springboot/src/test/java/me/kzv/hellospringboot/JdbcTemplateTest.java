package me.kzv.hellospringboot;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.*;

@HelloSpringBootTest
public class JdbcTemplateTest {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void init(){
        jdbcTemplate.execute("create table if not exists hello(name varchar(50) primary key, count int)");
    }

    @Test
    public void insertAndQuery() throws Exception {
        jdbcTemplate.update("insert into hello values(?,?)", "test1", 3);
        jdbcTemplate.update("insert into hello values(?,?)", "test2", 1);

        Long count = jdbcTemplate.queryForObject("select count(*) from hello", Long.class);
        assertThat(count).isEqualTo(2);
    }

    @Test
    public void insertAndQuery2() throws Exception {
        jdbcTemplate.update("insert into hello values(?,?)", "test1", 3);
        jdbcTemplate.update("insert into hello values(?,?)", "test2", 1);

        Long count = jdbcTemplate.queryForObject("select count(*) from hello", Long.class);
        assertThat(count).isEqualTo(2);
    }
}
