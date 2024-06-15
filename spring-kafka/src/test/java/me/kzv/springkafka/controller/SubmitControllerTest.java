package me.kzv.springkafka.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
class SubmitControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void testMemberJoinSubmit() throws Exception {
        mockMvc.perform(
                        post("/submit/join")
                                .param("memberName", "kzv")
                                .param("organizationName", "developer")
                                .param("memo", "backend")
                ).andExpect(status().isOk())
                .andDo(print());
    }

}