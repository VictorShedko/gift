package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.GiftCertificateTagRelationRepository;
import com.epam.esm.repository.TagRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiftCertificateService {
    @Autowired
    private GiftCertificateRepository giftCertificateRepository;

    @Autowired
    private GiftCertificateTagRelationRepository relationRepository;

    public List<GiftCertificate> getAllCertificates() {
        return giftCertificateRepository.getAllGiftCertificate();
    }

    public GiftCertificate findCertificateById(Integer id) {
        return giftCertificateRepository.findTagById(id);
    }

    public List<Tag> getCertificatesByTag(Integer id) {
        var cert = new GiftCertificate();
        cert.setId(id);
        return relationRepository.getTagsByCertificate(cert);
    }

    //TODO: add input params
    public GiftCertificate addGiftCertificate(String name) {
        var cert = new GiftCertificate();
        cert.setName(name);
        giftCertificateRepository.addGiftCertificate(cert);
        return null;
    }

    public int deleteTag(Integer id) {
        var cert = new GiftCertificate();
        cert.setId(id);
        return giftCertificateRepository.deleteGiftCertificate(cert);
    }
}
