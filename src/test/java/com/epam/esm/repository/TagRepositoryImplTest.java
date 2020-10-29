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
class TagRepositoryImplTest {

    @Autowired
    private static TagRepositoryImpl tagRepository;

    @BeforeAll
    public static void init(){
        TestConfig config=new TestConfig();
        tagRepository=new TagRepositoryImpl(config.dataSource());
    }

    @Test
    void addTag() {
        Tag newTag=new Tag();
        newTag.setName("testTag");

        Assertions.assertThrows(GiftException.class, () -> {
            tagRepository.findTagByName("testTag");
        });

        tagRepository.addTag(newTag);

        Tag founded=tagRepository.findTagByName("testTag");
        assertNotEquals(null,founded);

        tagRepository.deleteTag(founded);
    }

    @Test
    void deleteTag() {
        Tag newTag=new Tag();
        newTag.setName("testTag");

        tagRepository.addTag(newTag);
        Tag founded=tagRepository.findTagByName("testTag");
        assertNotEquals(null,founded);

        Tag toDelete=tagRepository.findTagByName("testTag");
        tagRepository.deleteTag(toDelete);
        Assertions.assertThrows(GiftException.class, () -> {
            tagRepository.findTagByName("testTag");
        });




    }

    @Test
    void findTagById() {
        Tag tag=tagRepository.findTagById(11);

        assertEquals("123",tag.getName());
    }

    @Test
    void findTagByName() {
        Tag tag=tagRepository.findTagByName("123");

        assertEquals(11,tag.getId());
    }

    @Test
    void getAllTags() {
        assertEquals(10,tagRepository.getAllTags().size());
    }

    @Test
    void nameIsUniqTag() {
        Tag newTag=new Tag();
        newTag.setName("testTag");
        tagRepository.addTag(newTag);
        Assertions.assertThrows(GiftException.class, () -> {
            tagRepository.addTag(newTag);
        });

    }
}