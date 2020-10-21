package com.epam.esm.repository;

import com.epam.esm.entity.GiftCertificate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class GiftCertificateRepository extends EntityGiftRepository {
    RowMapper<GiftCertificate> GIFT_CERTIFICATE_ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new GiftCertificate(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getFloat("price"),
                resultSet.getDate("creationTime"),
                resultSet.getDate("updateTime"),
                resultSet.getInt("duration")
        );
    };

    public GiftCertificateRepository(DataSource dataSource) {
        super(dataSource);
    }


    public void addGiftCertificate(GiftCertificate certificate) {
        if (certificate.getId() == null) {
            jdbcTemplate.update("insert " +
                            "into gift_certificate (name,description,price,creationTime,updateTime,duration) " +
                            "values ( ?,?,?,?,?,?)",
                    certificate.getName(), certificate.getDescription(),
                    certificate.getPrice(), certificate.getCreationTime(),
                    certificate.getUpdateTime(), certificate.getDuration());
        }
    }

    @Transactional

    public int deleteGiftCertificate(GiftCertificate certificate) {
        return jdbcTemplate.update("delete from gift_certificate where id=?", certificate.getId());
    }


    public GiftCertificate findTagById(Integer id) {
        GiftCertificate certificate = jdbcTemplate.queryForObject("select * from gift_certificate where id = ?", new Object[]{id}, GIFT_CERTIFICATE_ROW_MAPPER);
        return certificate;
    }

    public List<GiftCertificate> getAllGiftCertificate() {
        return jdbcTemplate.query("select * from gift_certificate",GIFT_CERTIFICATE_ROW_MAPPER);
    }

}
