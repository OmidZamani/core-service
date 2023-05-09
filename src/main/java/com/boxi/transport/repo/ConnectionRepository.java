package com.boxi.transport.repo;

import com.boxi.hub.entity.Hub;
import com.boxi.transport.entity.Connection;
import com.boxi.transport.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Long>, JpaSpecificationExecutor<Connection> {
 Optional<Connection> findTopByHubAndRoute(Hub hub, Route route);

    List<Connection> findByRoute(Route route);
}
