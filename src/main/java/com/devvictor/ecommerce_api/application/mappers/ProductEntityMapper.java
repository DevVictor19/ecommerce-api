package com.devvictor.ecommerce_api.application.mappers;

import com.devvictor.ecommerce_api.application.dtos.output.ProductOutputDTO;
import com.devvictor.ecommerce_api.domain.entities.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {
    ProductOutputDTO toDto(Product product);
    Product fromDto(ProductOutputDTO dto);
}
