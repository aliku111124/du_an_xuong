package com.example.test_xuong.respon;

import com.example.test_xuong.entity.Department;
import com.example.test_xuong.entity.Facility;
import com.example.test_xuong.entity.Staff;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class DepartmentFacilityRespon {
    private UUID id;


    private Short status;


    private Long createdDate;


    private Long lastModifiedDate;


    private String nameDepartment;


    private String nameFacility;


    private String nameStaff;
}
