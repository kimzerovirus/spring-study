package me.kzv.persistence;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;

import static org.junit.Assert.fail;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class DataSourceTest {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection(){
        try(Connection conn = dataSource.getConnection()){
            System.out.println(conn);
        }catch(Exception e){
            fail(e.getMessage());
        }
    }
}
