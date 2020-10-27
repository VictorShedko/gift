package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.GiftCertificateTagRelationRepository;
import com.epam.esm.util.TimeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.cert.Certificate;
import java.util.List;

@Service
public class CertificateService {
    @Autowired
    private GiftCertificateRepository giftCertificateRepository;

    @Autowired
    private GiftCertificateTagRelationRepository relationRepository;

    public List<GiftCertificate> all() {
        List<GiftCertificate> certificates = giftCertificateRepository.getAllGiftCertificate();
        return certificates;
    }


    public GiftCertificate findById(Integer id) {
        return giftCertificateRepository.findTagById(id);
    }

    public List<Tag> tags(Integer id) {
        var cert = new GiftCertificate();
        cert.setId(id);
        return relationRepository.getTagsByCertificate(cert);
    }

    //TODO: add input params
    public GiftCertificate add(GiftCertificate certificate) {
        giftCertificateRepository.addGiftCertificate(certificate);
        return null;
    }

    public int delete(Integer id) {
        var cert = new GiftCertificate();
        cert.setId(id);
        return giftCertificateRepository.deleteGiftCertificate(cert);
    }

    public void attachTag(Tag tag, Integer certId) {
        relationRepository.attach(tag.getId(), certId);
    }

    public void detachTag(Tag tag, Integer certId) {
        relationRepository.detach(tag.getId(), certId);

    }

    public Tag tagByNumber(Integer certId, Integer number) {
        return tags(certId).get(number);
    }

    public int update(GiftCertificate certificate) {
        GiftCertificate certificateFromDB = findById(certificate.getId());
        certificateFromDB.update(certificate);
        certificateFromDB.setUpdateTime(TimeManager.now());
        return giftCertificateRepository.update(certificateFromDB);
    }

}
