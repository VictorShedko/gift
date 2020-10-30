package com.epam.esm.repository;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.exception.GiftException;
import com.epam.esm.exception.IdInInsertException;
import com.epam.esm.exception.ResourceNotFoundedException;
import com.epam.esm.repository.util.RowMappers;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class GiftCertificateRepository extends EntityGiftRepository {


    public GiftCertificateRepository(DataSource dataSource) {
        super(dataSource);
    }

    @Transactional
    public void add(GiftCertificate certificate) {
        if (certificate.getId() == null) {
            jdbcTemplate.update("insert " +
                            "into gift_certificate (name,description,price,creationTime,updateTime,duration) " +
                            "values ( ?,?,?,?,?,?)",
                    certificate.getName(), certificate.getDescription(),
                    certificate.getPrice(), certificate.getCreationTime(),
                    certificate.getUpdateTime(), certificate.getDuration());
        } else {
            throw new IdInInsertException();
        }
    }

    @Transactional
    public int delete(GiftCertificate certificate) {
        return jdbcTemplate.update("delete from gift_certificate where id=?", certificate.getId());
    }


    public GiftCertificate findTagById(Integer id) {
        try {
            return jdbcTemplate.queryForObject("select * from gift_certificate where id = ?",
                    new Object[]{id}, RowMappers.GIFT_CERTIFICATE_ROW_MAPPER);
        } catch (EmptyResultDataAccessException exception) {
            throw new ResourceNotFoundedException("certificate", " id=" + id);
        }

    }

    public List<GiftCertificate> getAll() {
        return jdbcTemplate.query("select * from gift_certificate " +
                "ORDER BY name,creationTime", RowMappers.GIFT_CERTIFICATE_ROW_MAPPER);
    }

    @Transactional
    public int update(GiftCertificate certificate) {
        try {
            return jdbcTemplate.update("update gift.gift_certificate " +
                            "set gift_certificate.name=?," +
                            "gift_certificate.description=?," +
                            "gift_certificate.price=?," +
                            "gift_certificate.creationTime=?," +
                            "gift_certificate.updateTime=?," +
                            "gift_certificate.duration=? " +
                            "where id=?", certificate.getName(), certificate.getDescription(),
                    certificate.getPrice(), certificate.getCreationTime(), certificate.getUpdateTime(),
                    certificate.getDuration(), certificate.getId());

        } catch (DataAccessException ex) {
            throw new GiftException();
        }

    }

    public List<GiftCertificate> searchByName(String pattern) {
            return jdbcTemplate.query("select * " +
                "from gift_certificate " +
                "where name " +
                "LIKE ?" +
                "ORDER BY name,creationTime", new Object[]{"%" + pattern + "%"}, RowMappers.GIFT_CERTIFICATE_ROW_MAPPER);

    }

    public List<GiftCertificate> searchByDescription(String pattern) {
        return jdbcTemplate.query("select * " +
                        "from gift_certificate " +
                        "where description " +
                        "LIKE ?" +
                        "ORDER BY name,creationTime", new Object[]{"%" + pattern + "%"},
                RowMappers.GIFT_CERTIFICATE_ROW_MAPPER);
    }
}
