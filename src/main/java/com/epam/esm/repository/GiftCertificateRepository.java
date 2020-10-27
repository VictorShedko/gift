package com.epam.esm.repository;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.GiftException;
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
                resultSet.getTimestamp("creationTime"),
                resultSet.getTimestamp("updateTime"),
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
        }else {
            throw new GiftException("test",1);
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
        return jdbcTemplate.query("select * from gift_certificate", GIFT_CERTIFICATE_ROW_MAPPER);
    }

    public int update(GiftCertificate certificate) {
        return jdbcTemplate.update("update gift.gift_certificate " +
                "set gift_certificate.name=?," +
                "gift_certificate.description=?," +
                "gift_certificate.price=?," +
                "gift_certificate.creationTime=?," +
                "gift_certificate.updateTime=?," +
                "gift_certificate.duration=?" +
                "where id=?", new Object[]{certificate.getName(), certificate.getDescription(),
                certificate.getPrice(), certificate.getCreationTime(), certificate.getUpdateTime(),
                certificate.getDuration(), certificate.getId()});
    }

}
