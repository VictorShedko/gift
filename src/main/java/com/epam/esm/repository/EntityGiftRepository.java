package com.epam.esm.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class EntityGiftRepository {

    protected JdbcTemplate jdbcTemplate;
    @Autowired
    public EntityGiftRepository(DataSource dataSource) {
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }
}
