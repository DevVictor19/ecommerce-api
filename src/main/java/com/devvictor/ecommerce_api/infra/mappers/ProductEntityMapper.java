package com.devvictor.ecommerce_api.infra.mappers;

import com.devvictor.ecommerce_api.domain.entities.Product;
import com.devvictor.ecommerce_api.infra.dtos.products.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {
    ProductDTO toDto(Product product);
    Product fromDto(ProductDTO dto);
}
