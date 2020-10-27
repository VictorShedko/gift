package com.epam.esm.repository.util;

import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Tag;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;

public class RowMappers {
    public static final RowMapper<GiftCertificate> GIFT_CERTIFICATE_ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new GiftCertificate(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getFloat("price"),
                resultSet.getTimestamp("creationTime"),
                resultSet.getTimestamp("updateTime"),
                resultSet.getInt("duration")
        );
    };

    public static final RowMapper<Tag> TAG_ROW_MAPPER = (ResultSet resultSet, int rowNum) -> {
        return new Tag(resultSet.getInt("id"), resultSet.getString("name"));//todo should i use constant class instead had codded column names?
    };
}
