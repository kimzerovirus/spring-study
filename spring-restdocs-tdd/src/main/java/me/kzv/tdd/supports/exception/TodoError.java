package me.kzv.tdd.supports.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TodoError {
    INVALID_DEADLINE("유효하지 않은 기한입니다."),
    ALREADY_COMPLETED("이미 완료된 할 일입니다."),
    NOT_COMPLETED_YET("아직 완료되지 않은 할일입니다."),
    ;

    private final String message;
}
