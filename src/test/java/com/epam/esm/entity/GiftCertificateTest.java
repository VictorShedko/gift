package com.epam.esm.entity;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class GiftCertificateTest {

    @Test
    void allFieldNotEquals() {
        Tag tag1 = new Tag(1, "11");
        Tag tag2 = new Tag(2, "22");
        assertNotEquals(tag1, tag2);
    }

    @Test
    void partFieldNotEquals() {
        Tag tag1 = new Tag(1, "11");
        Tag tag2 = new Tag(2, "11");
        assertNotEquals(tag1, tag2);
    }

}