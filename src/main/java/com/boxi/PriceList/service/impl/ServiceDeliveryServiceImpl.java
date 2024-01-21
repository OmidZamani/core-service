package com.boxi.PriceList.service.impl;

import com.boxi.PriceList.Enum.DeliveryServiceType;
import com.boxi.PriceList.entity.DeliveryDiscount;
import com.boxi.PriceList.entity.ServiceDelivery;
import com.boxi.PriceList.entity.ServiceDeliveryCustomers;
import com.boxi.PriceList.entity.Services;
import com.boxi.PriceList.payload.converter.DeliveryDiscountConverter;
import com.boxi.PriceList.payload.converter.ServiceDeliveryConverter;
import com.boxi.PriceList.payload.converter.ServiceDeliveryCustomersConverter;
import com.boxi.PriceList.payload.dto.DeliveryDiscountDto;
import com.boxi.PriceList.payload.dto.ServiceDeliveryCustomersDto;
import com.boxi.PriceList.payload.dto.ServiceDeliveryDto;
import com.boxi.PriceList.payload.dto.ServiceDeliveryExcelDto;
import com.boxi.PriceList.payload.request.DiscountRequest;
import com.boxi.PriceList.payload.request.FilterServiceDelivery;
import com.boxi.PriceList.repo.DeliveryDiscountRepository;
import com.boxi.PriceList.repo.ServiceDeliveryCustomersRepository;
import com.boxi.PriceList.repo.ServiceDeliveryRepository;
import com.boxi.PriceList.repo.ServiceRepository;
import com.boxi.PriceList.service.ServiceDeliveryService;
import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.crm.entity.CustomerSegment;
import com.boxi.crm.entity.SalesChannel;
import com.boxi.crm.repo.CustomerSegmentRepository;
import com.boxi.crm.repo.SalesChannelRepository;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Transactional
public class ServiceDeliveryServiceImpl implements ServiceDeliveryService {
    private final ServiceDeliveryRepository serviceDeliveryRepository;
    private final ServiceDeliveryConverter serviceDeliveryConverter;
    private final ServiceDeliveryCustomersRepository serviceDeliveryCustomersRepository;
    private final ServiceDeliveryCustomersConverter serviceDeliveryCustomersConverter;
    private final CustomerSegmentRepository customerSegmentRepository;
    private final SalesChannelRepository salesChannelRepository;
    private final DeliveryDiscountRepository deliveryDiscountRepository;
    private final DeliveryDiscountConverter deliveryDiscountConverter;
    private final ServiceRepository service;

    public ServiceDeliveryServiceImpl(ServiceDeliveryRepository serviceDeliveryRepository,
                                      ServiceDeliveryConverter serviceDeliveryConverter,
                                      ServiceDeliveryCustomersRepository serviceDeliveryCustomersRepository,
                                      ServiceDeliveryCustomersConverter serviceDeliveryCustomersConverter,
                                      CustomerSegmentRepository customerSegmentRepository,
                                      SalesChannelRepository salesChannelRepository,
                                      DeliveryDiscountRepository deliveryDiscountRepository,
                                      DeliveryDiscountConverter deliveryDiscountConverter,
                                      ServiceRepository service) {

        this.serviceDeliveryRepository = serviceDeliveryRepository;
        this.serviceDeliveryConverter = serviceDeliveryConverter;
        this.serviceDeliveryCustomersRepository = serviceDeliveryCustomersRepository;
        this.serviceDeliveryCustomersConverter = serviceDeliveryCustomersConverter;
        this.customerSegmentRepository = customerSegmentRepository;
        this.salesChannelRepository = salesChannelRepository;

        this.deliveryDiscountRepository = deliveryDiscountRepository;
        this.deliveryDiscountConverter = deliveryDiscountConverter;
        this.service = service;

    }

