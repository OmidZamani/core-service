package com.boxi.transport.payload.converter;

import com.boxi.transport.entity.Vendor;
import com.boxi.transport.payload.dto.VendorDto;
import com.boxi.transport.payload.dto.VendorExcelDto;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-03-16T13:34:49+0330",
    comments = "version: 1.5.1.Final, compiler: javac, environment: Java 11.0.17 (IBM Corporation)"
)
@Component
@Primary
public class VendorConverterImpl extends VendorSelectDecorator {

    private final VendorConverter delegate;

    @Autowired
    public VendorConverterImpl(@Qualifier("delegate") VendorConverter delegate) {

        this.delegate = delegate;
    }

    @Override
    public Vendor fromDtoToModel(VendorDto dto)  {
        return delegate.fromDtoToModel( dto );
    }

    @Override
    public VendorDto fromModelToDto(Vendor model)  {
        return delegate.fromModelToDto( model );
    }

    @Override
    public VendorDto fromExcelDto(VendorExcelDto model)  {
        return delegate.fromExcelDto( model );
    }
}
