package me.kzv.jdbc.repository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.kzv.jdbc.connection.DBConnectionUtil;
import me.kzv.jdbc.domain.Member;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;

/*
    JDBC - DataSource 사용, JdbcUtils 사용
    직접 DriverManager에서 구현하는게 아닌 스프링 JDBC에서 제공하는 메서드를 이용하는 방법
 */
@Slf4j
@RequiredArgsConstructor
public class MemberRepositoryV1 {

    private final DataSource dataSource;

    // create
    public Member save(Member member) throws SQLException {
        String sql = "insert into member(member_id, money) values (?, ?)";

        Connection conn = null;
        PreparedStatement pstmt = null; // 데이터베이스에 전달할 sql과 파라미터로 전달할 데이터들을 준비
        // Statement의 자식으로 query에서 ?를 통한 바인딩 기능을 사용할 수 있음
        // ? 바인딩을 사용하지 않으면 sql injection공격을 당할 수 있다!!!


        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            pstmt.executeUpdate(); // 반영된 row 수 만큼 int로 반환

            return member;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            // 역순으로 TCP커넥션을 닫아줘야한다.
//            pstmt.close();
//            conn.close();
            // 연결이 제대로 안끊어질 수 있으므로 각 항목별로 에러를 캐치해야된다.
            close(conn, pstmt, null);
        }
    }

    // read
    public Member findById(String memberId) throws SQLException {
        String sql = "select * from member where member_id = ?";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);

            rs = pstmt.executeQuery();

            if (rs.next()) {
                // 여러개를 가져온다면 while로 next가 없을때까지 가져와야 한다.
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            } else {
                throw new NoSuchElementException("member not found memberId=" + memberId);
            }

        } catch (SQLException e) {
            log.error("db error", e);
            throw e;
        }finally{
            close(conn, pstmt, rs);
        }

    }

    // update
    public void update(String memberId, int money) throws SQLException {
        String sql = "update member set money=? where member_id=?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, memberId);
            int resultSize = pstmt.executeUpdate();
            log.info("resultSize {}", resultSize);

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            close(conn, pstmt, null);
        }

    }

    // delete
    public void delete(String memberId) throws SQLException {
        String sql = "delete from member where member_id=?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            int resultSize = pstmt.executeUpdate();
            log.info("resultSize {}", resultSize);

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            close(conn, pstmt, null);
        }

    }

    private void close(Connection conn, Statement stmt, ResultSet rs) {
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(stmt);
        JdbcUtils.closeConnection(conn);
    }


    private Connection getConnection() throws SQLException {
        Connection conn = dataSource.getConnection();
        log.info("get connection = {}, class = {}");
        return conn;
    }

}
