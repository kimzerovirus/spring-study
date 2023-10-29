package me.kzv.batch.example.jobparameter;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public abstract class LocalDateConverter {
    private static final String LOCAL_DATE_PATTERN = "yyyy-MM-dd";

    public static LocalDate convert(String source) {
        return LocalDate.parse(source, DateTimeFormatter.ofPattern(LOCAL_DATE_PATTERN));
    }
}
