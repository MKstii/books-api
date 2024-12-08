package com.utmn.books_api.domain.exhibition.model.validator;

import com.utmn.books_api.domain.exhibition.model.request.ExhibitionRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ExhibitionDateValidationValidator implements ConstraintValidator<ExhibitionDateValidation, ExhibitionRequest> {

    @Override
    public boolean isValid(ExhibitionRequest value, ConstraintValidatorContext context) {
        return value.getStartDate().isBefore(value.getEndDate());
    }
}