package com.epam.esm.controler;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.GiftException;
import com.epam.esm.exception.JSONExceptionEntity;
import com.epam.esm.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;


@RestControllerAdvice
@RequestMapping("tag-rest")
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "tag", method = RequestMethod.GET)
    public List<Tag> getAllTag() {
        return tagService.getAllTags();
    }

    @RequestMapping(value = "tag/{id}", method = RequestMethod.GET)
    public Tag getTag(@PathVariable int id) {
        return tagService.findTagById(id);
    }

//    @RequestMapping(value = "tag", method = RequestMethod.GET)
//    public Tag getTag(@RequestParam(name = "name") String name) {
//        return tagService.findTagByName(name);
//    }

    @RequestMapping(value = "tag", method = RequestMethod.POST)
    public void addTag(@RequestBody Tag tag) {

        tagService.add(tag.getName());
    }

    @RequestMapping(value = "tag/{id}", method = RequestMethod.DELETE)
    public void deleteTag(@PathVariable int id) {

        tagService.delete(id);
    }

    @ExceptionHandler({GiftException.class})
    public ResponseEntity<Object> handleCustomException(GiftException ce, WebRequest request) {
        return new ResponseEntity<>(
                new JSONExceptionEntity(ce, HttpStatus.I_AM_A_TEAPOT), new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT);
    }

}
