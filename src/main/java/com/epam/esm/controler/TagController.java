package com.epam.esm.controler;

import com.epam.esm.entity.Tag;
import com.epam.esm.service.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("gift_war/tag-rest")
public class TagController {

    @Autowired
    private TagServiceImpl tagService;

    @GetMapping("/")
    public String getTag() {
        return "lol";
    }

    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Tag getTag(@PathVariable int id) {
        return tagService.findTagById(id);
    }
}
