package me.kzv.olle.web.event.validator;

import me.kzv.olle.web.event.Event;
import me.kzv.olle.web.event.dto.EventRequestDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

@Component
public class EventRequestDtoValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return EventRequestDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        EventRequestDto dto = (EventRequestDto) target;

        if (isNotValidEndEnrollmentDateTime(dto)) {
            errors.rejectValue("endEnrollmentDateTime", "wrong.datetime", "모임 접수 종료 일시를 정확히 입력하세요.");
        }

        if (isNotValidEndDateTime(dto)) {
            errors.rejectValue("endDateTime", "wrong.datetime", "모임 종료 일시를 정확히 입력하세요.");
        }

        if (isNotValidStartDateTime(dto)) {
            errors.rejectValue("startDateTime", "wrong.datetime", "모임 시작 일시를 정확히 입력하세요.");
        }
    }

    private boolean isNotValidStartDateTime(EventRequestDto dto) {
        return dto.getStartDateTime().isBefore(dto.getEndEnrollmentDateTime());
    }

    private boolean isNotValidEndEnrollmentDateTime(EventRequestDto dto) {
        return dto.getEndEnrollmentDateTime().isBefore(LocalDateTime.now());
    }

    private boolean isNotValidEndDateTime(EventRequestDto dto) {
        LocalDateTime endDateTime = dto.getEndDateTime();
        return endDateTime.isBefore(dto.getStartDateTime()) || endDateTime.isBefore(dto.getEndEnrollmentDateTime());
    }

    public void validateUpdateDto(EventRequestDto dto, Event event, Errors errors) {
        if (dto.getLimitOfEnrollments() < event.getNumberOfAcceptedEnrollments()) {
            errors.rejectValue("limitOfEnrollments", "wrong.value", "확인된 참기 신청보다 모집 인원 수가 커야 합니다.");
        }
    }
}
