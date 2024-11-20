package com.utmn.books_api.domain.customer.model.mapper;

import com.utmn.books_api.domain.customer.model.entity.Customer;
import com.utmn.books_api.domain.customer.model.request.CustomerRequest;
import com.utmn.books_api.domain.customer.model.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING
)
public interface CustomerMapper {
    CustomerResponse toResponse(Customer entity);
    Customer toEntity(CustomerRequest request);
    void toEntity(CustomerRequest request, @MappingTarget Customer entity);
}
