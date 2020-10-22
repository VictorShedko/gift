package com.epam.esm.controler;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("gift-rest")
public class GiftController {

    @RequestMapping(value = "gift-cart", method = RequestMethod.GET)
    public List<GiftCertificate> getAllTag() {
        return null;
    }

    @RequestMapping(value = "gift-cart/{id}", method = RequestMethod.GET)
    public String getTag(@PathVariable int id) {
        return "lol";
    }


    @RequestMapping(value = "gift-cart/{id}/tags/{tag_num}", method = RequestMethod.GET)
    public String getTag(@PathVariable String name) {
        return "lol";
    }

}
