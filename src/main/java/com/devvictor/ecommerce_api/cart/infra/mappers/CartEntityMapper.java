package com.devvictor.ecommerce_api.cart.infra.mappers;

import com.devvictor.ecommerce_api.cart.domain.entities.Cart;
import com.devvictor.ecommerce_api.cart.infra.dtos.CartDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartEntityMapper {
    CartDTO toDto(Cart cart);
    Cart fromDto(CartDTO dto);
}
