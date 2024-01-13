package me.kzv.springevents.genericevent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GenericEvent<T> {
    private T result;
    protected boolean success;
}

// 제네릭으로 선언하여 다양한 이벤트 형태를 처리할 수도 있음