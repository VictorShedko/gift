package com.epam.esm.service;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.GiftCertificateTagRelationRepository;
import com.epam.esm.repository.GiftRepository;
import com.epam.esm.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository = null;

    @Autowired
    private GiftCertificateTagRelationRepository relationRepository;

    public List<Tag> all() {
        return tagRepository.all();
    }

    public Tag findTagById(Integer id) {
        return tagRepository.findById(id);
    }

    public Tag findTagByName(String name) {
        return tagRepository.findByName(name);
    }

    public Tag add(String name) {
        var tag = new Tag();
        tag.setName(name);
        tagRepository.add(tag);
        return findTagByName(name);
    }

    public int delete(Integer id) {
        var tag = new Tag();
        tag.setId(id);
        return tagRepository.delete(tag);
    }


}
