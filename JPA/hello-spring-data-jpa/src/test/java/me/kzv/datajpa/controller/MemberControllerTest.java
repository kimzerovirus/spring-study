package me.kzv.datajpa.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
class MemberControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Transactional
    @Test
    public void member_get() throws Exception {

        // 조회 API -> 대상의 데이터가 있어야됨
        ResultActions resultActions = mockMvc.perform(
                // pathParameters 의 경우, RestDocumentationRequestBuilders 사용
                // requestParameters 의 경우, MockMvcRequestBuilders 사용
                get("/api/members/{id}", 1L)
                    .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk());

        resultActions.andDo(
                // rest docs 문서 작성 시작
                document("member", // 문서 조각 디렉토리 명
                        pathParameters( // path 파라미터 정보 입력
                                parameterWithName("id").description("Member ID")
                        ),
                        responseFields( //response 필드 정보 입력
                                fieldWithPath("id").description("ID"),
                                fieldWithPath("username").description("username"),
                                fieldWithPath("age").description("age")
                        )
                )
        );
    }

}