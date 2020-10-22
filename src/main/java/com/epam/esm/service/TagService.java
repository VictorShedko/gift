package com.epam.esm.service;

import com.epam.esm.entity.Tag;

import java.util.List;

public interface TagService {
    void addTag(Tag newTag);
    int deleteTag(Tag tag);
    Tag findTagById(Integer id);
    Tag findTagByName(String name);
    List<Tag> getAllTags();

}
