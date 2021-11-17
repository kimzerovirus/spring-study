package com.kzv.study.exam03.dao;

import com.kzv.study.exam03.dto.RoleDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.kzv.study.exam03.dao.RoleDaoSqls.*; //static 임포트를 하면 클래스명 없이 사용가능하다.

@Repository //저장소의 역할
public class RoleDao {
    private NamedParameterJdbcTemplate jdbc;
    private SimpleJdbcInsert insertAction; // jdbcTemplate에서 insert구문을 실행하려면 필요한 객체
    private RowMapper<RoleDto> rowMapper = BeanPropertyRowMapper.newInstance(RoleDto.class); //BeanPropertyRowMapper -> 작명법 차이인 db의 role_id와 java의 roleId를 매칭해준다.

    public RoleDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
        this.insertAction = new SimpleJdbcInsert(dataSource)
                .withTableName("role");
    }

    public List<RoleDto> selectAll(){
        return jdbc.query(SELECT_ALL, Collections.emptyMap(), rowMapper);
    }

    public int insert(RoleDto role){
        System.out.println(role);
        SqlParameterSource params = new BeanPropertySqlParameterSource(role);
        return insertAction.execute(params);
    }

    public int update(RoleDto role){
        SqlParameterSource params = new BeanPropertySqlParameterSource(role);
        return jdbc.update(UPDATE, params);
    }

    public int deleteById(Integer id) {
        Map<String, ?> params = Collections.singletonMap("roleId", id); //값하나만 삭제할것이므로singletonMap
        return jdbc.update(DELETE_BY_ROLE_ID, params);
    }

    public RoleDto selectById(Integer id) {
        try {
            Map<String, ?> params = Collections.singletonMap("roleId", id);
            return jdbc.queryForObject(SELECT_BY_ROLE_ID, params, rowMapper);
        }catch(EmptyResultDataAccessException e) {
            return null; //해당 조건에 맞는 값이 없을경우 exception 발생
        }
    }

}
