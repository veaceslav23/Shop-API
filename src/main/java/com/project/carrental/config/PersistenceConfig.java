package com.project.carrental.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"com.project.carrental.persistence.model"})
@EnableJpaRepositories(basePackages = {"com.project.carrental.persistence.repository"})
@EnableJpaAuditing(dateTimeProviderRef = "auditDateTimeProvider")
public class PersistenceConfig {
}
