package com.utmn.books_api.domain.exhibition.model.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ExhibitionDateValidationValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExhibitionDateValidation {
    String message() default "Начало даты выставки не должно быть позже окончания";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}