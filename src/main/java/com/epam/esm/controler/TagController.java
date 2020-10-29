package com.epam.esm.controler;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.GiftException;
import com.epam.esm.exception.JSONExceptionEntity;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;


@RestControllerAdvice
@RequestMapping("/api/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private CertificateService giftCertificateService;

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public List<Tag> getAllTag() {
        return tagService.getAllTags();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Tag getTag(@RequestParam int tagId) {
        return tagService.findTagById(tagId);
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void addTag(@RequestBody String tagName) {

        tagService.add(tagName);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public void deleteTag(@PathVariable int tagId) {

        tagService.delete(tagId);
    }

    @RequestMapping(value = "/{certId}/tags", method = RequestMethod.GET)
    public List<Tag> tags(@PathVariable int certId) {
        return giftCertificateService.tags(certId);
    }



}
