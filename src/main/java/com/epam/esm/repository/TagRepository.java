package com.epam.esm.repository;

import com.epam.esm.entity.Tag;

import java.util.List;

public interface TagRepository {
    void addTag(Tag newTag);

    int deleteTag(Tag tag);

    Tag findTagById(Integer id);

    Tag findTagByName(String name);

    List<Tag> getAllTags();

}
