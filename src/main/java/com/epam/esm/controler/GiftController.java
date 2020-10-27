package com.epam.esm.controler;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.service.CertificateService;
import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("gift-rest")
public class GiftController {

    @Autowired
    private CertificateService giftCertificateService;

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

    @RequestMapping(value = "gift-cert", method = RequestMethod.PATCH)
    public int update(@RequestBody GiftCertificate certificate) {
        return giftCertificateService.update(certificate);
    }


    @RequestMapping(value = "gift-cert", method = RequestMethod.DELETE)
    public int delete(@RequestBody GiftCertificate certificate) {
        return giftCertificateService.update(certificate);
    }

    @RequestMapping(value = "gift-cert/test", method = RequestMethod.POST)
    public ZonedDateTime timeTest(@RequestBody
                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime date) {

        return date;
    }
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'.'S Z")
}
