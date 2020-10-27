package com.epam.esm.repository;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.GiftException;
import com.epam.esm.repository.util.RowMappers;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class TagRepositoryImpl extends EntityGiftRepository implements TagRepository {


    public TagRepositoryImpl(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    public void addTag(Tag newTag) {
        if (newTag.getId() == null) {
            jdbcTemplate.update("insert into tag (name) values ( ?)", newTag.getName());
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
            throw new GiftException("resource not fonded",123);
        }
        return tag;
    }

    @Override
    public Tag findTagByName(String name) {
        Tag tag = jdbcTemplate.queryForObject("select * from tag where name = ?", new Object[]{name}, RowMappers.TAG_ROW_MAPPER);
        return tag;
    }

    @Override
    public List<Tag> getAllTags() {
        return jdbcTemplate.query("select * from tag", RowMappers.TAG_ROW_MAPPER);
    }
}
