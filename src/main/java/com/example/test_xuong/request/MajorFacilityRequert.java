package com.example.test_xuong.request;

import ch.qos.logback.core.status.Status;
import com.example.test_xuong.entity.Department;
import com.example.test_xuong.entity.DepartmentFacility;
import com.example.test_xuong.entity.Facility;
import com.example.test_xuong.entity.Major;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MajorFacilityRequert {
    private UUID id;
    private Major idMajor;
    private Facility idFacility;
    private Department idDepartment;
    private DepartmentFacility idDepartmentFacility;
//    private UUID idMajor;
//    private UUID idFacility;
//    private UUID idDepartment;
//    private UUID idDepartmentFacility;

//    private String nameDepartment;
//    private String nameFacility;
//    private String nameMajor;
    private Short status;
}
