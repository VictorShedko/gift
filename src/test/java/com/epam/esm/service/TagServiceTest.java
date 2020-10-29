package com.epam.esm.service;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.TagRepositoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
class TagServiceTest {
    private static final Tag TEST_TAG_1 = new Tag(1, "test1");
    private static final Tag TEST_TAG_2 = new Tag(2, "test2");


    @InjectMocks
    private TagService tagService = new TagService();

    @Mock
    private TagRepositoryImpl tagRepository;

    @BeforeEach
    public void  setup() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    void getAllTags() {
        List<Tag> tags = List.of(TEST_TAG_1, TEST_TAG_2);
        Mockito.when(tagRepository.getAllTags()).thenReturn(tags);

        List<Tag> resultTags = tagService.getAllTags();

        Assert.assertEquals(resultTags, tags);
    }

    @Test
    void findTagById() {
        
    }

    @Test
    void findTagByName() {
    }

    @Test
    void getCertificatesByTag() {
    }

    @Test
    void addTag() {
    }

    @Test
    void deleteTag() {
    }
}