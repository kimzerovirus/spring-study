package me.kzv.restapi.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest //슬라이싱 테스트와 달리 application을 실행했을 때와 같도록 모든 bean을 등록
@AutoConfigureMockMvc //실제 repository 사용
//@WebMvcTest //슬라이싱 테스트 MockBean사용
public class EventControllerTests {

    @Autowired
    MockMvc mockMvc; //가짜요청과 응답을 할 수 있음 dispatcherServlet을 이용, ws를 이용하지 않으므로 조금 빠름

    @Autowired
    ObjectMapper objectMapper;

//    @MockBean
//    EventRepository eventRepository;

    @Test
    public void createEvent() throws Exception {
        Event event = Event.builder()
                .name("Spring")
                .description("REST API")
                .beginEnrollDateTime(LocalDateTime.of(2018,11,23,14,21))
                .closeEnrollDateTime(LocalDateTime.of(2018,11,24,14,21))
                .beginEventDateTime(LocalDateTime.of(2018,11,25,14,21))
                .endEventDateTime(LocalDateTime.of(2018,11,26,14,21))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역 D2")
                .free(true) //잘못된 value
                .offline(false) //잘못된 value
                .eventStatus(EventStatus.PUBLISHED)
                .build();

        //mockbean으로 가상?으로 등록된거여서 null을 리턴하는듯? 따라서 직접 id를 넣어주자
//        event.setId(10);
//        Mockito.when(eventRepository.save(event)).thenReturn(event);

        mockMvc.perform(post("/api/events")
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaTypes.HAL_JSON)
                    .content(objectMapper.writeValueAsString(event))) //json으로 변환
                .andDo(print()) //어떤 요청이 있었는지 보여줌
                .andExpect(jsonPath("id").exists())
//                .andExpect(header().exists("Location"))
//                .andExpect(header().string("Content-Type","application/hal+json"))
                .andExpect(header().exists(HttpHeaders.LOCATION))
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE,MediaTypes.HAL_JSON_VALUE))
                .andExpect(status().isCreated()) //isCreated()  = is(201)
                .andExpect(jsonPath("id").value(Matchers.not(100)))
                .andExpect(jsonPath("free").value(Matchers.not(true))) //error
                .andExpect(jsonPath("eventStatus").value(EventStatus.DRAFT.name()));
    }


    //Bad_request로 응답 vs 받기로 한 값 이외는 무시
    @Test
    public void createEvent_badRequest() throws Exception {
        Event event = Event.builder()
                .id(100)
                .name("Spring")
                .description("REST API")
                .beginEnrollDateTime(LocalDateTime.of(2018,11,23,14,21))
                .closeEnrollDateTime(LocalDateTime.of(2018,11,24,14,21))
                .beginEventDateTime(LocalDateTime.of(2018,11,25,14,21))
                .endEventDateTime(LocalDateTime.of(2018,11,26,14,21))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역 D2")
                .free(true) //잘못된 value
                .offline(false) //잘못된 value
                .eventStatus(EventStatus.PUBLISHED)
                .build();

        mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaTypes.HAL_JSON)
                .content(objectMapper.writeValueAsString(event))) //json으로 변환
                .andDo(print()) //어떤 요청이 있었는지 보여줌
               .andExpect(status().isBadRequest());
    }

    @Test
    public void createEvent_Bad_Request_Empty_Input() throws Exception{
        EventDto eventDto = EventDto.builder().build();

        this.mockMvc.perform(post("/api/events")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(this.objectMapper.writeValueAsString(eventDto))) //입력값이 이상한데(빈 값을 보냄) bad_request가 나오지 않는다 ,,, vaild와 errors를 통해 에러 처리함
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createEvent_Bad_Request_Wrong_Input() throws Exception{
        EventDto eventDto = EventDto.builder().build();

        this.mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(eventDto))) //입력값이 이상한데(빈 값을 보냄) bad_request가 나오지 않는다 ,,, vaild와 errors를 통해 에러 처리함
                .andExpect(status().isBadRequest());
    }
}
