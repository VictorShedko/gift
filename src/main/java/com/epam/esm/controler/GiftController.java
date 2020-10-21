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
    public List<GiftCertificate> getAllCertificate() {
        return giftCertificateService.all();
    }

    @RequestMapping(value = "gift-cert/{id}", method = RequestMethod.GET)
    public GiftCertificate getCertificate(@PathVariable int id) {
        GiftCertificate giftCertificate = giftCertificateService.findById(id);
        return giftCertificate;
    }


    @RequestMapping(value = "gift-cert/{id}/tags", method = RequestMethod.GET)
    public List<Tag> getGiftCertificateTags(@PathVariable int id) {
        return giftCertificateService.tags(id);
    }

    @RequestMapping(value = "gift-cert", method = RequestMethod.POST)
    public void getAllCertificate(@RequestBody GiftCertificate certificate) {

        giftCertificateService.add(certificate);
    }


    public GiftCertificate update() {
        return null;
    }

}
