package com.example.test_xuong.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "status", columnDefinition = "tinyint")
    private Short status;

    @Column(name = "created_date")
    private Long createdDate;

    @Column(name = "last_modified_date")
    private Long lastModifiedDate;

    @Nationalized
    @Column(name = "code")
    private String code;

    @Nationalized
    @Column(name = "name")
    private String name;

}