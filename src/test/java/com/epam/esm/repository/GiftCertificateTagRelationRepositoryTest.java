package com.epam.esm.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.epam.esm.config.TestConfig;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
@ActiveProfiles("test")
class GiftCertificateTagRelationRepositoryTest {

    private static GiftCertificate certificate;
    private static Tag tag;

    @Autowired
    private GiftCertificateTagRelationRepository repository;
    @Autowired
    private GiftCertificateRepository certificateRepository;
    @Autowired
    private TagRepository tagRepository;

    @BeforeEach
    public void init() {
        if (tag == null) {
            tag = tagRepository.findById(11);
        }
        if (certificate == null) {
            certificate = certificateRepository.findById(7);
        }
    }

    @Test
    void getTagsByCertificate() {
        List<Tag> tags = repository.getTagsByCertificate(certificate);
        assertEquals(2, tags.size());
    }

    @Test
    void getCertificateByTag() {
        List<GiftCertificate> certificates = repository.getCertificateByTag(tag);
        assertEquals(1, certificates.size());
    }

    @Test
    void attachAndDetach() {

        List<GiftCertificate> certificatesBeforeAttach = repository.getCertificateByTag(tag);
        assertEquals(1, certificatesBeforeAttach.size());
        repository.attach(11, 7);
        List<GiftCertificate> certificates = repository.getCertificateByTag(tag);
        assertEquals(2, certificates.size());
        repository.detach(11, 7);
    }

}