package com.example.test_xuong.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Nationalized;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "staff")
public class Staff {
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
    @Column(name = "account_fe")
    private String accountFe;

    @Nationalized
    @Column(name = "account_fpt")
    private String accountFpt;

    @Nationalized
    @Column(name = "name")
    private String name;

    @Nationalized
    @Column(name = "staff_code")
    private String staffCode;
    @JsonManagedReference
    @OneToMany(mappedBy = "idStaff", cascade = CascadeType.ALL)
    private List<StaffMajorFacility> staffMajorFacilities;

}