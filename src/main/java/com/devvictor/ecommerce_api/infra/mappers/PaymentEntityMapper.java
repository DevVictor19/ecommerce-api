package com.devvictor.ecommerce_api.infra.mappers;

import com.devvictor.ecommerce_api.domain.vo.CardVO;
import com.devvictor.ecommerce_api.infra.dtos.payments.CardDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentEntityMapper {
    CardVO toCardVO(CardDTO dto);
}
