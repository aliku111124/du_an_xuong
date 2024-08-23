package com.example.test_xuong.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "department_facility")
public class DepartmentFacility {
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_department")
    private Department idDepartment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_facility")
    private Facility idFacility;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_staff")
    private Staff idStaff;

}