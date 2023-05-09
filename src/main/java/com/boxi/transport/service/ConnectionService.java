package com.boxi.transport.service;


import com.boxi.core.response.SelectResponse;
import com.boxi.hub.entity.Hub;
import com.boxi.transport.entity.Connection;
import com.boxi.transport.entity.Route;
import com.boxi.transport.payload.dto.ConnectionDelDto;
import com.boxi.transport.payload.dto.ConnectionDto;
import com.boxi.transport.payload.dto.ConnectionFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ConnectionService {
    ConnectionDto create(ConnectionDto request);

    Connection findById(Long id);

    ConnectionDto findByIdDto(Long id);

    Connection findTopByHubAndRoute(Hub hub, Route route);

    List<Connection> findByRoute(Route route);

    void delete(Long id);

    Connection fromSelect(SelectResponse select) ;
    
    SelectResponse toSelect(Connection connection);

    List<Connection> saveAll(List<Connection> connections);

    void deleteAll(List<Connection> connections);

    Page<ConnectionDto> filter(ConnectionFilter request, Pageable pageable);

    void physicalDelete(ConnectionDelDto ids);
}
