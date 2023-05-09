package com.boxi.hub.service.impl;

import com.boxi.hub.entity.Hub;
import com.boxi.hub.payload.dto.HubPermissionDto;
import com.boxi.hub.repo.HubRepository;
import lombok.extern.slf4j.Slf4j;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class ListofHubPermition {
    private final HubRepository hubRepository;

    private List<HubPermissionDto> listOfHubPermissionarr = new ArrayList<>();

    public ListofHubPermition(HubRepository hubRepository) {
        this.hubRepository = hubRepository;
    }

    public List<HubPermissionDto> findchild(Long ParentId) {
        List<HubPermissionDto> list = new ArrayList<>();

        try {
            for (HubPermissionDto hubPermissionDto : listOfHubPermissionarr) {
                if (hubPermissionDto.getParent().equals(ParentId)) {
                    hubPermissionDto.setChildren(findchild(hubPermissionDto.getId()));
                    list.add(hubPermissionDto);
                }


            }
        } catch (Exception e) {

        }
        return list;

    }

    public List<HubPermissionDto> listOfHubPermission(List<HubPermissionDto> hubPermissionDtos) {
        List<HubPermissionDto> list = new ArrayList<>();

//        List<HubPermissionDto> parentlist = listOfHubPermissionarr.stream() //TODO
//                .filter(c -> c.getParent() == 0)
//                .collect(Collectors.toList());
//
//
//        if(parentlist.size()!=0) {
//            for (HubPermissionDto hubPermissionDto : parentlist) {
//                hubPermissionDto.setChildren(findchild(hubPermissionDto.getId()));
//
//                list.add(hubPermissionDto);
//            }
//            return list;
//        }
//        else
//        {
//            return hubPermissionDtos;
//        }
        return hubPermissionDtos;
    }

    public List<HubPermissionDto> listOfHubPermission(String[] strings) {
        listOfHubPermissionarr.clear();
        for (String string : strings) {
            log.warn(string);
            List<Hub> byCodeLike = hubRepository.findByCodeLike(string);
            if (byCodeLike != null)
                for (Hub hub : byCodeLike) {
                    HubPermissionDto hubPermissionDto = new HubPermissionDto();
                    hubPermissionDto.setId(hub.getId());
                    hubPermissionDto.setValue(hub.getCode());
                    hubPermissionDto.setLabel(hub.getName());
                    if (hub.getParentHub() != null)
                        hubPermissionDto.setParent(hub.getParentHub().getId());
                    else
                        hubPermissionDto.setParent(0L);
                    listOfHubPermissionarr.add(hubPermissionDto);
                }
        }
        List<HubPermissionDto> list = listOfHubPermission(listOfHubPermissionarr);
//        listOfHubPermissionarr.clear(); //TODO
        return list;
    }
}
