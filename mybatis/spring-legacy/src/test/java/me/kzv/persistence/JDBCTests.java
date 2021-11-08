package me.kzv.persistence;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

import static org.junit.Assert.fail;

public class JDBCTests {
    static {
        try {
            Class.forName("com.mysql.jdbc.driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testConnection() {
        try (Connection conn =
                     DriverManager.getConnection(
                             "jdbc:mysql://127.0.0.1:3306/board_sample?useSSL=false&amp;characterEncoding=UTF-8&amp;serverTimezone=UTC",
                             "root",
                             "1541"
                     )) {
            System.out.println(conn);
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
