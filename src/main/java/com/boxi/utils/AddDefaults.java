package com.boxi.utils;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;


@Component
@Slf4j
public class AddDefaults {


    @Autowired
    private DataSource dataSource;

    @Transactional
    public void initSql() {
        try {
         ResourceDatabasePopulator resourceDatabasePopulator = new ResourceDatabasePopulator(false, true, "UTF-8", new ClassPathResource("scripts/province_city_loc.sql"));
            resourceDatabasePopulator.execute(dataSource);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
