package com.boxi.product.payload.converter;

import com.boxi.core.response.SelectResponse;
import com.boxi.product.Enum.TimeUnit;
import com.boxi.product.entity.TimeCommitment;
import com.boxi.product.payload.dto.TimeCommitmentDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-17T11:51:36+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
public class TimeCommitmentConverterImpl implements TimeCommitmentConverter {

    @Override
    public TimeCommitment fromDtoToModel(TimeCommitmentDto dto) {
        if ( dto == null ) {
            return null;
        }

        TimeCommitment timeCommitment = new TimeCommitment();

        if ( dto.getId() != null ) {
            timeCommitment.setId( dto.getId() );
        }
        if ( dto.getName() != null ) {
            timeCommitment.setName( dto.getName() );
        }
        if ( dto.getFrom() != null ) {
            timeCommitment.setFrom( Double.parseDouble( dto.getFrom() ) );
        }
        if ( dto.getTo() != null ) {
            timeCommitment.setTo( Double.parseDouble( dto.getTo() ) );
        }
        String text = dtoSelecttedtimeUnitText( dto );
        if ( text != null ) {
            timeCommitment.setTimeUnit( Enum.valueOf( TimeUnit.class, text ) );
        }
        if ( dto.getIsActive() != null ) {
            timeCommitment.setIsActive( dto.getIsActive() );
        }
        if ( dto.getDescription() != null ) {
            timeCommitment.setDescription( dto.getDescription() );
        }
        if ( dto.getIsDeleted() != null ) {
            timeCommitment.setIsDeleted( dto.getIsDeleted() );
        }

        after( dto );

        return timeCommitment;
    }

    @Override
    public void updateFromDto(TimeCommitmentDto dto, TimeCommitment timeCommitment) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            timeCommitment.setId( dto.getId() );
        }
        else {
            timeCommitment.setId( null );
        }
        if ( dto.getName() != null ) {
            timeCommitment.setName( dto.getName() );
        }
        else {
            timeCommitment.setName( null );
        }
        if ( dto.getFrom() != null ) {
            timeCommitment.setFrom( Double.parseDouble( dto.getFrom() ) );
        }
        else {
            timeCommitment.setFrom( null );
        }
        if ( dto.getTo() != null ) {
            timeCommitment.setTo( Double.parseDouble( dto.getTo() ) );
        }
        else {
            timeCommitment.setTo( null );
        }
        String text = dtoSelecttedtimeUnitText( dto );
        if ( text != null ) {
            timeCommitment.setTimeUnit( Enum.valueOf( TimeUnit.class, text ) );
        }
        else {
            timeCommitment.setTimeUnit( null );
        }
        if ( dto.getIsActive() != null ) {
            timeCommitment.setIsActive( dto.getIsActive() );
        }
        else {
            timeCommitment.setIsActive( null );
        }
        if ( dto.getDescription() != null ) {
            timeCommitment.setDescription( dto.getDescription() );
        }
        else {
            timeCommitment.setDescription( null );
        }
        if ( dto.getIsDeleted() != null ) {
            timeCommitment.setIsDeleted( dto.getIsDeleted() );
        }
        else {
            timeCommitment.setIsDeleted( null );
        }

        after( dto );
    }

    @Override
    public TimeCommitmentDto fromModelToDto(TimeCommitment timeCommitment) {
        if ( timeCommitment == null ) {
            return null;
        }

        TimeCommitmentDto timeCommitmentDto = new TimeCommitmentDto();

        if ( timeCommitment != null ) {
            timeCommitmentDto.setSelecttedtimeUnit( timeCommitmentToSelectResponse( timeCommitment ) );
        }
        if ( timeCommitment.getId() != null ) {
            timeCommitmentDto.setId( timeCommitment.getId() );
        }
        if ( timeCommitment.getName() != null ) {
            timeCommitmentDto.setName( timeCommitment.getName() );
        }
        if ( timeCommitment.getFrom() != null ) {
            timeCommitmentDto.setFrom( String.valueOf( timeCommitment.getFrom() ) );
        }
        if ( timeCommitment.getTo() != null ) {
            timeCommitmentDto.setTo( String.valueOf( timeCommitment.getTo() ) );
        }
        if ( timeCommitment.getIsActive() != null ) {
            timeCommitmentDto.setIsActive( timeCommitment.getIsActive() );
        }
        if ( timeCommitment.getDescription() != null ) {
            timeCommitmentDto.setDescription( timeCommitment.getDescription() );
        }
        if ( timeCommitment.getIsDeleted() != null ) {
            timeCommitmentDto.setIsDeleted( timeCommitment.getIsDeleted() );
        }

        validate( timeCommitment, timeCommitmentDto );

        return timeCommitmentDto;
    }

    @Override
    public TimeCommitment selectToTimeCommitment(SelectResponse select) {
        if ( select == null ) {
            return null;
        }

        TimeCommitment timeCommitment = new TimeCommitment();

        if ( select.getId() != null ) {
            timeCommitment.setId( select.getId() );
        }

        return timeCommitment;
    }

    @Override
    public SelectResponse TimeCommitmentToSelect(TimeCommitment h) {
        if ( h == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( h.getId() != null ) {
            selectResponse.setId( h.getId() );
        }

        return selectResponse;
    }

    private String dtoSelecttedtimeUnitText(TimeCommitmentDto timeCommitmentDto) {
        if ( timeCommitmentDto == null ) {
            return null;
        }
        SelectResponse selecttedtimeUnit = timeCommitmentDto.getSelecttedtimeUnit();
        if ( selecttedtimeUnit == null ) {
            return null;
        }
        String text = selecttedtimeUnit.getText();
        if ( text == null ) {
            return null;
        }
        return text;
    }

    protected SelectResponse timeCommitmentToSelectResponse(TimeCommitment timeCommitment) {
        if ( timeCommitment == null ) {
            return null;
        }

        SelectResponse selectResponse = new SelectResponse();

        if ( timeCommitment.getTimeUnit() != null ) {
            selectResponse.setText( timeCommitment.getTimeUnit().name() );
        }

        return selectResponse;
    }
}
