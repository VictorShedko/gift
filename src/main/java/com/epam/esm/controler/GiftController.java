package com.epam.esm.controler;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.GiftCertificateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("gift-rest")
public class GiftController {

    @Autowired
    private GiftCertificateService giftCertificateService;

    @RequestMapping(value = "gift-cert", method = RequestMethod.GET)
    public List<GiftCertificate> getAllCertificate() {
        return giftCertificateService.getAllCertificates();
    }

    @RequestMapping(value = "gift-cert/{id}", method = RequestMethod.GET)
    public GiftCertificate getCertificate(@PathVariable int id) {
        return giftCertificateService.findCertificateById(id);
    }


    @RequestMapping(value = "gift-cert/{id}/tags", method = RequestMethod.GET)
    public List<Tag> getGiftCertificateTags(@PathVariable int id) {
        return giftCertificateService.getCertificatesByTag(id);
    }

    public GiftCertificate update(){
        return null;
    }

}
