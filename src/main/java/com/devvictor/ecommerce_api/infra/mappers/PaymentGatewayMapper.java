package com.devvictor.ecommerce_api.infra.mappers;

import com.devvictor.ecommerce_api.domain.vo.ChargeVO;
import com.devvictor.ecommerce_api.domain.vo.CustomerVO;
import com.devvictor.ecommerce_api.infra.gateways.asaas.dtos.ChargeDTO;
import com.devvictor.ecommerce_api.infra.gateways.asaas.dtos.CustomerDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PaymentGatewayMapper {
    CustomerVO toCustomerVO(CustomerDTO dto);
    ChargeVO toChargeVO(ChargeDTO dto);
}
