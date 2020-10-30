package com.epam.esm.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.UniqFieldException;
import com.epam.esm.repository.util.RowMappers;

@Repository
public class GiftCertificateTagRelationRepository extends EntityGiftRepository {

    public GiftCertificateTagRelationRepository(DataSource dataSource) {
        super(dataSource);
    }

    public List<Tag> getTagsByCertificate(GiftCertificate certificate) {
        return jdbcTemplate.query(
                "select tag.id,tag.name " + "from tag join gift_to_tag " + "on tag.id=gift_to_tag.tag_id  "
                        + "where cert_id= ?", new Object[] {certificate.getId()}, RowMappers.TAG_ROW_MAPPER);
    }

    public List<GiftCertificate> getCertificateByTag(Tag tag) {
        return jdbcTemplate.query("select gift_certificate.id,gift_certificate.name,"
                        + "gift_certificate.description, gift_certificate.price,gift_certificate.creationTime,"
                        + "gift_certificate.updateTime,gift_certificate.duration " + "from gift_certificate join gift_to_tag "
                        + "on gift_certificate.id = gift_to_tag.cert_id " + "where tag_id= ?", new Object[] {tag.getId()},
                RowMappers.GIFT_CERTIFICATE_ROW_MAPPER);
    }

    @Transactional
    public void attach(int tagId, int certId) {
        try {
            jdbcTemplate.update("insert " + "into gift_to_tag (tag_id,cert_id) " + "values ( ?,?)", tagId, certId);
        } catch (DuplicateKeyException ex) {
            throw new UniqFieldException("tag - certificate");
        }
    }

    @Transactional
    public Integer detach(int tagId, int certId) {
        return jdbcTemplate.update("delete from gift_to_tag where tag_id=? AND cert_id=?", tagId, certId);
    }
}
