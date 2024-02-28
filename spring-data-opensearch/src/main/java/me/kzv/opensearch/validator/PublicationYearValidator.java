package me.kzv.opensearch.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.Year;

public class PublicationYearValidator implements ConstraintValidator<PublicationYear, Integer> {

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        return !Year.of(value).isAfter(Year.now());
    }
}
