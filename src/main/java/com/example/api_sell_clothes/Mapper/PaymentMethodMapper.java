package com.example.api_sell_clothes.Mapper;

import com.example.api_sell_clothes.DTO.PaymentMethodsDTO;
import com.example.api_sell_clothes.Entity.PaymentMethods;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMethodMapper implements EntityMapper<PaymentMethods, PaymentMethodsDTO> {

    @Override
    public PaymentMethods toEntity(PaymentMethodsDTO dto) {
        if (dto == null) {
            return null;
        }
        return PaymentMethods.builder()
                .paymentMethodId(dto.getPaymentMethodId())
                .methodName(dto.getMethodName())
                .createdAt(dto.getCreatedAt())
                .updatedAt(dto.getUpdatedAt())
                .build();
    }

    @Override
    public PaymentMethodsDTO toDto(PaymentMethods entity) {
        if (entity == null) {
            return null;
        }
        return PaymentMethodsDTO.builder()
                .paymentMethodId(entity.getPaymentMethodId())
                .methodName(entity.getMethodName())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    @Override
    public List<PaymentMethods> toEntity(List<PaymentMethodsDTO> dtoList) {
        if (dtoList == null) {
            return null;
        }
        return dtoList.stream().map(this::toEntity).collect(Collectors.toList());
    }

    @Override
    public List<PaymentMethodsDTO> toDto(List<PaymentMethods> entityList) {
        if (entityList == null) {
            return null;
        }
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }
}
