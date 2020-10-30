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

/**
 * The type Gift certificate service.
 */
@Service
public class GiftCertificateService {

    @Autowired
    private GiftCertificateRepository giftCertificateRepository;

    @Autowired
    private GiftCertificateTagRelationRepository relationRepository;

    @Autowired
    private TagService tagService;

    @Autowired
    private UpdateCertificateService updateCertificateService;

    /**
     * return list of all certificates from DB.
     *
     * @return the list
     */
    public List<GiftCertificate> all() {
        return giftCertificateRepository.all();
    }

    /**
     * Find by id gift certificate.
     *
     * @param id the id
     * @return the gift certificate
     */
    public GiftCertificate findById(Integer id) {
        return giftCertificateRepository.findById(id);
    }

    /**
     * Add certificate to DB.
     *
     * @param certificate the certificate
     */
    public void add(GiftCertificate certificate) {
        Date nowDate = TimeManager.now();
        certificate.setCreationTime(nowDate);
        certificate.setUpdateTime(nowDate);
        giftCertificateRepository.add(certificate);
    }

    /**
     * Delete certificate.
     *
     * @param id the id
     * @return amount of affected rows
     */
    public int delete(Integer id) {
        var cert = new GiftCertificate();
        cert.setId(id);
        return giftCertificateRepository.delete(cert);
    }

    /**
     * Attach tag to certificate. Use relationRepository.
     *
     * @param tagId  the tag id
     * @param certId the cert id
     */
    public void attachTag(Integer tagId, Integer certId) {
        relationRepository.attach(tagId, certId);
    }

    /**
     * Detach tag from cert.
     *
     * @param tagId  the tag id
     * @param certId the cert id
     * @return the int
     */
    public int detachTag(Integer tagId, Integer certId) {
        return relationRepository.detach(tagId, certId);

    }


    /**
     * Update gift certificate with same id in DB.
     *
     * @param pathCertificate the path certificate
     * @return the int
     */
    public int update(GiftCertificate pathCertificate) {
        GiftCertificate certificateFromDB = findById(pathCertificate.getId());
        GiftCertificate updated=updateCertificateService.updateCertificate(certificateFromDB, pathCertificate);
        return giftCertificateRepository.update(updated);
    }

    /**
     * Found certificates by part of name.
     *
     * @param pattern the pattern
     * @return the list
     */
    public List<GiftCertificate> searchByName(String pattern) {
        return giftCertificateRepository.searchByName(pattern);
    }

    /**
     * Found certificates by part of description.
     *
     * @param pattern the pattern
     * @return the list
     */
    public List<GiftCertificate> searchByDescription(String pattern) {
        return giftCertificateRepository.searchByDescription(pattern);
    }

    /**
     * Search by name and description.
     *
     * @param pattern the pattern
     * @return the list
     */
    public List<GiftCertificate> searchByAnyString(String pattern) {
        return giftCertificateRepository.searchByAnyString(pattern);
    }

    /**
     * Search certificates wit tag.
     *
     * @param tagName the tag name
     * @return the list
     */
    public List<GiftCertificate> searchByTag(String tagName) {
        Tag tag = tagService.findByName(tagName);
        return relationRepository.getCertificateByTag(tag);
    }

    /**
     * Return tags attached to certificate.
     *
     * @param id the id
     * @return the list of attached tags
     */
    public List<Tag> tags(Integer id) {
        var cert = new GiftCertificate();
        cert.setId(id);
        return relationRepository.getTagsByCertificate(cert);
    }

}
