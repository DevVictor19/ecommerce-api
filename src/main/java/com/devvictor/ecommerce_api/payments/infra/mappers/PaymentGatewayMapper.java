package com.devvictor.ecommerce_api.payments.infra.mappers;

import com.devvictor.ecommerce_api.shared.domain.vo.CardVO;
import com.devvictor.ecommerce_api.shared.domain.vo.ChargeVO;
import com.devvictor.ecommerce_api.shared.domain.vo.CustomerVO;
import com.devvictor.ecommerce_api.payments.infra.gateways.asaas.dtos.CardDTO;
import com.devvictor.ecommerce_api.payments.infra.gateways.asaas.dtos.ChargeDTO;
import com.devvictor.ecommerce_api.payments.infra.gateways.asaas.dtos.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentGatewayMapper {
    CustomerVO toCustomerVO(CustomerDTO dto);
    ChargeVO toChargeVO(ChargeDTO dto);
    CardDTO toCardDTO(CardVO vo);
}
