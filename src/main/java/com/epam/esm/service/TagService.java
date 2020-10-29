package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.repository.GiftCertificateTagRelationRepository;
import com.epam.esm.repository.TagRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepositoryImpl tagRepository = null;

    @Autowired
    private GiftCertificateTagRelationRepository relationRepository;

    public List<Tag> all() {
        return tagRepository.getAllTags();
    }

    public Tag findTagById(Integer id) {
        return tagRepository.findTagById(id);
    }

    public Tag findTagByName(String name) {
        return tagRepository.findTagByName(name);
    }

    public Tag add(String name) {
        var tag = new Tag();
        tag.setName(name);
        tagRepository.addTag(tag);
        return findTagByName(name);
    }

    public int delete(Integer id) {
        var tag = new Tag();
        tag.setId(id);
        return tagRepository.deleteTag(tag);
    }


}
