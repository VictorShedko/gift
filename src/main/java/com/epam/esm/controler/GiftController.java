package com.epam.esm.controler;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("gift-rest")
public class GiftController {

    @Autowired
    private GiftCertificateService giftCertificateService;

    @RequestMapping(value = "gift-cert", method = RequestMethod.GET)
    public List<GiftCertificate> all() {
        return giftCertificateService.all();
    }

    @RequestMapping(value = "gift-cert/{id}", method = RequestMethod.GET)
    public GiftCertificate certificate(@PathVariable int id) {
        GiftCertificate giftCertificate = giftCertificateService.findById(id);
        return giftCertificate;
    }


    @RequestMapping(value = "gift-cert/{id}/tags", method = RequestMethod.GET)
    public List<Tag> tags(@PathVariable int id) {
        return giftCertificateService.tags(id);
    }

    @RequestMapping(value = "gift-cert/{id}/tags", method = RequestMethod.POST)
    public void attachTag(@PathVariable int id, @RequestBody Tag tag) {
        giftCertificateService.attachTag(tag, id);
    }

    @RequestMapping(value = "gift-cert/{id}/tags", method = RequestMethod.DELETE)
    public void detachTag(@PathVariable int id, @RequestBody Tag tag) {
        giftCertificateService.detachTag(tag, id);
    }

    @RequestMapping(value = "gift-cert", method = RequestMethod.POST)
    public void add(@RequestBody GiftCertificate certificate) {

        giftCertificateService.add(certificate);
    }

    @RequestMapping(value = "gift-cert", method = RequestMethod.POST)
    public int update(@RequestBody GiftCertificate certificate) {
        return giftCertificateService.update(certificate);
    }

}
