package com.boxi.PriceList.service.impl;

import com.boxi.PriceList.Enum.ServiceType;
import com.boxi.PriceList.entity.PriceList;
import com.boxi.PriceList.entity.Services;
import com.boxi.PriceList.payload.converter.PriceListConverter;
import com.boxi.PriceList.payload.converter.ServiceConvertor;
import com.boxi.PriceList.payload.dto.PriceListDto;
import com.boxi.PriceList.payload.dto.ServiceDto;
import com.boxi.PriceList.repo.PriceListRepository;
import com.boxi.PriceList.repo.ServiceDeliveryCustomersRepository;
import com.boxi.PriceList.repo.ServiceDeliveryRepository;
import com.boxi.PriceList.repo.ServiceRepository;
import com.boxi.PriceList.payload.request.FilterService;
import com.boxi.PriceList.service.ServiceService;
import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.excel.payload.CreateServiceExcelRequest;
import com.boxi.product.entity.Product;
import com.boxi.product.repo.ProductRepository;
import com.boxi.utils.DateUtil;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ServiceServiceImpl implements ServiceService {

    private final ServiceRepository serviceRepository;
    private final ServiceConvertor serviceConvertor;
    private final PriceListRepository priceListRepository;
    private final PriceListConverter priceListConverter;

    private final ProductRepository productRepository;
    private final ServiceDeliveryRepository serviceDeliveryRepository;
    private final ServiceDeliveryCustomersRepository serviceDeliveryCustomersRepository;


    public ServiceServiceImpl(ServiceRepository serviceRepository,
                              ServiceConvertor serviceConvertor,
                              PriceListRepository priceListRepository,
                              PriceListConverter priceListConverter,
                              ProductRepository productRepository,
                              ServiceDeliveryRepository serviceDeliveryRepository,
                              ServiceDeliveryCustomersRepository serviceDeliveryCustomersRepository) {
        this.serviceRepository = serviceRepository;
        this.serviceConvertor = serviceConvertor;
        this.priceListRepository = priceListRepository;
        this.priceListConverter = priceListConverter;
        this.productRepository = productRepository;
        this.serviceDeliveryRepository = serviceDeliveryRepository;
        this.serviceDeliveryCustomersRepository = serviceDeliveryCustomersRepository;
    }

    public ServiceDto createService(ServiceDto request) {
        if (serviceRepository.existsByCode(request.getCode())) {
            throw BusinessException.valueException(EntityType.Service, "service.delivery.code.duplicate");
        }

        PriceList priceList = priceListRepository.findById(request.getPriceList().getId()).orElseThrow(() -> {
            throw BusinessException.valueException(EntityType.Service, "price.list.code.not.exist");
        });
        PriceListDto priceListDto = priceListConverter.fromModelToDto(priceList);

        Date servicefromdate = DateUtil.convertDateToJalaliDateDto(request.getValidDateFrom());
        Date servicetodate = DateUtil.convertDateToJalaliDateDto(request.getValidDateTo());

        Date pricelisttodate = DateUtil.convertDateToJalaliDateDto(priceListDto.getValidDateTo());

        Date pricelistfromdate = DateUtil.convertDateToJalaliDateDto(priceListDto.getValidDateFrom());

        if (servicefromdate.compareTo(servicetodate) > 0) {
            throw BusinessException.valueException(EntityType.Service, "price.list.fromdate.notvalid");
        }

        if (servicefromdate.compareTo(pricelistfromdate) < 0) {
            throw BusinessException.valueException(EntityType.Service, "price.list.fromdate.notvalid");
        }


        if (servicetodate.compareTo(pricelisttodate) > 0) {
            throw BusinessException.valueException(EntityType.Service, "price.list.fromdate.notvalid");
        }

        request.setId(null);
        request.setIsDeleted(false);

        Services save = serviceRepository.save(serviceConvertor.fromDtoToModel(request));
        request.setId(save.getId());

        return request;
    }

    @Override
    public ServiceDto create(ServiceDto request) {
        return createService(request);
    }

    @Override
    public Page<ServiceDto> filter(FilterService filter, Pageable pageable) {

        Page<Services> all = serviceRepository.findAll((Specification<Services>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            if (filter.getIsActive() != null)
                predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));

            if (filter.getCode() != null && StringUtils.isNotBlank(filter.getCode()))
                predicates.add(criteriaBuilder.like(root.get("code"), "%" + filter.getCode().trim() + "%"));

            if (filter.getName() != null && StringUtils.isNotBlank(filter.getName()))
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName().trim() + "%"));

            if (filter.getProduct() != null) {
                predicates.add(criteriaBuilder.equal(root.get("product"), filter.getProduct().getId()));
            }

            if (filter.getType() != null)
                predicates.add(criteriaBuilder.equal(root.get("type"), filter.getType().getId()));

            if (filter.getPriceList() != null)
                predicates.add(criteriaBuilder.equal(root.get("priceList"), filter.getPriceList().getId()));


            if (filter.getDescription() != null)
                predicates.add(criteriaBuilder.like(root.get("description"), filter.getDescription()));

            query.orderBy(criteriaBuilder.desc(root.get("Id")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

        }, pageable);


        return all.map(serviceConvertor::fromModelToDto);

    }

    @Override
    public ServiceDto SelectEdit(String filter) {
        return null;
    }

    @Override
    public void delete(Long id) {
        serviceRepository.deleteById(id);
    }

    @Override
    public void deletedetails(Long id) {
        serviceDeliveryCustomersRepository.deleteById(id);
    }

    @Override
    public ServiceDto edit(ServiceDto request) {
        if (!serviceRepository.existsById(request.getId()))
            throw BusinessException.valueException(EntityType.Service, "service.not.exist");

        request.setIsDeleted(false);
        Services save = serviceRepository.save(serviceConvertor.fromDtoToModel(request));
        return serviceConvertor.fromModelToDto(save);
    }

    @Override
    public List<ServiceDto> ImportExcel(List<CreateServiceExcelRequest> createServiceExcelRequests) {
        List<ServiceDto> serviceDtos = new ArrayList<>();

        for (CreateServiceExcelRequest createServiceExcelRequest : createServiceExcelRequests) {

            if (createServiceExcelRequest.getCode() != null) {
                ServiceDto serviceDto = serviceConvertor.fromExcelToDto(createServiceExcelRequest);

                PriceList byPriceListName = priceListRepository.findByPriceListCode(serviceDto.getPriceList().getText());
                serviceDto.setPriceList(new SelectResponse(byPriceListName.getId(), byPriceListName.getPriceListName()));

                Product byCode = productRepository.findByCode(serviceDto.getProduct().getText());
                serviceDto.setProduct(new SelectResponse(byCode.getId(), byCode.getName()));
                serviceDto.setIsDeleted(false);

                Services save = serviceRepository.save(serviceConvertor.fromDtoToModel(serviceDto));
                ServiceDto serviceDto1 = serviceConvertor.fromModelToDto(save);
                serviceDto1.setProduct(serviceDto.getProduct());
                serviceDto1.setPriceList(serviceDto.getPriceList());


                serviceDtos.add(serviceDto1);

            }


        }
        return serviceDtos;

    }

    @Override
    public boolean ExcelValidation(List<CreateServiceExcelRequest> createServiceExcelRequests) {
        int i = 1;
        for (CreateServiceExcelRequest createServiceExcelRequest : createServiceExcelRequests) {
            if (createServiceExcelRequest.getCode() != null) {
                if (serviceRepository.existsByCode(createServiceExcelRequest.getCode())) {

                    throw BusinessException.valueException(EntityType.Service,
                            "service.code.not.exist", createServiceExcelRequest.getCode() + "  ردیف " + i);

                }
                Product byCode = productRepository.findByCode(createServiceExcelRequest.getProduct());
                if (byCode == null) {
                    throw BusinessException.valueException(EntityType.Service,
                            "product.code.not.exist", createServiceExcelRequest.getProduct() + "  ردیف " + i);
                }
                PriceList byPriceListCode = priceListRepository.findByPriceListCode(createServiceExcelRequest.getPriceList());

                if (byPriceListCode == null) {
                    throw BusinessException.valueException(EntityType.Service,
                            "price.list.code.not.exist", createServiceExcelRequest.getProduct() + "  ردیف  " + i);
                }
            }
            i++;
        }

        return true;
    }

    @Override
    public List<SelectResponse> select(String filter) {
        List<Services> all = serviceRepository.findAll((Specification<Services>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (filter != null && StringUtils.isNotBlank(filter))
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.trim() + "%"));


            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        });
        List<SelectResponse> selectResponses = new ArrayList<>();
        for (Services services : all) {
            selectResponses.add(new SelectResponse(services.getId(), services.getName()));
        }
        return selectResponses;

    }

    @Override
    public ServiceDto findByPricelist(PriceListDto dto) {
        Services byPriceList = serviceRepository.findByPriceList(priceListConverter.fromDtoToModel(dto));
        return serviceConvertor.fromModelToDto(byPriceList);
    }

    public List<SelectResponse> baseTypeSelect() {
        List<Services> servicesList = serviceRepository.findByTypeAndIsActiveIsTrue(ServiceType.BASE.getValue());
        return servicesList.stream().map(this::toSelect).collect(Collectors.toList());
    }

    public List<SelectResponse> additionalTypeSelect() {
        List<Services> servicesList = serviceRepository.findByTypeAndIsActiveIsTrue(ServiceType.ADDITIONAL.getValue());
        return servicesList.stream().map(this::toSelect).collect(Collectors.toList());
    }

    @Override
    public SelectResponse toSelect(Services services) {
        return new SelectResponse(services.getId(), services.getName());
    }

    @Override
    public ServiceDto findByid(Long id) {

        return serviceConvertor.fromModelToDto(serviceRepository.findById(id).orElseThrow());
    }

}
