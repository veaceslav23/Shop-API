package com.project.carrental.config;

import com.zaxxer.hikari.HikariDataSource;
import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.SessionFactory;
import org.hibernate.internal.SessionFactoryImpl;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;

@Configuration
@EntityScan(basePackages = {"com.project.carrental.persistence.model"})
@EnableJpaRepositories(basePackages = {"com.project.carrental.persistence.repository"})
@EnableJpaAuditing(dateTimeProviderRef = "auditDateTimeProvider")
public class PersistenceConfig {
}
