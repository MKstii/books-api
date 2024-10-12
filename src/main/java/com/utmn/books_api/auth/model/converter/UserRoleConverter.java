package com.utmn.books_api.auth.model.converter;

import com.utmn.books_api.auth.model.entity.UserRole;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Converter
public class UserRoleConverter implements AttributeConverter<Set<UserRole>, String> {

    private final static String DELIMITER = ";";

    @Override
    public String convertToDatabaseColumn(Set<UserRole> roles) {
        return roles.stream()
                .map(UserRole::name)
                .collect(Collectors.joining(";"));
    }

    @Override
    public Set<UserRole> convertToEntityAttribute(String roles) {
        return Arrays.stream(roles.split(DELIMITER))
                .map(UserRole::valueOf)
                .collect(Collectors.toSet());
    }
}
