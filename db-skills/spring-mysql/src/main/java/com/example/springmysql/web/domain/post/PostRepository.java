package com.example.springmysql.web.domain.post;

import com.example.springmysql.web.domain.post.dto.DailyPostCount;
import com.example.springmysql.web.domain.post.dto.DailyPostCountRequest;
import com.example.springmysql.web.util.PageHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class PostRepository {
    static final String TABLE = "Post";

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final RowMapper<Post> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> Post.builder()
            .id(resultSet.getLong("id"))
            .memberId(resultSet.getLong("memberId"))
            .contents(resultSet.getString("contents"))
            .createdDate(resultSet.getObject("createdDate", LocalDate.class))
            .createdAt(resultSet.getObject("createdAt", LocalDateTime.class))
            .likeCount(resultSet.getLong("likeCount"))
            .version(resultSet.getLong("version"))
            .build();

    public List<Post> findByMemberId(Long memberId) {
        var params = new MapSqlParameterSource()
                .addValue("memberId", memberId);
        String query = String.format("SELECT * FROM `%s` WHERE id = :id", TABLE);
        return namedParameterJdbcTemplate.query(query, params, ROW_MAPPER);
    }

    public List<DailyPostCount> groupByCreatedDate(DailyPostCountRequest request) {
        var params = new BeanPropertySqlParameterSource(request);
        String query = "SELECT memberId, createdDate, count(id) as cnt\nFROM " + TABLE + "\nWHERE memberId = :memberId and createdDate between :firstDate and :lastDate\nGROUP BY memberId, createdDate\n";

        RowMapper<DailyPostCount> mapper = (ResultSet resultSet, int rowNum) -> new DailyPostCount(
                resultSet.getLong("memberId"),
                resultSet.getObject("createdDate", LocalDate.class),
                resultSet.getLong("cnt")
        );

        return namedParameterJdbcTemplate.query(query, params, mapper);
    }

    public Page<Post> findAllByMemberId(Long memberId, PageRequest pageRequest) {
        var params = new MapSqlParameterSource()
                .addValue("memberId", memberId)
                .addValue("offset", pageRequest.getOffset())
                .addValue("size", pageRequest.getPageSize());

        Sort sort = pageRequest.getSort();
        String query = "SELECT *\nFROM " + TABLE + "\nWHERE memberId = :memberId\nORDER BY " + PageHelper.orderBy(sort) + "\nLIMIT :offset, :size\n";

        var posts = namedParameterJdbcTemplate.query(query, params, ROW_MAPPER);
        return new PageImpl<Post>(posts, pageRequest, getCount(memberId));
    }

    public List<Post> findAllByLessThanIdAndMemberIdInAndOrderByIdDesc(Long id, List<Long> memberIds, int size) {
        if (memberIds.isEmpty()) {
            return List.of();
        }

        var params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("memberIds", memberIds)
                .addValue("size", size);

        String query = "SELECT *\nFROM " + TABLE + "\nWHERE memberId in (:memberIds) and id < :id\nORDER BY id DESC\nLIMIT :size\n";

        return namedParameterJdbcTemplate.query(query, params, ROW_MAPPER);

    }

    public List<Post> findAllByMemberIdInAndOrderByIdDesc(List<Long> memberIds, int size) {
        if (memberIds.isEmpty()) {
            return List.of();
        }

        var params = new MapSqlParameterSource()
                .addValue("memberIds", memberIds)
                .addValue("size", size);

        String query = "SELECT *\nFROM " + TABLE + "\nWHERE memberId in (:memberIds)\nORDER BY id DESC\nLIMIT :size\n";

        return namedParameterJdbcTemplate.query(query, params, ROW_MAPPER);

    }

    private Integer getCount(Long memberId) {
        String countQuery = "SELECT count(id)\nFROM " + TABLE + "\nWHERE memberId = :memberId\n";
        var countParam = new MapSqlParameterSource().addValue("memberId", memberId);
        return namedParameterJdbcTemplate.queryForObject(countQuery,  countParam, Integer.class);
    }

    public List<Post> findAllByLessThanIdAndMemberIdAndOrderByIdDesc(Long id, Long memberId, int size) {
        var params = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("memberId", memberId)
                .addValue("size", size);

        String query = "SELECT *\nFROM " + TABLE + "\nWHERE memberId = :memberId and id < :id\nORDER BY id DESC\nLIMIT :size\n";

        return namedParameterJdbcTemplate.query(query, params, ROW_MAPPER);
    }

    public List<Post> findAllByMemberIdAndOrderByIdDesc(Long memberId, int size) {
        var params = new MapSqlParameterSource()
                .addValue("memberId", memberId)
                .addValue("size", size);

        String query = "SELECT *\nFROM " + TABLE + "\nWHERE memberId = :memberId\nORDER BY id DESC\nLIMIT :size\n";

        return namedParameterJdbcTemplate.query(query, params, ROW_MAPPER);
    }

    public List<Post> findAllByIdIn(List<Long> postIds) {
        if (postIds.isEmpty()) {
            return List.of();
        }

        var params = new MapSqlParameterSource()
                .addValue("postIds", postIds);

        String query = "SELECT *\nFROM " + TABLE + "\nWHERE id in (:postIds)\n";

        return namedParameterJdbcTemplate.query(query, params, ROW_MAPPER);

    }

    public Optional<Post> findById(Long postId, boolean requiredLock) {
        String query = "SELECT *\nFROM " + TABLE + "\nWHERE id = :postId\n";
        if (requiredLock) {
            query += "FOR UPDATE";
        }

        var params = new MapSqlParameterSource()
                .addValue("postId", postId);
        var nullablePost = namedParameterJdbcTemplate.queryForObject(query, params, ROW_MAPPER);
        return Optional.ofNullable(nullablePost);
    }

    public Post save(Post post) {
        if (post.getId() == null)
            return insert(post);
        return update(post);
    }

    private Post insert(Post post) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(namedParameterJdbcTemplate.getJdbcTemplate())
                .withTableName(TABLE)
                .usingGeneratedKeyColumns("id");

        SqlParameterSource params = new BeanPropertySqlParameterSource(post);
        var id = jdbcInsert.executeAndReturnKey(params).longValue();

        return Post.builder()
                .id(id)
                .memberId(post.getMemberId())
                .contents(post.getContents())
                .createdDate(post.getCreatedDate())
                .createdAt(post.getCreatedAt())
                .build();
    }

    private Post update(Post post) {
        var sql = "UPDATE " + TABLE + " set\n    memberId = :memberId,\n    contents = :contents,\n    createdDate = :createdDate,\n    createdAt = :createdAt,\n    likeCount = :likeCount,\n    version = :version + 1\nWHERE id = :id and version = :version\n";

        SqlParameterSource params = new BeanPropertySqlParameterSource(post);
        var updatedCount = namedParameterJdbcTemplate.update(sql, params);
        if (updatedCount == 0) {
            throw new RuntimeException("not updated");
        }
        return post;
    }
    public void bulkInsert(List<Post> posts) {
        var sql = "INSERT INTO `" + TABLE + "` (memberId, contents, createdDate, createdAt)\nVALUES (:memberId, :contents, :createdDate, :createdAt)\n";

        SqlParameterSource[] params = posts
                .stream()
                .map(BeanPropertySqlParameterSource::new)
                .toArray(SqlParameterSource[]::new);
        namedParameterJdbcTemplate.batchUpdate(sql, params);
    }

}

