package com.epam.esm.repository;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class GiftCertificateTagRelationRepository extends EntityGiftRepository {
    public GiftCertificateTagRelationRepository(DataSource dataSource) {
        super(dataSource);
    }

    public List<Tag> getTagsByCertificate(GiftCertificate certificate){
        return null;
    }

    public List<GiftCertificate> getCertificateByTag(Tag tag){
        return null;
    }

    public void attach(){

    }

    public Integer detach(){
        return null;
    }
}
