package com.epam.esm.controler;

import com.epam.esm.entity.Tag;
import com.epam.esm.repository.TagRepositoryImpl;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("tag-rest")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "tag", method = RequestMethod.GET)
    public List<Tag> getAllTag() {
        return tagService.getAllTags();
    }

    @RequestMapping(value = "tag/{id}", method = RequestMethod.GET)
    public Tag getTag(@PathVariable int id) {
        return tagService.findTagById(id);
    }

    // TODO:
    @RequestMapping(value = "tag", method = RequestMethod.GET)
    public Tag getTag(@RequestParam String name) {
        return tagService.findTagByName(name);
    }

    @RequestMapping(value = "tag", method = RequestMethod.POST)
    public void addTag(@RequestBody String name) {
        tagService.addTag(name);
    }

    @RequestMapping(value = "tag/{id}", method = RequestMethod.DELETE)
    public void deleteTag(@PathVariable int id) {

        tagService.deleteTag(id);
    }

    @RequestMapping(value = "tag/{id}/cert", method = RequestMethod.DELETE)
    public void getRelatedCertificates(@PathVariable int id) {

        tagService.getCertificatesByTag(id);
    }
}
