package com.epam.esm.service;

import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
public class TagServiceImpl implements TagService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    RowMapper<Tag> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Tag(resultSet.getInt("id"), resultSet.getString("name"));//todo should i use constant class instead had codded column names?
    };

    @Override
    public void addTag(Tag newTag) {
        if (newTag.getId() == null) {
            jdbcTemplate.update("insert into tag values (?, ?)", newTag.getId(), newTag.getName());
        }
    }

    @Override
    public int deleteTag(Tag tag) {
        return jdbcTemplate.update("delete from tag where id=?",tag.getId());
    }

    @Override
    public Tag findTagById(Integer id) {
         Tag tag = jdbcTemplate.queryForObject("select * from tag where id = ?", new Object[]{id}, ROW_MAPPER);
         return tag;
    }

    @Override
    public Tag findTagByName(String name) {
        Tag tag = jdbcTemplate.queryForObject("select * from tag where name = ?", new Object[]{name}, ROW_MAPPER);
        return tag;
    }

    @Override
    public List<Tag> getAllTags() {
        return jdbcTemplate.query("select * from tag", ROW_MAPPER);
    }
}
