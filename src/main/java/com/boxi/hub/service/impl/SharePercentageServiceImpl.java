package com.boxi.hub.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.hub.entity.SharePercentage;
import com.boxi.hub.payload.converter.SharePercentageConverter;
import com.boxi.hub.payload.dto.SharePercentageDto;
import com.boxi.hub.repo.SharePercentageRepository;
import com.boxi.hub.service.SharePercentageService;
import org.springframework.stereotype.Service;

@Service
public class SharePercentageServiceImpl implements SharePercentageService {

    private final SharePercentageRepository sharePercentageRepository;
    private final SharePercentageConverter sharePercentageConverter;

    public SharePercentageServiceImpl(SharePercentageRepository sharePercentageRepository, SharePercentageConverter sharePercentageConverter) {
        this.sharePercentageRepository = sharePercentageRepository;
        this.sharePercentageConverter = sharePercentageConverter;
    }

    @Override
    public SharePercentageDto create(SharePercentageDto dto) {
        return saveCreate(dto);
    }

    private SharePercentageDto saveCreate(SharePercentageDto dto) {
        SharePercentage sharePercentage = sharePercentageConverter.fromDtoToModel(dto);
        sharePercentage.setIsDeleted(false);
        SharePercentage save = sharePercentageRepository.save(sharePercentage);
        return sharePercentageConverter.fromModelToDto(save);
    }

    @Override
    public SharePercentageDto edit(SharePercentageDto dto) {
        return saveEdit(dto);
    }

    @Override
    public SharePercentageDto findById(Long id) {
        if (isExists(id)) {
            return sharePercentageConverter.fromModelToDto(sharePercentageRepository.findById(id).orElseThrow());
        } else
            throw BusinessException.entityNotFoundException(EntityType.SharePercentage, "notDeliveredSMS");
    }

    private Boolean isExists(Long id) {
        return sharePercentageRepository.existsById(id);
    }

    private SharePercentageDto saveEdit(SharePercentageDto dto) {
        if (isExists(dto.getId())) {
            SharePercentage sharePercentage = sharePercentageConverter.fromDtoToModel(dto);
            sharePercentage.setIsDeleted(false);
            return sharePercentageConverter.fromModelToDto(sharePercentageRepository.save(sharePercentage));

        } else
            throw BusinessException.entityNotFoundException(EntityType.SharePercentage, "notDeliveredSMS");
    }


}
