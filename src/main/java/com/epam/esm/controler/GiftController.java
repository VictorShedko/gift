package com.epam.esm.controler;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.CertificateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.List;

@RestController
@RequestMapping("api/gift-cert")
public class GiftController {

    @Autowired
    private CertificateService giftCertificateService;

    @RequestMapping(value = "/certs", method = RequestMethod.GET)
    public List<GiftCertificate> all() {
        return giftCertificateService.all();
    }

    @RequestMapping(value = "/certs/{tagName}/tagName", method = RequestMethod.GET)
    public List<GiftCertificate> byTag(@PathVariable(name = "tagName") String tagName) {
        return giftCertificateService.searchByTag(tagName);
    }

    @RequestMapping(value = "/certs/{find}/find", method = RequestMethod.GET)
    public List<GiftCertificate> find(@PathVariable( name = "find") String pattern) {
        return giftCertificateService.searchByAnyString(pattern);
    }


    @RequestMapping(value = "/certs/{id}", method = RequestMethod.GET)
    public GiftCertificate certificate(@PathVariable int id) {
        GiftCertificate giftCertificate = giftCertificateService.findById(id);
        return giftCertificate;
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void add(@RequestBody GiftCertificate certificate) {

        giftCertificateService.add(certificate);
    }

    @RequestMapping(value = "/", method = RequestMethod.PATCH)
    public int update(@RequestBody GiftCertificate certificate) {
        return giftCertificateService.update(certificate);
    }


    @RequestMapping(value = "/{certificateId}/delete", method = RequestMethod.DELETE)
    public int delete(@PathVariable int certificateId) {
        return giftCertificateService.delete(certificateId);
    }


    @Deprecated
    @RequestMapping(value = "gift-cert/test", method = RequestMethod.POST)
    public ZonedDateTime timeTest(@RequestBody
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime date) {

        return date;
    }
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'.'S Z")
}
