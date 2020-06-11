package pl.akademiaspringa.homework_week7.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class ConfigMysql {

    @Value("${database.username}")
    private String username;
    @Value("${database.password}")
    private String password;
    @Value("${database.url}")
    private String url;
    @Value("${database.driverClassName}")
    private String driverClassName;

    @Bean
    public DataSource getDataSource() {
        return DataSourceBuilder
                .create()
                .url(url)
                .username(username)
                .password(password)
                .driverClassName(driverClassName)
                .build();
    }

    @Bean
    public JdbcTemplate getJdbcTemplate() {
        return new JdbcTemplate(getDataSource());
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void initCars() {
//        String sqlDrop ="Drop table cars";
//        String sql = "CREATE table cars (" +
//                "id int AUTO_INCREMENT Primary key," +
//                "mark varchar(30)," +
//                "model varchar(30)," +
//                "color varchar(30)," +
//                "year varchar(5));";
//        getJdbcTemplate().update(sqlDrop);
//        getJdbcTemplate().update(sql);
//    }
//    @EventListener(ApplicationReadyEvent.class)
//    public void initArticle() {
//        String sqlDrop ="Drop table articles";
//        String sql = "CREATE table articles (" +
//                "id int AUTO_INCREMENT Primary key," +
//                "author varchar(2000)," +
//                "title varchar(2000)," +
//                "description varchar(2000)," +
//                "url varchar(2000)," +
//                "url_to_image varchar(2000));";
//        getJdbcTemplate().update(sqlDrop);
//        getJdbcTemplate().update(sql);
//    }

}
