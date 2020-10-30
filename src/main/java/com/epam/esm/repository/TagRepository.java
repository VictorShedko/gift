package com.epam.esm.repository;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.GiftException;
import com.epam.esm.exception.ResourceNotFoundedException;
import com.epam.esm.exception.UniqFieldException;
import com.epam.esm.repository.util.RowMappers;

@Repository
public class TagRepository extends EntityGiftRepository {

    public TagRepository(DataSource dataSource) {
        super(dataSource);
    }

    public void add(Tag newTag) {
        if (newTag.getId() == null) {
            try {
                jdbcTemplate.update("insert into tag (name) values ( ?)", newTag.getName());
            } catch (DuplicateKeyException exception) {
                throw new UniqFieldException("name");

            } catch (DataAccessException e) {
                throw new GiftException();
            }
        } else {
            throw new GiftException();
        }
    }

    @Transactional
    public int delete(Tag tag) {
        return jdbcTemplate.update("delete from tag where id=?", tag.getId());
    }

    public Tag findById(Integer id) {
        Tag tag = null;
        try {
            tag = jdbcTemplate.queryForObject("select * from tag where id = ?", new Object[] {id},
                    RowMappers.TAG_ROW_MAPPER);
        } catch (EmptyResultDataAccessException exception) {
            throw new ResourceNotFoundedException("tag", id.toString());
        }
        return tag;
    }

    public Tag findByName(String name) {
        Tag tag = null;
        try {
            tag = jdbcTemplate.queryForObject("select * from tag where name = ?", new Object[] {name},
                    RowMappers.TAG_ROW_MAPPER);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundedException("tag", name);
        }

        return tag;
    }

    public List<Tag> all() {
        return jdbcTemplate.query("select * from tag", RowMappers.TAG_ROW_MAPPER);
    }
}
