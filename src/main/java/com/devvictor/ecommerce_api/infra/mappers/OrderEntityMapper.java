package com.devvictor.ecommerce_api.infra.mappers;

import com.devvictor.ecommerce_api.domain.entities.Order;
import com.devvictor.ecommerce_api.infra.dtos.orders.OrderDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderEntityMapper {
    OrderDTO toDto(Order order);
    Order fromDto(OrderDTO dto);
}
