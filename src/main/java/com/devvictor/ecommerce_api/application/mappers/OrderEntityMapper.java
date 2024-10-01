package com.devvictor.ecommerce_api.application.mappers;

import com.devvictor.ecommerce_api.application.dtos.orders.OrderOutputDTO;
import com.devvictor.ecommerce_api.domain.entities.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderEntityMapper {
    OrderOutputDTO toDto(Order order);
    Order fromDto(OrderOutputDTO dto);
}
