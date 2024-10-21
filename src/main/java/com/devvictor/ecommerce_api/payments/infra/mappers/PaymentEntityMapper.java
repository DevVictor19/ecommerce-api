package com.devvictor.ecommerce_api.payments.infra.mappers;

import com.devvictor.ecommerce_api.shared.domain.vo.CardVO;
import com.devvictor.ecommerce_api.payments.infra.dtos.CardDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentEntityMapper {
    CardVO toCardVO(CardDTO dto);
}
