package com.boxi.PriceList.payload.converter;

import com.boxi.PriceList.entity.DeliveryDiscount;
import com.boxi.PriceList.payload.dto.DeliveryDiscountDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T13:34:49+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class DeliveryDiscountConverterImpl implements DeliveryDiscountConverter {

    @Override
    public DeliveryDiscount fromDtoToModel(DeliveryDiscountDto dto) {
        if ( dto == null ) {
            return null;
        }

        DeliveryDiscount deliveryDiscount = new DeliveryDiscount();

        if ( dto.getId() != null ) {
            deliveryDiscount.setId( dto.getId() );
        }
        if ( dto.getIsActive() != null ) {
            deliveryDiscount.setIsActive( dto.getIsActive() );
        }
        if ( dto.getIsDeleted() != null ) {
            deliveryDiscount.setIsDeleted( dto.getIsDeleted() );
        }
        if ( dto.getDiscountFrom() != null ) {
            deliveryDiscount.setDiscountFrom( dto.getDiscountFrom() );
        }
        if ( dto.getDiscountTo() != null ) {
            deliveryDiscount.setDiscountTo( dto.getDiscountTo() );
        }
        if ( dto.getDiscountPercent() != null ) {
            deliveryDiscount.setDiscountPercent( dto.getDiscountPercent() );
        }

        validate( dto, deliveryDiscount );

        return deliveryDiscount;
    }

    @Override
    public DeliveryDiscountDto fromModelToDto(DeliveryDiscount deliveryDiscount) {
        if ( deliveryDiscount == null ) {
            return null;
        }

        DeliveryDiscountDto deliveryDiscountDto = new DeliveryDiscountDto();

        if ( deliveryDiscount.getId() != null ) {
            deliveryDiscountDto.setId( deliveryDiscount.getId() );
        }
        if ( deliveryDiscount.getIsActive() != null ) {
            deliveryDiscountDto.setIsActive( deliveryDiscount.getIsActive() );
        }
        if ( deliveryDiscount.getIsDeleted() != null ) {
            deliveryDiscountDto.setIsDeleted( deliveryDiscount.getIsDeleted() );
        }
        if ( deliveryDiscount.getDiscountFrom() != null ) {
            deliveryDiscountDto.setDiscountFrom( deliveryDiscount.getDiscountFrom() );
        }
        if ( deliveryDiscount.getDiscountTo() != null ) {
            deliveryDiscountDto.setDiscountTo( deliveryDiscount.getDiscountTo() );
        }
        if ( deliveryDiscount.getDiscountPercent() != null ) {
            deliveryDiscountDto.setDiscountPercent( deliveryDiscount.getDiscountPercent() );
        }

        validateDto( deliveryDiscount, deliveryDiscountDto );

        return deliveryDiscountDto;
    }
}
