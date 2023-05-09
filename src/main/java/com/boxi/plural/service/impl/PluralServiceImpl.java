package com.boxi.plural.service.impl;

import com.boxi.PriceList.service.ExceptionService;
import com.boxi.core.response.SelectResponse;
import com.boxi.hub.service.HubService;
import com.boxi.plural.service.PluralService;
import com.boxi.plural.service.payload.ConsignmentFilterAsset;
import com.boxi.plural.service.payload.ConsignmentPluralRequest;
import com.boxi.transport.service.BagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
@Transactional
@Slf4j
public class PluralServiceImpl implements PluralService {

    final HubService hubService;
    final BagService bagService;
    final ExceptionService exceptionService;

    @Autowired
    public PluralServiceImpl(HubService hubService, BagService bagService, ExceptionService exceptionService) {
        this.hubService = hubService;
        this.bagService = bagService;
        this.exceptionService = exceptionService;
    }


    @Override
    public ConsignmentFilterAsset fetchData(ConsignmentPluralRequest r) {
        ConsignmentFilterAsset out = new ConsignmentFilterAsset();

        long deliveryHubId = r.getDeliveryHubId() != null ? r.getDeliveryHubId() : 0;
        long destinationHubId = r.getDestinationHubId() != null ? r.getDestinationHubId() : 0;
        long originHubId = r.getOriginHubId() != null ? r.getOriginHubId() : 0;
        long activeHubId = r.getActiveHubId() != null ? r.getActiveHubId() : 0;
        long nextHubId = r.getNextHubId() != null ? r.getNextHubId() : 0;

        List<Long> hubsIds = List.of(deliveryHubId, destinationHubId, originHubId, activeHubId, nextHubId);
        Map<Long, String> hubsMap = hubService.fetchHubsMapByIds(hubsIds);
        log.warn(hubsMap.toString());
        if(hubsMap.containsKey(deliveryHubId)) {
            out.setDeliveryHub(mapToSelect(deliveryHubId, hubsMap.get(deliveryHubId)));
        }
        if(hubsMap.containsKey(destinationHubId)) {
            out.setDestinationHub(mapToSelect(destinationHubId, hubsMap.get(destinationHubId)));
        }
        if(hubsMap.containsKey(originHubId)) {
            out.setOriginHub(mapToSelect(originHubId, hubsMap.get(originHubId)));
        }
        if(hubsMap.containsKey(activeHubId)) {
            out.setActiveHub(mapToSelect(activeHubId, hubsMap.get(activeHubId)));
        }
        if(hubsMap.containsKey(nextHubId)) {
            out.setNextHub(mapToSelect(nextHubId, hubsMap.get(nextHubId)));
        }
        if (r.getBagId() != null && r.getBagId()!=0) {
            out.setBag(mapToSelect(r.getBagId(), bagService.findById(r.getBagId()).selectToString()));
        }
        if (r.getLastExceptionId() != null && r.getLastExceptionId()!=0) {
            out.setBag(mapToSelect(r.getLastExceptionId(), exceptionService.findById(r.getLastExceptionId()).getName()));
        }
        return out;
    }


    SelectResponse mapToSelect(Long id, String txt) {
        return new SelectResponse().setId(id).setText(txt);
    }
}
