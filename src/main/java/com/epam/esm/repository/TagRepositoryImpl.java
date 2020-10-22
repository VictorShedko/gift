package com.epam.esm.repository;

import com.epam.esm.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.sql.DataSource;
import java.sql.ResultSet;
import java.util.List;

@Repository
public class TagRepositoryImpl extends GiftRepository implements TagRepository {



    RowMapper<Tag> ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Tag(resultSet.getInt("id"), resultSet.getString("name"));//todo should i use constant class instead had codded column names?
    };

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
