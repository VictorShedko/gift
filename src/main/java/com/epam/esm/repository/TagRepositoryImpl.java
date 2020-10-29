package com.epam.esm.repository;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.ErrorCodeDict;
import com.epam.esm.exception.GiftException;
import com.epam.esm.repository.util.RowMappers;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
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
            try {
                jdbcTemplate.update("insert into tag (name) values ( ?)", newTag.getName());
            }catch (DuplicateKeyException exception){
                throw new GiftException("Tag with this name already exist",ErrorCodeDict.UNIQ_FIELD_DUPLICATION);

            }catch (DataAccessException e){
                throw new GiftException("Data access exception",ErrorCodeDict.UNKNOWN_ERROR);
            }
        }else {
            throw new GiftException("Input mustn't contain id", ErrorCodeDict.BAD_INPUT_DATA_FORMAT);
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
        Tag tag=null;
        try {
            tag = jdbcTemplate.queryForObject("select * from tag where name = ?", new Object[]{name}, RowMappers.TAG_ROW_MAPPER);
        }catch (EmptyResultDataAccessException ex){
            throw new GiftException("Resource by name "+ name+" not founded", ErrorCodeDict.RESOURCE_NOT_FOUNDED);
        }

        return tag;
    }

    @Override
    public List<Tag> getAllTags() {
        return jdbcTemplate.query("select * from tag", RowMappers.TAG_ROW_MAPPER);
    }
}
