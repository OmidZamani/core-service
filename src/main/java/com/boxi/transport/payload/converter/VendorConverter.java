package com.boxi.transport.payload.converter;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.errors.ExceptionType;
import com.boxi.core.response.SelectResponse;
import com.boxi.transport.entity.Vendor;
import com.boxi.transport.payload.dto.VendorDto;
import com.boxi.transport.payload.dto.VendorExcelDto;
import com.boxi.transport.repo.VendorRepository;
import com.boxi.utils.SelectUtil;
import org.mapstruct.*;
import org.springframework.beans.factory.annotation.Autowired;


@Mapper(componentModel = "spring", uses = {VendorRepository.class}, injectionStrategy = InjectionStrategy.CONSTRUCTOR, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
@DecoratedWith(VendorSelectDecorator.class)
public interface VendorConverter {

    Vendor fromDtoToModel(final VendorDto dto);

    VendorDto fromModelToDto(final Vendor model);

    VendorDto fromExcelDto(final VendorExcelDto model);

    SelectResponse fromModelToSelect(Vendor vendor);

    Vendor fromSelectToModel(SelectResponse select);

}

abstract class VendorSelectDecorator implements VendorConverter {

    @Autowired
    private VendorRepository vendorRepository;

    @Override
    public SelectResponse fromModelToSelect(Vendor vendor) {
        return new SelectResponse(vendor.getId(), vendor.selectToString());
    }

    @Override
    public Vendor fromSelectToModel(SelectResponse select) {
        if (SelectUtil.NZ_CHECK(select)) return null;
        return vendorRepository.findById(select.getId()).orElseThrow(() -> {
            throw BusinessException.throwException(EntityType.Vendor, ExceptionType.ENTITY_NOT_FOUND);
        });


    }
}
