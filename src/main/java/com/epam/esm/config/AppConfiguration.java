package com.epam.esm.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.mysql.cj.jdbc.MysqlDataSource;

@Configuration
public class AppConfiguration {

    @Profile("!test")
    @Bean
    public DataSource getPoolDataSource() throws IOException {
        Properties properties = new Properties();
        String propFileName = "DB.properties";

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

        properties.load(inputStream);

        String url = properties.getProperty("url");
        String driver = properties.getProperty("driver");
        String username = properties.getProperty("username");
        String password = properties.getProperty("password");

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(driver);
        basicDataSource.setUrl(url);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        return basicDataSource;
    }

    public DataSource getDefaultSource() {
        MysqlDataSource dataSource = new MysqlDataSource();

        try {
            Properties properties = new Properties();
            String propFileName = "DB.properties";

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

            properties.load(inputStream);

            String db = properties.getProperty("db");
            String host = properties.getProperty("host");
            int port = Integer.parseInt(properties.getProperty("port"));
            String login = properties.getProperty("login");
            String password = properties.getProperty("password");

            dataSource.setDatabaseName(db);
            dataSource.setServerName(host);
            dataSource.setPort(port);
            dataSource.setUser(login);
            dataSource.setPassword(password);
            try {
                dataSource.setServerTimezone("UTC");
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (IOException io) {

        }

        return dataSource;
    }
}