    private ServiceDeliveryCustomers findServiceDeliveryCustomers(Long Id, ServiceDelivery serviceDelivery) {
        ServiceDeliveryCustomers byCustomerId = serviceDeliveryCustomersRepository.findByCustomerIdAndServiceDelivery(Id, serviceDelivery);
        if (byCustomerId == null) {
            ServiceDeliveryCustomers serviceDeliveryCustomers = new ServiceDeliveryCustomers();
            serviceDeliveryCustomers.setId(null);
            serviceDeliveryCustomers.setCustomerId(Id);
            serviceDeliveryCustomers.setServiceDelivery(serviceDelivery);


            return serviceDeliveryCustomers;
        } else
            return byCustomerId;

    }

    public ServiceDeliveryDto createDeliveryService(ServiceDeliveryDto request) {
        ServiceDelivery save = serviceDeliveryConverter.fromDtoToModel(request);
        save.setServiceType(DeliveryServiceType.findByValue(request.getType().getId()));
        save.setIsDeleted(false);
        save.setIsActive(true);

        ServiceDelivery save1 = serviceDeliveryRepository.save(save);

        for (DeliveryDiscountDto deliveryDiscount : request.getDeliveryDiscounts()) {
            if (deliveryDiscount.getDiscountFrom() != null) {
                DeliveryDiscount deliveryDiscount1 = deliveryDiscountConverter.fromDtoToModel(deliveryDiscount);
                deliveryDiscount1.setServiceDelivery(save1);
                deliveryDiscount1.setIsActive(true);
                deliveryDiscount1.setIsDeleted(false);
                deliveryDiscountRepository.save(deliveryDiscount1);


            }
        }

        List<ServiceDeliveryCustomersDto> serviceDeliveryCustomersList = new ArrayList<>();
        if(request.getServiceDeliveryCustomers()!=null)
        for (ServiceDeliveryCustomersDto serviceDeliveryCustomer : request.getServiceDeliveryCustomers()) {
            ServiceDeliveryCustomers serviceDeliveryCustomers = serviceDeliveryCustomersConverter.fromDtoToModel(serviceDeliveryCustomer);
            serviceDeliveryCustomers.setServiceDelivery(save);
            serviceDeliveryCustomers.setCustomerId(serviceDeliveryCustomer.getId());
            serviceDeliveryCustomers.setId(null);
            ServiceDeliveryCustomers save2 = serviceDeliveryCustomersRepository.save(serviceDeliveryCustomers);
            serviceDeliveryCustomersList.add(serviceDeliveryCustomersConverter.fromModelToDto(save2));
        }

        request.setServiceDeliveryCustomers(serviceDeliveryCustomersList);

        request.setId(save.getId());
        return request;
    }

    @Override
    public ServiceDeliveryDto create(ServiceDeliveryDto request) {
        return createDeliveryService(request);
    }

