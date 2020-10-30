package com.epam.esm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.GiftCertificateTagRelationRepository;
import com.epam.esm.repository.TagRepository;

/**
 * Tag service.
 */
@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository = null;

    @Autowired
    private GiftCertificateTagRelationRepository relationRepository;

    /**
     * All tags from DB.
     *
     * @return the list
     */
    public List<Tag> all() {
        return tagRepository.all();
    }

    /**
     * Find tag by id.
     *
     * @param id the id
     * @return the tag
     */
    public Tag findById(Integer id) {
        return tagRepository.findById(id);
    }

    /**
     * Find by name tag.
     *
     * @param name the name
     * @return the tag
     */
    public Tag findByName(String name) {
        return tagRepository.findByName(name);
    }

    /**
     * Add tag to BD.
     *
     * @param name the name
     */
    public void add(String name) {
        var tag = new Tag();
        tag.setName(name);
        tagRepository.add(tag);
    }

    /**
     * Delete tag with id=id. And returns affected row amount(if
     * tag was deleted returns 1)
     *
     * @param id the id
     * @return the int
     */
    public int delete(Integer id) {
        var tag = new Tag();
        tag.setId(id);
        return tagRepository.delete(tag);
    }

}
