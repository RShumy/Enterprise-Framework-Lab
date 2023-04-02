package ro.ubb.serverConfig;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfig {
    @Bean
    JdbcOperations jdbcOperations(){
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        jdbcTemplate.setDataSource(dataSource());
        return jdbcTemplate;
    }

    private DataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:postgresql://localhost:5432/MedicalScheduling");
        basicDataSource.setUsername(System.getProperty("userName"));
        basicDataSource.setPassword(System.getProperty("password"));
        basicDataSource.setInitialSize(2);
        return basicDataSource;
    }
}
