package com.epam.esm.config;

import javax.sql.DataSource;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.GiftCertificateTagRelationRepository;
import com.epam.esm.repository.TagRepository;

@Configuration
@ComponentScan(basePackages = "com.epam.esm")
@ExtendWith(SpringExtension.class)
public class TestConfig {

    @Profile("test")
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                .addScript("create_script.sql")
                .addScript("dta.sql")
                .build();
    }

    public GiftCertificateRepository giftCertificateRepository(@Autowired DataSource dataSource) {
        return new GiftCertificateRepository(dataSource);
    }

    public TagRepository tagRepository(@Autowired DataSource dataSource) {
        return new TagRepository(dataSource);
    }

    public GiftCertificateTagRelationRepository relationRepository(@Autowired DataSource dataSource) {
        return new GiftCertificateTagRelationRepository(dataSource);
    }

}
