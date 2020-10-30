package com.epam.esm.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.service.GiftCertificateService;

@RestController
@RequestMapping("api/gift-cert")
public class GiftController {

    @Autowired
    private GiftCertificateService giftCertificateService;

    @RequestMapping(value = "/certs", method = RequestMethod.GET)
    public List<GiftCertificate> all() {
        return giftCertificateService.all();
    }

    @RequestMapping(value = "/certs/{tagName}/tagName", method = RequestMethod.GET)
    public List<GiftCertificate> byTag(@PathVariable(name = "tagName") String tagName) {
        return giftCertificateService.searchByTag(tagName);
    }

    @RequestMapping(value = "/certs/{find}/find", method = RequestMethod.GET)
    public List<GiftCertificate> find(@PathVariable(name = "find") String pattern) {
        return giftCertificateService.searchByAnyString(pattern);
    }

    @RequestMapping(value = "/certs/{id}", method = RequestMethod.GET)
    public GiftCertificate certificate(@PathVariable int id) {
        return giftCertificateService.findById(id);
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

}
