package com.devvictor.ecommerce_api.products.infra.mappers;

import com.devvictor.ecommerce_api.products.domain.entities.Product;
import com.devvictor.ecommerce_api.products.infra.dtos.ProductDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductEntityMapper {
    ProductDTO toDto(Product product);
    Product fromDto(ProductDTO dto);
}
