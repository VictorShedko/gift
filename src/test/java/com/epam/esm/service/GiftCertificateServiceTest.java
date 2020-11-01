package com.epam.esm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.repository.GiftCertificateRepository;
import com.epam.esm.repository.GiftCertificateTagRelationRepository;
import com.epam.esm.service.util.UpdateCertificateService;

class GiftCertificateServiceTest {

    private static GiftCertificate testCert1 = new GiftCertificate();
    private static GiftCertificate testCert2 = new GiftCertificate();
    private static List<GiftCertificate> allCerts = List.of(testCert1, testCert2);

    static {
        testCert1.setId(1);
        testCert1.setName("test1");
        testCert1.setDescription("test desc 1");
        testCert1.setPrice(1.0f);
        testCert1.setCreationTime(new Date());
        testCert1.setUpdateTime(new Date());
        testCert1.setDuration(1);

        testCert2.setId(2);
        testCert2.setName("test2");
        testCert2.setDescription("test desc 2");
        testCert2.setPrice(2.0f);
        testCert2.setCreationTime(new Date());
        testCert2.setUpdateTime(new Date());
        testCert2.setDuration(2);

    }

    @InjectMocks
    private GiftCertificateService service = new GiftCertificateService();

    @Mock
    private GiftCertificateRepository giftCertificateRepository;

    @Mock
    private GiftCertificateTagRelationRepository relationRepository;

    @Mock
    private TagService tagService;

    @Mock
    private UpdateCertificateService updateCertificateService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void all() {
        List<GiftCertificate> certificates = List.of(testCert1, testCert2);
        Mockito.when(giftCertificateRepository.all()).thenReturn(certificates);

        List<GiftCertificate> resultCerts = service.all();

        assertEquals(resultCerts, certificates);

    }

    @Test
    void findById() {
        Mockito.when(giftCertificateRepository.findById(1)).thenReturn(testCert1);
        Mockito.when(giftCertificateRepository.findById(2)).thenReturn(testCert2);
        GiftCertificate cert1 = service.findById(1);
        GiftCertificate cert2 = service.findById(2);
        assertEquals(testCert1, cert1);
        assertEquals(testCert2, cert2);
    }

    @Test
    void add() {
        service.add(testCert1);
        Mockito.verify(giftCertificateRepository).add(testCert1);
    }

    @Test
    void delete() {
        service.delete(1);
        Mockito.verify(giftCertificateRepository).delete(Mockito.any());
    }

    @Test
    void attachTag() {
        service.attachTag(1, 2);
        Mockito.verify(relationRepository).attach(1, 2);
    }

    @Test
    void detachTag() {
        service.detachTag(1, 2);
        Mockito.verify(relationRepository).detach(1, 2);
    }

    @Test
    void update() {
        GiftCertificate patch = new GiftCertificate();
        patch.setId(2);
        patch.setDuration(3);

        GiftCertificate expected = new GiftCertificate();

        expected.setId(2);
        expected.setName("test2");
        expected.setDescription("test desc 2");
        expected.setPrice(2.0f);
        expected.setCreationTime(new Date());
        expected.setUpdateTime(new Date());
        expected.setDuration(3);

        Mockito.when(giftCertificateRepository.findById(2)).thenReturn(testCert2);
        Mockito.when(updateCertificateService.updateCertificate(testCert2, patch)).thenReturn(expected);
        service.update(patch);

        Mockito.verify(giftCertificateRepository).update(expected);
    }

    @Test
    void searchByName() {
        Mockito.when(giftCertificateRepository.searchByName("te")).thenReturn(allCerts);

        List<GiftCertificate> certificates = service.searchByName("te");
        assertEquals(certificates, allCerts);

    }

    @Test
    void searchByDescription() {
        Mockito.when(giftCertificateRepository.searchByDescription("te")).thenReturn(allCerts);

        List<GiftCertificate> certificates = service.searchByDescription("te");
        assertEquals(certificates, allCerts);
    }

    @Test
    void searchByAnyString() {
        Mockito.when(giftCertificateRepository.searchByAnyString("te")).thenReturn(allCerts);

        List<GiftCertificate> certificates = service.searchByAnyString("te");
        assertEquals(certificates, allCerts);
    }

    @Test
    void searchByTag() {

        Tag tag = new Tag();
        tag.setId(11);
        Mockito.when(tagService.findByName("tag")).thenReturn(tag);
        Mockito.when(relationRepository.getCertificateByTag(tag)).thenReturn(allCerts);

        List<GiftCertificate> certificates = service.searchByTag("tag");
        assertEquals(allCerts, certificates);

    }

    @Test
    void tags() {
        List<Tag> tags = List.of(new Tag());
        Mockito.when(relationRepository.getTagsByCertificate(Mockito.any())).thenReturn(tags);
        List<Tag> resultTags = service.tags(1);
        assertEquals(tags, resultTags);

    }
}