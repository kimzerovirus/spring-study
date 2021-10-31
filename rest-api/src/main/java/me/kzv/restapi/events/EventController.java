package me.kzv.restapi.events;

import org.modelmapper.ModelMapper;
import org.modelmapper.internal.Errors;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.net.URI;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Controller
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_VALUE)
public class EventController {

    private final EventRepository eventRepository;

    private final ModelMapper modelMapper;

    //@Autowired말고 생성자 주입
    public EventController(EventRepository eventRepository, ModelMapper modelMapper){
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }


    @PostMapping
    public ResponseEntity createEvent(@RequestBody @Valid EventDto eventDto, Errors errors) {
        //value 검증 후 잘못되었을 경우 errors 객체에 담아준다
        if(errors.hasErrors()){
            return ResponseEntity.badRequest().build();
        }

//        Event event = Event.builder()
//                .name(EventDto.getName()) ...
//                .build();
// model mapper를 사용하면 위의 코드를 줄일 수 있다. 다만 직접 작성하는거에 비해 성능이 떨어 질 수 있다.

        Event event = modelMapper.map(eventDto, Event.class);

        Event newEvent = this.eventRepository.save(event);

//        URI createUri = linkTo(methodOn(EventController.class).createEvent(null)).slash("{id}").toUri(); //mediatype을 지정해줘서 methodon필요없어짐
//        URI createdUri = linkTo(EventController.class).slash("{id}").toUri();
        URI createdUri = linkTo(EventController.class).slash(newEvent.getId()).toUri();
//        return ResponseEntity.created(createUri).build();
//        event.setId(10);
        return ResponseEntity.created(createdUri).body(event);
    }
}
