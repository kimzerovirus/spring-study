package me.kzv.sns.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.kzv.sns.controller.request.UserJoinRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    
    @Test
    public void 회원가입() throws Exception {
        //given
        String userName = "tester";
        String password = "1234";

        //when

        //then
        mockMvc.perform(post("/api/v1/users/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName,password)))
        ).andDo(print())
        .andExpect(status().isOk());

    }

    @Test
    public void 중복회원_가입으로인한_실패() throws Exception {
        //given
        String userName = "tester";
        String password = "1234";

        //when

        //then
        mockMvc.perform(post("/api/v1/users/join")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(new UserJoinRequest(userName,password)))
        ).andDo(print())
                .andExpect(status().isConflict());

    }
}
