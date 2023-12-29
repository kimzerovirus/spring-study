package me.kzv.message;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TisError {
    UNKNOWN("error.unknown");

    private final String messageKey;
}
