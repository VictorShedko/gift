package com.epam.esm.repository;

import com.epam.esm.config.TestConfig;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.util.TimeManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GiftCertificateRepositoryTest {
    private static GiftCertificate testCert=new GiftCertificate();

    static {
        testCert.setUpdateTime(TimeManager.now());
        testCert.setCreationTime(TimeManager.now());
        testCert.setPrice(1.0f);
        testCert.setDescription("Test description");
        testCert.setName("Test name");
        testCert.setDuration(1);
    }

    @Autowired
    private static GiftCertificateRepository repository;

    @BeforeAll
    public static void init(){
        TestConfig config=new TestConfig();
        repository=new GiftCertificateRepository(config.dataSource());
    }

    @AfterEach
    public void deleteTestCert(){
        List<GiftCertificate> certificates=repository.searchByName("Test name");
        if(certificates.size()!=0) {
            GiftCertificate certificate = repository.searchByName("Test name").get(0);
            repository.delete(certificate);
        }
    }


    @Test
    void add() {
        List<GiftCertificate> certificates=repository.searchByName("Test name");
        assertEquals(0,certificates.size());

        repository.add(testCert);

        List<GiftCertificate> founded=repository.searchByName("Test name");
        assertNotEquals(0,founded.size());


    }

    @Test
    void delete() {

        repository.add(testCert);
        GiftCertificate certificate=repository.searchByName("Test").get(0);
        assertNotEquals(null,certificate);

        repository.delete(certificate);
        List<GiftCertificate> certificates=repository.searchByName("Test");
        assertEquals(0,certificates.size());


    }

    @Test
    void findTagById() {

        repository.add(testCert);
        GiftCertificate certificate=repository.searchByName("Test").get(0);
        GiftCertificate certificateFoundedById=repository.findById(certificate.getId());
        assertEquals(certificate,certificateFoundedById);
    }

    @Test
    void getAll() {
        assertEquals(2,repository.all().size());
    }

    @Test
    void update() {

        repository.add(testCert);

        GiftCertificate certificateFromDB=repository.searchByName("Test").get(0);
        certificateFromDB.setDuration(2);
        repository.update(certificateFromDB);
        GiftCertificate certificateFromDB2=repository.findById(certificateFromDB.getId());
        assertEquals(2,certificateFromDB2.getDuration());
    }

    @Test
    void searchByName() {

        repository.add(testCert);
        GiftCertificate certificate=repository.searchByName("Test").get(0);
        assertEquals("Test name",certificate.getName());

    }

    @Test
    void searchByDescription() {

        repository.add(testCert);
        GiftCertificate certificate=repository.searchByDescription("Test").get(0);
        assertEquals("Test description",certificate.getDescription());
    }
}