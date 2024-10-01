package com.devvictor.ecommerce_api.application.mappers;

import com.devvictor.ecommerce_api.application.dtos.output.CartOutputDTO;
import com.devvictor.ecommerce_api.domain.entities.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartEntityMapper {
    CartOutputDTO toDto(Cart cart);
    Cart fromDto(CartOutputDTO dto);
}
