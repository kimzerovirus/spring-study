package me.kzv.jdbcjpas3.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * 200 OK - isOK()
 * 201 created - isCreated()
 * 400 Bad Request - isBadRequest()
 * 403 Forbidden - isForbidden()
 * 404 NotFound - isNotFound()
 * 400~ - is4xxClientError()
 * 500 Internal Server Error - isInternalServerError()
 * 500~ - is5xxServerError()
 */

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class TestControllerTest {
    @Autowired WebApplicationContext context;
    @Autowired TestMemberRepository testMemberRepository;
    MockMvc mockMvc;

    @BeforeEach
    void mockMvcSetUp(){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @DisplayName("모드 테스트멤버 조회")
    @Test
    public void getAllTestMembers() throws Exception {
        String url = "/test";
        TestMember savedMember = testMemberRepository.save(new TestMember(1L, "홍길동"));

        ResultActions result = mockMvc.perform(get(url));

        result
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(savedMember.getId()))
                .andExpect(jsonPath("$[0].name").value(savedMember.getName()));
    }
}