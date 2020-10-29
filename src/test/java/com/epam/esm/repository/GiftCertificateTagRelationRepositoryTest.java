package com.epam.esm.repository;

import com.epam.esm.config.TestConfig;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GiftCertificateTagRelationRepositoryTest {
    private static GiftCertificate certificate;
    private static Tag tag;

    private static GiftCertificateTagRelationRepository repository;
    private static GiftCertificateRepository certificateRepository;
    private static TagRepository tagRepository;
    @BeforeAll
    public static void init(){
        TestConfig config=new TestConfig();
        DataSource dataSource=config.dataSource();
        repository=new GiftCertificateTagRelationRepository(dataSource);
        certificateRepository=new GiftCertificateRepository(dataSource);
        tagRepository=new TagRepository(dataSource);
        tag=tagRepository.findById(11);
        certificate=certificateRepository.findById(7);
    }

    @Test
    void getTagsByCertificate() {
        List<Tag> tags=repository.getTagsByCertificate(certificate);
        assertEquals(2,tags.size());
    }

    @Test
    void getCertificateByTag() {
        List<GiftCertificate> certificates=repository.getCertificateByTag(tag);
        assertEquals(1,certificates.size());
    }

    @Test
    void attachAndDetach() {

        List<GiftCertificate> certificatesBeforeAttach=repository.getCertificateByTag(tag);
        assertEquals(1,certificatesBeforeAttach.size());
        repository.attach(11,7);
        List<GiftCertificate> certificates=repository.getCertificateByTag(tag);
        assertEquals(2,certificates.size());
        repository.detach(11,7);
    }

}