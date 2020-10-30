package com.epam.esm.controler;

import com.epam.esm.entity.Tag;
import com.epam.esm.service.CertificateService;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestControllerAdvice
@RequestMapping("api/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @Autowired
    private CertificateService giftCertificateService;

    @RequestMapping(value = "/tags", method = RequestMethod.GET)
    public List<Tag> getAllTag() {
        return tagService.all();
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public Tag getTag(@RequestParam int tagId) {
        return tagService.findTagById(tagId);
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void addTag(@RequestBody String tagName) {

        tagService.add(tagName);
    }

    @RequestMapping(value = "/{tagId}/delete", method = RequestMethod.DELETE)
    public void deleteTag(@PathVariable int tagId) {

        tagService.delete(tagId);
    }

    @RequestMapping(value = "/{certId}/tags", method = RequestMethod.GET)
    public List<Tag> tags(@PathVariable int certId) {
        return giftCertificateService.tags(certId);
    }

    @RequestMapping(value = "/{certId}/tags/{tagId}", method = RequestMethod.POST)
    public void attachTag(@PathVariable int certId, @PathVariable int tagId) {
        giftCertificateService.attachTag(tagId, certId);
    }

    @RequestMapping(value = "/{certId}/tags/{tagId}/delete", method = RequestMethod.DELETE)
    public int detachTag(@PathVariable int certId, @PathVariable int tagId) {
        return giftCertificateService.detachTag(tagId, certId);
    }

}
