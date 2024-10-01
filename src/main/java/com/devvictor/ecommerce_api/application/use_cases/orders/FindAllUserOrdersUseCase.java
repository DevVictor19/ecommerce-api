package com.devvictor.ecommerce_api.application.use_cases.orders;

import com.devvictor.ecommerce_api.application.dtos.orders.FindAllUserOrdersInputDTO;
import com.devvictor.ecommerce_api.application.dtos.orders.OrderOutputDTO;
import com.devvictor.ecommerce_api.application.mappers.OrderEntityMapper;
import com.devvictor.ecommerce_api.application.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindAllUserOrdersUseCase {
    private final OrderService orderService;
    private final OrderEntityMapper orderEntityMapper;

    public Page<OrderOutputDTO> execute(FindAllUserOrdersInputDTO dto) {
        return orderService.findRecentByUser(dto.userId(), dto.page(), dto.size())
                .map(orderEntityMapper::toDto);
    }
}
