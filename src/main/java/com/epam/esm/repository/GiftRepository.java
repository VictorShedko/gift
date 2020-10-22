package com.epam.esm.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class GiftRepository {

    protected JdbcTemplate jdbcTemplate;
    @Autowired
    public GiftRepository(DataSource dataSource) {
        this.jdbcTemplate=new JdbcTemplate(dataSource);
    }
}
