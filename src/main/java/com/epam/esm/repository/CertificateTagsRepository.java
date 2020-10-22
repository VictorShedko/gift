package com.epam.esm.repository;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class CertificateTagsRepository extends GiftRepository{
    public CertificateTagsRepository(DataSource dataSource) {
        super(dataSource);
    }
}
