package com.epam.esm.repository;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.repository.util.RowMappers;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class GiftCertificateTagRelationRepository extends EntityGiftRepository {
    public GiftCertificateTagRelationRepository(DataSource dataSource) {
        super(dataSource);
    }

    public List<Tag> getTagsByCertificate(GiftCertificate certificate) {
        return jdbcTemplate.query("select (tag.id,tag.name) from " +
                "tag join gift_to_tag on tag.id " +
                "where cert_id= ?", new Object[]{certificate.getId()}, RowMappers.TAG_ROW_MAPPER);
    }

    public List<GiftCertificate> getCertificateByTag(Tag tag) {
        return null;
    }

    public void attach(int tagId, int certId) {
        jdbcTemplate.update("insert " +
                "into gift_to_tag (tag_id,cert_id) " +
                "values ( ?,?)", tagId, certId);

    }

    public Integer detach(int tagId, int certId) {
        return jdbcTemplate.update("delete from gift_to_tag where tag_id=? AND cert_id=?",
                tagId, certId);
    }
}
