package com.epam.esm.service;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.repository.GiftCertificateRelationRepository;
import com.epam.esm.repository.TagRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    @Autowired
    private TagRepositoryImpl tagRepository;

    @Autowired
    private GiftCertificateRelationRepository relationRepository;

    public List<Tag> getAllTags(){
        return tagRepository.getAllTags();
    }

    public Tag findTagById(Integer id){
        return tagRepository.findTagById(id);
    }

    public Tag findTagByName(String name){
        return tagRepository.findTagByName(name);
    }

    public List<GiftCertificate> getCertificatesByTag(Integer id){
        var tag =new Tag();
        tag.setId(id);
        return relationRepository.getCertificateByTag(tag);
    }

    public Tag addTag(String name){
        var tag=new Tag();
        tag.setName(name);
        tagRepository.addTag(tag);
        return findTagByName(name);
    }

    public int deleteTag(Integer id){
        var tag=new Tag();
        tag.setId(id);
        return tagRepository.deleteTag(tag);
    }



}
