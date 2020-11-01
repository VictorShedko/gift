package com.epam.esm.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.epam.esm.config.TestConfig;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.GiftException;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
@ActiveProfiles("test")
class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    @Test
    void addTag() {
        Tag newTag = new Tag();
        newTag.setName("testTag");

        Assertions.assertThrows(GiftException.class, () -> {
            tagRepository.findByName("testTag");
        });

        tagRepository.add(newTag);

        Tag founded = tagRepository.findByName("testTag");
        assertNotEquals(null, founded);

        tagRepository.delete(founded);
    }

    @Test
    void addTagWithId() {
        Tag newTag = new Tag();
        newTag.setName("testTag");
        newTag.setId(12);

        Assertions.assertThrows(GiftException.class, () -> {
            tagRepository.add(newTag);
        });
    }

    @Test
    void deleteTag() {
        Tag newTag = new Tag();
        newTag.setName("testTag");

        tagRepository.add(newTag);
        Tag founded = tagRepository.findByName("testTag");
        assertNotEquals(null, founded);

        Tag toDelete = tagRepository.findByName("testTag");
        tagRepository.delete(toDelete);
        Assertions.assertThrows(GiftException.class, () -> {
            tagRepository.findByName("testTag");
        });

    }

    @Test
    void findTagById() {
        Tag tag = tagRepository.findById(11);

        assertEquals("123", tag.getName());
    }

    @Test
    void findTagByName() {
        Tag tag = tagRepository.findByName("123");

        assertEquals(11, tag.getId());
    }

    @Test
    void getAllTags() {
        assertEquals(10, tagRepository.all().size());
    }

    @Test
    void nameIsUniqTag() {
        Tag newTag = new Tag();
        newTag.setName("testTag");
        tagRepository.add(newTag);
        Assertions.assertThrows(GiftException.class, () -> {
            tagRepository.add(newTag);
        });

    }
}