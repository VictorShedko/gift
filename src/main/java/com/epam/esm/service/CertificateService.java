package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.GiftCertificateTagRelationRepository;
import com.epam.esm.service.util.UpdateCertificateService;
import com.epam.esm.util.TimeManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        List<GiftCertificate> certificates = giftCertificateRepository.getAll();
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


    public GiftCertificate add(GiftCertificate certificate) {
        Date nowDate=TimeManager.now();
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

    public void attachTag(Tag tag, Integer certId) {
        relationRepository.attach(tag.getId(), certId);
    }

    public void detachTag(Tag tag, Integer certId) {
        relationRepository.detach(tag.getId(), certId);

    }

    public Tag tagByNumber(Integer certId, Integer number) {
        return tags(certId).get(number);
    }

    public int update(GiftCertificate pathCertificate) {
        GiftCertificate certificateFromDB = findById(pathCertificate.getId());
        updateCertificateService.updateCertificate(certificateFromDB,pathCertificate);
        return giftCertificateRepository.update(certificateFromDB);
    }

    public List<GiftCertificate> searchByName(String pattern){
        return giftCertificateRepository.searchByName(pattern);
    }

    public List<GiftCertificate> searchByDescription(String pattern){
        return giftCertificateRepository.searchByDescription(pattern);
    }

    public List<GiftCertificate> searchByAnyString(String pattern){
        List<GiftCertificate> allCertificates=searchByName(pattern);
        allCertificates.addAll(searchByDescription(pattern));
        return allCertificates;
    }

    public List<GiftCertificate> searchByTag(String tagName){
        Tag tag=tagService.findTagByName(tagName);
        return relationRepository.getCertificateByTag(tag);
    }

}
