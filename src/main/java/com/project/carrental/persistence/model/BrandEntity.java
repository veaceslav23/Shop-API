package com.project.carrental.persistence.model;

import org.hibernate.annotations.Type;

import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.Constraint;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "brands")
@ToString(exclude = "cars")
@EqualsAndHashCode(exclude = "cars")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BrandEntity {
    @Id
    @Column(name = "id", columnDefinition = "VARCHAR(36) NOT NULL")
    private String id;

    @Column
    private String name;
}
