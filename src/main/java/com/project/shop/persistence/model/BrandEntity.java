package com.project.shop.persistence.model;

import org.hibernate.annotations.Type;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "brands")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BrandEntity {
    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(36) NOT NULL")
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    @Column
    private String name;
}
