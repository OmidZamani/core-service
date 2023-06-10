package com.boxi.crm.service.impl;

import com.boxi.core.errors.BusinessException;
import com.boxi.core.errors.EntityType;
import com.boxi.core.response.SelectResponse;
import com.boxi.crm.entity.CustomerSegment;
import com.boxi.crm.entity.SegmentCustomers;
import com.boxi.crm.payload.converter.CustomerSegmentConvert;
import com.boxi.crm.payload.converter.SegmentCustomersConverter;
import com.boxi.crm.payload.dto.CustomerSegmentDto;
import com.boxi.crm.payload.dto.SegmentCustomersDto;
import com.boxi.crm.payload.request.CustomerSegmentFilter;
import com.boxi.crm.repo.CustomerSegmentRepository;
import com.boxi.crm.repo.SegmentCustomersRepository;
import com.boxi.crm.service.CustomerSegmentService;
import com.boxi.feign.CustomerClient;
import com.boxi.feign.dto.CustomerDto;
import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CustomerSegmentServiceImpl implements CustomerSegmentService {
    private final CustomerSegmentRepository customerSegmentRepository;
    private final CustomerSegmentConvert customerSegmentConvert;

    private final SegmentCustomersRepository segmentCustomersRepository;
    private final SegmentCustomersConverter segmentCustomersConverter;
    private final CustomerClient customerClient;


    public CustomerSegmentServiceImpl(CustomerSegmentRepository customerSegmentRepository,
                                      CustomerSegmentConvert customerSegmentConvert,
                                      SegmentCustomersRepository segmentCustomersRepository,
                                      SegmentCustomersConverter segmentCustomersConverter,
                                      CustomerClient customerClient) {
        this.customerSegmentRepository = customerSegmentRepository;
        this.customerSegmentConvert = customerSegmentConvert;
        this.segmentCustomersRepository = segmentCustomersRepository;
        this.segmentCustomersConverter = segmentCustomersConverter;
        this.customerClient = customerClient;
    }

    @Override
    public List<SelectResponse> Select(String filter) {

        List<CustomerSegment> all = customerSegmentRepository.findAll((Specification<CustomerSegment>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));
            predicates.add(criteriaBuilder.equal(root.get("isActive"), true));
            if ( StringUtils.isNotBlank(filter))
                predicates.add(criteriaBuilder.equal(root.get("name"), "%" + filter + "%"));

            return criteriaBuilder.and(predicates.toArray(predicates.toArray(new Predicate[0])));
        });
        List<SelectResponse> selectResponses = new ArrayList<>();
        for (CustomerSegment segment : all) {
            selectResponses.add(new SelectResponse(segment.getId(), segment.getName()));
        }
        return selectResponses;

    }

    public Boolean isExists(String Code) {
        return customerSegmentRepository.existsByCode(Code);
    }

    @Override
    public CustomerSegmentDto create(CustomerSegmentDto customerSegmentDto) {

        return saveCreate(customerSegmentDto);

    }

    @Override
    public CustomerSegmentDto edit(CustomerSegmentDto customerSegmentDto) {
        return saveEdit(customerSegmentDto);
    }

    @Override
    public Page<CustomerSegmentDto> filter(CustomerSegmentFilter filter, Pageable pageable) {
        Page<CustomerSegment> all = customerSegmentRepository.findAll((Specification<CustomerSegment>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("isDeleted"), false));

            if (filter.getIsActive() != null)
                predicates.add(criteriaBuilder.equal(root.get("isActive"), filter.getIsActive()));

            if (StringUtils.isNotBlank(filter.getCode()))
                predicates.add(criteriaBuilder.like(root.get("code"), "%" + filter.getCode() + "%"));

            if (StringUtils.isNotBlank(filter.getName()))
                predicates.add(criteriaBuilder.like(root.get("name"), "%" + filter.getName() + "%"));

            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        }, pageable);
        return all.map(customerSegmentConvert::fromModelToDto);
    }

    @Override
    public CustomerSegmentDto findEdit(Long id) {
        CustomerSegment segment = customerSegmentRepository.findById(id).orElseThrow(() -> {
                    throw BusinessException.valueException(EntityType.CUSEOMERSEGMENT, "Customer.Segment.not.found");
                }
        );
        CustomerSegmentDto dto = customerSegmentConvert.fromModelToDto(segment);
        List<SegmentCustomersDto> segmentCustomersList = new ArrayList<>();

        for (SegmentCustomersDto segmentCustomer : dto.getSegmentCustomers()) {
            CustomerDto customerDto = customerClient.getfindById(segmentCustomer.getSelectcustomer().getId());
            segmentCustomer.setSelectcustomer(new SelectResponse(customerDto.getId(), customerDto.getName()));
            segmentCustomersList.add(segmentCustomer);
        }
        dto.setSegmentCustomers(segmentCustomersList);

        return dto;
    }

    @Override
    public void delete(Long id) {
        segmentCustomersRepository.deleteBySegment(customerSegmentRepository.findById(id).orElseThrow());
        customerSegmentRepository.logicalDelete(id);
    }

    public CustomerSegmentDto saveEdit(CustomerSegmentDto customerSegmentDto) {

        customerSegmentDto.setIsDeleted(false);
        CustomerSegment segment = customerSegmentRepository.save(customerSegmentConvert.fromDtoToModel(customerSegmentDto));
        List<SegmentCustomersDto> segmentCustomersList = new ArrayList<>();
        for (SegmentCustomersDto segmentCustomer : customerSegmentDto.getSegmentCustomers()) {
            SegmentCustomers segmentCustomers = segmentCustomersConverter.fromDtoToModel(segmentCustomer);
            segmentCustomers.setSegment(segment);
            segmentCustomers.setCustomerId(segmentCustomer.getSelectcustomer().getId());

            SegmentCustomers save = segmentCustomersRepository.save(segmentCustomers);
            segmentCustomersList.add(segmentCustomersConverter.fromModelToDto(save));
        }
        CustomerSegmentDto dto = customerSegmentConvert.fromModelToDto(segment);
        dto.setSegmentCustomers(segmentCustomersList);

        return dto;
    }

    public CustomerSegmentDto saveCreate(CustomerSegmentDto customerSegmentDto) {
        if (isExists(customerSegmentDto.getCode()))
            throw BusinessException.valueException(EntityType.CUSEOMERSEGMENT, "Customer.Segment.is.duplicate");

        customerSegmentDto.setIsDeleted(false);
        customerSegmentDto.setId(null);
        CustomerSegment customerSegment = customerSegmentConvert.fromDtoToModel(customerSegmentDto);


        CustomerSegment segment = customerSegmentRepository.save(customerSegment);
        List<SegmentCustomersDto> segmentCustomersList = new ArrayList<>();
        for (SegmentCustomersDto segmentCustomer : customerSegmentDto.getSegmentCustomers()) {
            SegmentCustomers segmentCustomers = new SegmentCustomers();
            segmentCustomers.setSegment(segment);
            segmentCustomers.setCustomerId(segmentCustomer.getSelectcustomer().getId());
            segmentCustomersList.add(segmentCustomersConverter.fromModelToDto(segmentCustomersRepository.save(segmentCustomers)));
        }

        CustomerSegmentDto dto = customerSegmentConvert.fromModelToDto(segment);
        dto.setSegmentCustomers(segmentCustomersList);
        return dto;
    }


}
