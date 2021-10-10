package com.project.carrental.persistence.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "created_at")
    @CreatedDate
    private ZonedDateTime createdAt;

    @Column(name = "updated_at")
    @LastModifiedDate
    private ZonedDateTime updatedAt;
}
