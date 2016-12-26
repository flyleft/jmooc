package me.jcala.jmooc.conf;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
public class DruidDataSourceConfig implements EnvironmentAware {
    private RelaxedPropertyResolver propertyResolver;

    public void setEnvironment(Environment env) {
        this.propertyResolver = new RelaxedPropertyResolver(env, "spring.datasource.");

    }
    @Bean
    public DataSource dataSource() {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(propertyResolver.getProperty("url"));
        datasource.setUsername(propertyResolver.getProperty("username"));
        datasource.setPassword(propertyResolver.getProperty("password"));
        datasource.setDriverClassName(propertyResolver.getProperty("driverClassName"));
        datasource.setInitialSize(Integer.valueOf(propertyResolver.getProperty("druid.initial-size")));
        datasource.setMinIdle(Integer.valueOf(propertyResolver.getProperty("druid.min-idle")));
        datasource.setMaxWait(Long.valueOf(propertyResolver.getProperty("druid.max-wait")));
        datasource.setMaxActive(Integer.valueOf(propertyResolver.getProperty("druid.max-active")));
        datasource.setMinEvictableIdleTimeMillis(Long.valueOf(propertyResolver.getProperty("druid.min-evictable-idle-time-millis")));
        datasource.setPoolPreparedStatements(Boolean.valueOf(propertyResolver.getProperty("druid.poolPreparedStatements")));
        datasource.setMaxPoolPreparedStatementPerConnectionSize(Integer.valueOf(propertyResolver.getProperty("druid.max-active")));
        try {
            datasource.setFilters("stat,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }

}
