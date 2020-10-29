package com.epam.esm.controler;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import com.epam.esm.exception.GiftException;
import com.epam.esm.exception.JSONExceptionEntity;
import com.epam.esm.service.CertificateService;
import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.time.ZonedDateTime;
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

    @RequestMapping(value = "gift-cert", method = RequestMethod.GET)
    public List<GiftCertificate> byTag(@RequestParam(name = "tagName") String tagName) {
        return giftCertificateService.searchByTag(tagName);
    }

    @RequestMapping(value = "gift-cert", method = RequestMethod.GET)
    public List<GiftCertificate> find(@RequestParam(required = false, name = "find") String pattern) {
        return giftCertificateService.searchByAnyString(pattern);
    }

    @RequestMapping(value = "gift-cert", method = RequestMethod.GET)
    public List<GiftCertificate> all(@RequestParam(required = false, name = "name") String name,
                                     @RequestParam(required = false, name = "tagName") String tagName,
                                     @RequestParam(required = false, name = "find") String pattern) {
        if (name != null) {
            return giftCertificateService.searchByName(name);
        }
        if (tagName != null) {
            return giftCertificateService.searchByTag(pattern);
        }
        if (pattern != null) {
            return giftCertificateService.searchByAnyString(pattern);
        }
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
    public void attachTag(@PathVariable int id, @RequestBody Integer tagId) {
        giftCertificateService.attachTag(tag, id);
    }

    @RequestMapping(value = "gift-cert/{id}/tags", method = RequestMethod.DELETE)
    public void detachTag(@PathVariable int id, @RequestBody Integer tagIg) {
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
    public int delete(@RequestBody Integer certificateId) {
        return giftCertificateService.delete(certificateId);
    }

    public List<GiftCertificate> byOneTag(String tagName) {
        return giftCertificateService.searchByTag(tagName);
    }

    public List<GiftCertificate> findByString(String pattern) {
        return giftCertificateService.searchByAnyString(pattern);
    }


    @Deprecated
    @RequestMapping(value = "gift-cert/test", method = RequestMethod.POST)
    public ZonedDateTime timeTest(@RequestBody
                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) ZonedDateTime date) {

        return date;
    }
    //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'.'S Z")
}
