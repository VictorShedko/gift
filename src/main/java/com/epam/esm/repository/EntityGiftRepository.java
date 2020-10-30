package com.epam.esm.repository;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

public abstract class EntityGiftRepository {

    protected JdbcTemplate jdbcTemplate;

    @Autowired
    public EntityGiftRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
