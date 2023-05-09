package com.boxi.hub.service.specs;

import com.boxi.hub.entity.Hub;
import org.springframework.data.jpa.domain.Specification;


public class HubSpecifications {

    public static Specification<Hub> nameLike(String name){
         return(root, query, criteriaBuilder)->
             criteriaBuilder.like(root.get("name"), "%" + name.trim() + "%");
        };
}

