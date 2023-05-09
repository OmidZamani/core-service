package com.boxi.PriceList.service.impl;

import com.boxi.PriceList.entity.PriceDetailDevision;
import com.boxi.PriceList.entity.PriceList;
import com.boxi.PriceList.entity.PriceListDetail;
import com.boxi.PriceList.payload.dto.ConsignmentInfoDto;
import com.boxi.PriceList.payload.dto.SuggestionPriceDto;
import com.boxi.PriceList.repo.PriceListRepository;
import com.boxi.PriceList.payload.request.PriceListRequest;
import com.boxi.hub.entity.CustomCountryDevision;
import com.boxi.hub.entity.CustomDevisionDetail;
import com.boxi.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CalculatePriceService {

    @Autowired
    PriceListRepository priceListRepository;

    //TODO add product also to join ......join has unneccesary data


    public Optional<SuggestionPriceDto> priceSuggestion(ConsignmentInfoDto request) {
        List<PriceList> res = priceListRepository
                .findAll((Specification<PriceList>) (root, query, cb) -> {


                    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    List<Predicate> predicates = new ArrayList<>();
                    predicates.add(cb.equal(root.get("isDeleted"), false));
                    predicates.add(cb.equal(root.get("isActive"), true));

                    Date dayCheck = new Date();
                    if (request.getPickUpDate() != null) {
                        dayCheck = DateUtil.convertDateToJalaliDateDto(request.getPickUpDate());
                    }
                    predicates.add(cb.lessThanOrEqualTo(root.<Date>get("validDateFrom"), dayCheck));
                    predicates.add(cb.greaterThanOrEqualTo(root.<Date>get("validDateTo"), dayCheck));

                    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    Join<PriceList, PriceListDetail> priceListDetailJoin = root.join("priceListDetails");
                    predicates.add(cb.equal(priceListDetailJoin.get("isActive"), true));
                    predicates.add(cb.equal(priceListDetailJoin.get("isDeleted"), false));


                    //........................... weight check
                    Double weight = 0.0;
                    if (request.getDeclarativeWeight() != null) {
                        weight = request.getDeclarativeWeight();
                    } else {
                        weight = request.getWeight();
                    }
                    predicates.add(cb.lessThanOrEqualTo(priceListDetailJoin.get("fromWeight"), weight));
                    predicates.add(cb.greaterThanOrEqualTo(priceListDetailJoin.get("toWeight"), weight));

                    //...........................dimension check
                    Double dimension = 0.0;
                    if (request.getDeclarativeWidth() != null && request.getDeclarativeHeight() != null && request.getDeclarativeLength()!=null) {
                        dimension= request.getDeclarativeWidth()* request.getDeclarativeHeight()* request.getDeclarativeLength();
                        predicates.add(cb.lessThanOrEqualTo(priceListDetailJoin.get("fromDim"), dimension));
                        predicates.add(cb.greaterThanOrEqualTo(priceListDetailJoin.get("toDimension"), dimension));
                    }

                    //.......................... declare value check
                    BigDecimal d_value = BigDecimal.ZERO;
                    // بازه ارزشی-CHECK BY DECLARATION VALUE چک با ارزش اظهاری
                    if (request.getDeclarativeValue() != null ) {
                        d_value= request.getDeclarativeValue();
                        predicates.add(cb.lessThanOrEqualTo(priceListDetailJoin.<BigDecimal>get("fromValue"), d_value));
                        predicates.add(cb.greaterThanOrEqualTo(priceListDetailJoin.<BigDecimal>get("toValue"), d_value));
                    }

                    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    Join<PriceListDetail, PriceDetailDevision> priceDetailDevisionsJoin = priceListDetailJoin.join("priceDetailDevisions",JoinType.LEFT);
                    if(request.getFromStateId()!=null) {
                        predicates.add(cb.equal(priceDetailDevisionsJoin.get("fromCountryDevision").get("id"), request.getFromStateId()));
                    }
                    if(request.getFromCityId()!=null) {
                        predicates.add(cb.equal(priceDetailDevisionsJoin.get("fromCountryDevision").get("id"), request.getFromCityId()));
                    }
                    if(request.getFromRegionId()!=null) {
                        predicates.add(cb.equal(priceDetailDevisionsJoin.get("fromCountryDevision").get("id"), request.getFromRegionId()));
                    }


                    if(request.getToStateId()!=null) { //TODO May just region exist
                        predicates.add(cb.equal(priceDetailDevisionsJoin.get("toCountryDevision").get("id"), request.getToStateId()));
                    }
                    if(request.getToCityId()!=null) {
                        predicates.add(cb.equal(priceDetailDevisionsJoin.get("toCountryDevision").get("id"), request.getToCityId()));
                    }
                    if(request.getToRegionId()!=null) {
                        predicates.add(cb.equal(priceDetailDevisionsJoin.get("toCountryDevision").get("id"), request.getToRegionId()));
                    }

                    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    // join همجوار
                    Join<PriceListDetail, CustomCountryDevision> joinCustomCountryDevision = priceListDetailJoin.join("customCountryDevision", JoinType.LEFT);

                    Join<CustomCountryDevision,CustomDevisionDetail> customDevisionDetailsJoin=joinCustomCountryDevision.join("customDevisionDetails",JoinType.LEFT);
                    // Unable to locate Attribute  with the the given name [customDevisionDetails]

                    if(request.getFromStateId()!=null) {
                        predicates.add(cb.equal(customDevisionDetailsJoin.get("fromCountryDevision").get("id"), request.getFromStateId()));
                    }
                    if(request.getFromCityId()!=null) {
                        predicates.add(cb.equal(customDevisionDetailsJoin.get("fromCountryDevision").get("id"), request.getFromCityId()));
                    }
                    if(request.getFromRegionId()!=null) {
                        predicates.add(cb.equal(customDevisionDetailsJoin.get("fromCountryDevision").get("id"), request.getFromRegionId()));
                    }


                    if(request.getToStateId()!=null) { //TODO May just region exist
                        predicates.add(cb.equal(customDevisionDetailsJoin.get("toCountryDevision").get("id"), request.getToStateId()));
                    }
                    if(request.getToCityId()!=null) {
                        predicates.add(cb.equal(customDevisionDetailsJoin.get("toCountryDevision").get("id"), request.getToCityId()));
                    }
                    if(request.getToRegionId()!=null) {
                        predicates.add(cb.equal(customDevisionDetailsJoin.get("toCountryDevision").get("id"), request.getToRegionId()));
                    }

                    //......................................
                    query.distinct(true);

                    return cb.and(predicates.toArray(new Predicate[predicates.size()]));

                });
        return res.stream().map(this::mapToPriceList).findFirst();
    }

    private SuggestionPriceDto mapToPriceList(PriceList in){
        SuggestionPriceDto out=new SuggestionPriceDto();
        return out.setCode(in.getPriceListCode()).setName(in.getPriceListName()).setId(in.getId()).setPrice(in.getPriceListDetails().stream().map(PriceListDetail::getPrice).max(Comparator.naturalOrder()).get());
    }




}
