package com.epam.esm.repository;

import com.epam.esm.config.TestConfig;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.GiftException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
class TagRepositoryTest {

    @Autowired
    private static TagRepository tagRepository;

    @BeforeAll
    public static void init(){
        TestConfig config=new TestConfig();
        tagRepository=new TagRepository(config.dataSource());
    }

    @Test
    void addTag() {
        Tag newTag=new Tag();
        newTag.setName("testTag");

        Assertions.assertThrows(GiftException.class, () -> {
            tagRepository.findByName("testTag");
        });

        tagRepository.add(newTag);

        Tag founded=tagRepository.findByName("testTag");
        assertNotEquals(null,founded);

        tagRepository.delete(founded);
    }

    @Test
    void addTagWithId() {
        Tag newTag=new Tag();
        newTag.setName("testTag");
        newTag.setId(12);

        Assertions.assertThrows(GiftException.class, () -> {
            tagRepository.add(newTag);
        });
    }

    @Test
    void deleteTag() {
        Tag newTag=new Tag();
        newTag.setName("testTag");

        tagRepository.add(newTag);
        Tag founded=tagRepository.findByName("testTag");
        assertNotEquals(null,founded);

        Tag toDelete=tagRepository.findByName("testTag");
        tagRepository.delete(toDelete);
        Assertions.assertThrows(GiftException.class, () -> {
            tagRepository.findByName("testTag");
        });




    }

    @Test
    void findTagById() {
        Tag tag=tagRepository.findById(11);

        assertEquals("123",tag.getName());
    }

    @Test
    void findTagByName() {
        Tag tag=tagRepository.findByName("123");

        assertEquals(11,tag.getId());
    }

    @Test
    void getAllTags() {
        assertEquals(10,tagRepository.all().size());
    }

    @Test
    void nameIsUniqTag() {
        Tag newTag=new Tag();
        newTag.setName("testTag");
        tagRepository.add(newTag);
        Assertions.assertThrows(GiftException.class, () -> {
            tagRepository.add(newTag);
        });

    }
}