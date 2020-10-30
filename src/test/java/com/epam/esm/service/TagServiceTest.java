package com.epam.esm.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.TagRepository;

@RunWith(MockitoJUnitRunner.class)
class TagServiceTest {
    private static final Tag TEST_TAG_1 = new Tag(1, "test1");
    private static final Tag TEST_TAG_2 = new Tag(2, "test2");


    @InjectMocks
    private TagService tagService = new TagService();

    @Mock
    private TagRepository tagRepository;

    @BeforeEach
    public void  setup() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    void getAllTags() {
        List<Tag> tags = List.of(TEST_TAG_1, TEST_TAG_2);
        Mockito.when(tagRepository.all()).thenReturn(tags);

        List<Tag> resultTags = tagService.all();

        assertEquals(resultTags, tags);
    }

    @Test
    void findTagById() {
        Mockito.when(tagRepository.findById(1)).thenReturn(TEST_TAG_1);
        Mockito.when(tagRepository.findById(2)).thenReturn(TEST_TAG_2);
        Tag tag1=tagService.findById(1);
        Tag tag2=tagService.findById(2);
        assertEquals(TEST_TAG_1,tag1);
        assertEquals(TEST_TAG_2,tag2);
    }

    @Test
    void findTagByName() {
        Mockito.when(tagRepository.findByName("test1")).thenReturn(TEST_TAG_1);
        Mockito.when(tagRepository.findByName("test2")).thenReturn(TEST_TAG_2);
        Tag tag1=tagService.findByName("test1");
        Tag tag2=tagService.findByName("test2");
        assertEquals(TEST_TAG_1,tag1);
        assertEquals(TEST_TAG_2,tag2);
    }

    @Test
    void addTag() {
        tagService.add("test3");
        Mockito.verify(tagRepository).add(Mockito.any());
    }

    @Test
    void deleteTag() {
        tagService.delete(1);
        Tag deleteTag=new Tag();
        deleteTag.setId(1);
        Mockito.verify(tagRepository).delete(deleteTag);
    }
}