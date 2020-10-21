package com.epam.esm.config;

import com.mysql.cj.jdbc.MysqlDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class AppConfiguration {

    @Bean
    public DataSource getDataSource() {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setDatabaseName("gift");
        dataSource.setServerName("localhost");
        dataSource.setPort(3306);
        dataSource.setUser("victor");
        dataSource.setPassword("victor");
        try {
            dataSource.setServerTimezone("UTC");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return dataSource;
    }
}
