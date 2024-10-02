package com.devvictor.ecommerce_api.infra.mappers;

import com.devvictor.ecommerce_api.domain.entities.Cart;
import com.devvictor.ecommerce_api.infra.dtos.carts.CartDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartEntityMapper {
    CartDTO toDto(Cart cart);
    Cart fromDto(CartDTO dto);
}
