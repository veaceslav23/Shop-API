package com.project.carrental.persistence.model;

import org.hibernate.annotations.Type;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "images")
public class ImageEntity {
    @Id
    @Column(name = "id",
            columnDefinition = "VARCHAR(36) NOT NULL")
    @GeneratedValue
    @Type(type = "uuid-char")
    private UUID id;

    @Column
    private String code;
}
