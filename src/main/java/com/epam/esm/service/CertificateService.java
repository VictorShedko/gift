package com.epam.esm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.GiftCertificateTagRelationRepository;
import com.epam.esm.service.util.UpdateCertificateService;
import com.epam.esm.util.TimeManager;

@Service
public class CertificateService {

    @Autowired
    private GiftCertificateRepository giftCertificateRepository;

    @Autowired
    private GiftCertificateTagRelationRepository relationRepository;

    @Autowired
    private TagService tagService;

    @Autowired
    private UpdateCertificateService updateCertificateService;

    public List<GiftCertificate> all() {
        List<GiftCertificate> certificates = giftCertificateRepository.all();
        return certificates;
    }

    public GiftCertificate findById(Integer id) {
        return giftCertificateRepository.findById(id);
    }

    public GiftCertificate add(GiftCertificate certificate) {
        Date nowDate = TimeManager.now();
        certificate.setCreationTime(nowDate);
        certificate.setUpdateTime(nowDate);
        giftCertificateRepository.add(certificate);
        return null;
    }

    public int delete(Integer id) {
        var cert = new GiftCertificate();
        cert.setId(id);
        return giftCertificateRepository.delete(cert);
    }

    public void attachTag(Integer tagId, Integer certId) {
        relationRepository.attach(tagId, certId);
    }

    public int detachTag(Integer tagId, Integer certId) {
        return relationRepository.detach(tagId, certId);

    }

    public Tag tagByNumber(Integer certId, Integer number) {
        return tags(certId).get(number);
    }

    public int update(GiftCertificate pathCertificate) {
        GiftCertificate certificateFromDB = findById(pathCertificate.getId());
        updateCertificateService.updateCertificate(certificateFromDB, pathCertificate);
        return giftCertificateRepository.update(certificateFromDB);
    }

    public List<GiftCertificate> searchByName(String pattern) {
        return giftCertificateRepository.searchByName(pattern);
    }

    public List<GiftCertificate> searchByDescription(String pattern) {
        return giftCertificateRepository.searchByDescription(pattern);
    }

    public List<GiftCertificate> searchByAnyString(String pattern) {
        return giftCertificateRepository.searchByAnyString(pattern);
    }

    public List<GiftCertificate> searchByTag(String tagName) {
        Tag tag = tagService.findTagByName(tagName);
        return relationRepository.getCertificateByTag(tag);
    }

    public List<Tag> tags(Integer id) {
        var cert = new GiftCertificate();
        cert.setId(id);
        return relationRepository.getTagsByCertificate(cert);
    }

}
