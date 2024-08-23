package com.example.test_xuong.respon;

import com.example.test_xuong.entity.MajorFacility;
import com.example.test_xuong.entity.Staff;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class StaffFacilityRespon {

    private UUID id;


//
//    private MajorFacility idMajorFacility;
//
//
//    private Staff idStaff;
    private String nameDepartment;
    private String nameFacility;


    private String nameMajor;
}
