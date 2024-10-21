package com.devvictor.ecommerce_api.orders.infra.mappers;

import com.devvictor.ecommerce_api.orders.domain.entities.Order;
import com.devvictor.ecommerce_api.orders.infra.dtos.OrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderEntityMapper {
    OrderDTO toDto(Order order);
}
