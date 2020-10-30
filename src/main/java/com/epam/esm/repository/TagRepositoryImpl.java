package com.epam.esm.repository;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.GiftException;
import com.epam.esm.exception.ResourceNotFoundedException;
import com.epam.esm.exception.UniqFieldException;
import com.epam.esm.repository.util.RowMappers;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.sql.DataSource;
import java.util.List;

@Repository
public class TagRepositoryImpl extends EntityGiftRepository implements TagRepository {


    public TagRepositoryImpl(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    public void addTag(Tag newTag) {
        if (newTag.getId() == null) {
            try {
                jdbcTemplate.update("insert into tag (name) values ( ?)", newTag.getName());
            }catch (DuplicateKeyException exception){
                throw new UniqFieldException("name");

            }catch (DataAccessException e){
                throw new GiftException();
            }
        }else {
            throw new GiftException();
        }
    }

    @Transactional
    @Override
    public int deleteTag(Tag tag) {
        return jdbcTemplate.update("delete from tag where id=?", tag.getId());
    }

    @Override
    public Tag findTagById(Integer id) {
        Tag tag=null;
        try {
            tag = jdbcTemplate.queryForObject("select * from tag where id = ?", new Object[]{id}, RowMappers.TAG_ROW_MAPPER);
        }catch (EmptyResultDataAccessException exception){
            throw new ResourceNotFoundedException("tag",id.toString());
        }
        return tag;
    }

    @Override
    public Tag findTagByName(String name) {
        Tag tag=null;
        try {
            tag = jdbcTemplate.queryForObject("select * from tag where name = ?", new Object[]{name}, RowMappers.TAG_ROW_MAPPER);
        }catch (EmptyResultDataAccessException ex){
            throw new ResourceNotFoundedException("tag",name);
        }

        return tag;
    }

    @Override
    public List<Tag> getAllTags() {
        return jdbcTemplate.query("select * from tag", RowMappers.TAG_ROW_MAPPER);
    }
}