    @Override
    public Page<ServiceDeliveryDto> filter(FilterServiceDelivery filter, Pageable pageable) {

        Page<ServiceDelivery> all = serviceDeliveryRepository.findAll((Specification<ServiceDelivery>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            if (filter.getIsActive() == null)
                predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
            else
                predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));

            if (filter.getCode() != null && StringUtils.isNotBlank(filter.getCode()))
                predicates.add(criteriaBuilder.like(root.get("code"), "%" + filter.getCode().trim() + "%"));

            if (filter.getName() != null && StringUtils.isNotBlank(filter.getName()))
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName().trim() + "%"));

            if (filter.getType() != null && StringUtils.isNotBlank(String.valueOf(filter.getType())))
                predicates.add(criteriaBuilder.equal(root.get("type"), filter.getType().getId()));

            if (filter.getSaleschannels() != null) {

                Join<SalesChannel, ServiceDelivery> joinSales = root.join("saleschannels");

                List<Long> listIdOfSubjects = filter.getSaleschannels().stream().map(SelectResponse::getId).collect(Collectors.toList());

                predicates.add(criteriaBuilder.and(joinSales.get("Id").in(listIdOfSubjects)));

            }

            if (filter.getCustomerSegments() != null) {
                Join<CustomerSegment, ServiceDelivery> joinSales = root.join("customerSegments");
                List<Long> listIdOfSubjects = filter.getCustomerSegments().stream().map(SelectResponse::getId).collect(Collectors.toList());
                predicates.add(criteriaBuilder.and(joinSales.get("Id").in(listIdOfSubjects)));
            }

            if (filter.getServiceDeliveryCustomers() != null) {
                Join<ServiceDeliveryCustomers, ServiceDelivery> joinSales = root.join("serviceDeliveryCustomers");
                List<Long> listIdOfSubjects = filter.getServiceDeliveryCustomers().stream().map(SelectResponse::getId).collect(Collectors.toList());
                predicates.add(criteriaBuilder.and(joinSales.get("Id").in(listIdOfSubjects)));

            }

            if (filter.getService() != null)
                predicates.add(criteriaBuilder.equal(root.get("service"), filter.getService().getId()));
            query.orderBy(criteriaBuilder.desc(root.get("id")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);


        return all.map(serviceDeliveryConverter::fromModelToDto);
    }

    @Override
    public ServiceDeliveryDto SelectEdit(String filter) {
        return null;
    }

    public ServiceDelivery findById(Long id) {
        return serviceDeliveryRepository.findById(id).orElseThrow(() -> {
            throw BusinessException.entityNotFoundException(EntityType.ServiceDelivery, "service.delivery.code.not.exist");
        });

    }

    @Override
    public void delete(Long id) {
        serviceDeliveryRepository.LogicalDelete(id);
    }

    @Override
    public ServiceDeliveryDto edit(ServiceDeliveryDto request) {

        if (!serviceDeliveryRepository.existsById(request.getId()))
            throw BusinessException.valueException(EntityType.Service, "service.delivery.not.found");

        ServiceDelivery save = serviceDeliveryConverter.fromDtoToModel(request);
        save.removeAllCustomerSegments();
        save.removeAllDeliveryDiscounts();
        save.removeAllSaleschannels();
        List<ServiceDeliveryCustomers> serviceDeliveryCustomers = new ArrayList<>();
        for (ServiceDeliveryCustomersDto serviceDeliveryCustomer : request.getServiceDeliveryCustomers()) {
            serviceDeliveryCustomers.add(findServiceDeliveryCustomers(serviceDeliveryCustomer.getId(), save));

        }

        save.setServiceDeliveryCustomers(serviceDeliveryCustomers);
        for (SelectResponse salesChannel : request.getSaleschannels()) {
            SalesChannel salesChannels = salesChannelRepository.findById(salesChannel.getId()).orElseThrow(() -> {
                throw BusinessException.valueException(EntityType.ServiceDelivery, "sales.chanel.not.found");
            });

            save.addSaleschannels(salesChannels);
        }
        for (SelectResponse customerSegment : request.getCustomerSegments()) {
            CustomerSegment segment = customerSegmentRepository.findById(customerSegment.getId()).orElseThrow(() -> {
                throw BusinessException.valueException(EntityType.ServiceDelivery, "customer.segment.not.found");
            });

            save.addCustomerSegments(segment);
        }

        save.setIsDeleted(false);
        ServiceDelivery delivery = serviceDeliveryRepository.save(save);
        for (DeliveryDiscountDto deliveryDiscount : request.getDeliveryDiscounts()) {
            DeliveryDiscount deliveryDiscount1 = deliveryDiscountConverter.fromDtoToModel(deliveryDiscount);
            deliveryDiscount1.setServiceDelivery(delivery);
            deliveryDiscount1.setIsActive(true);
            deliveryDiscount1.setIsDeleted(false);
            DeliveryDiscount save2 = deliveryDiscountRepository.save(deliveryDiscount1);
            deliveryDiscount.setId(save2.getId());
        }

        return request;

    }

    @Override
    public boolean ExcelValidation(List<ServiceDeliveryExcelDto> exceptionExcelList) {
        int i = 1;
        for (ServiceDeliveryExcelDto serviceDeliveryExcelDto : exceptionExcelList) {
            if (serviceDeliveryExcelDto.getCode() != null) {
                if (serviceDeliveryRepository.existsByCode(serviceDeliveryExcelDto.getCode()))
                    throw BusinessException.valueException(EntityType.EXCEPTION,
                            "service.delivery.code.duplicate",
                            serviceDeliveryExcelDto.getCode() + "  ردیف " + i);
            }
            i++;
        }

        return true;
    }

    @Override
    public List<ServiceDeliveryDto> ImportExcel(List<ServiceDeliveryExcelDto> exceptionExcelList) {
        List<ServiceDeliveryDto> serviceDeliveryList = new ArrayList<>();
        for (ServiceDeliveryExcelDto serviceDeliveryExcelDto : exceptionExcelList) {

            if (serviceDeliveryExcelDto.getCode() != null) {
                ServiceDeliveryDto serviceDeliveryDto = serviceDeliveryConverter.fromExcelToDto(serviceDeliveryExcelDto);
                Services byCode = service.findByCode(serviceDeliveryDto.getService().getText());

                serviceDeliveryDto.setService(new SelectResponse(byCode.getId(), byCode.getName()));
                List<SelectResponse> selectResponses = new ArrayList<>();
                for (SelectResponse salesChannel : serviceDeliveryDto.getSaleschannels()) {
                    if (salesChannel.getText() != null) {
                        SalesChannel byCode1 = salesChannelRepository.findByCode(salesChannel.getText());
                        selectResponses.add(new SelectResponse(byCode1.getId(), byCode1.getName()));
                    }
                }
                serviceDeliveryDto.setSaleschannels(selectResponses);

                List<SelectResponse> selectResponses1 = new ArrayList<>();
                for (SelectResponse customerSegment : serviceDeliveryDto.getCustomerSegments()) {
                    if (customerSegment.getText() != null) {
                        CustomerSegment byCode1 = customerSegmentRepository.findByCode(customerSegment.getText());
                        selectResponses1.add(new SelectResponse(byCode1.getId(), byCode1.getName()));
                    }
                }
                serviceDeliveryDto.setCustomerSegments(selectResponses1);

                serviceDeliveryList.add(create(serviceDeliveryDto));

            }
        }
        return serviceDeliveryList;
    }

    @Override
    public void deleteDiscount(Long Id) {
        deliveryDiscountRepository.deleteById(Id);
    }

    @Override
    public BigDecimal findAllDiscountPercent(DiscountRequest dto) { //get all percent in list id
        List<ServiceDelivery> discountPercent = serviceDeliveryRepository.findAll((Specification<ServiceDelivery>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            predicates.add(criteriaBuilder.between(criteriaBuilder.literal(dto.getOrderDate()),
                    root.get("validDateFrom"),
                    root.get("validDateTo")));
            Join<Object, Object> serviceDeliveryCustomers = root.join("serviceDeliveryCustomers", JoinType.LEFT);
            Predicate cu = criteriaBuilder.and(criteriaBuilder.equal(serviceDeliveryCustomers.get("Id"), dto.getSenderCustomerId()));
            Predicate id = criteriaBuilder.and(criteriaBuilder.and(root.get("service").get("Id").in(dto.getServiceIds())));

            predicates.add(criteriaBuilder.or(cu, id));

            query.distinct(true);
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));

        });

        BigDecimal percents = new BigDecimal(0);
        for (ServiceDelivery serviceDelivery : discountPercent) {
            if (serviceDelivery != null)
                percents = percents.add(serviceDelivery.getDiscountPercent());
        }
        return percents;

    }


}
