package com.example.test_xuong.respository;

import com.example.test_xuong.entity.Department;
import com.example.test_xuong.entity.MajorFacility;
import com.example.test_xuong.respon.MajorFacilityRespon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface MajorFacilityRepository extends JpaRepository<MajorFacility, UUID> {
//    @Query("""
//        select new com.example.test_xuong.respon.MajorFacilityRespon(
//         mf.id,
//         m.id,
//         f.id,
//         d.id,
//         d.name,
//         f.name,
//         m.name
//        )
//        from DepartmentFacility  df  join Facility f on f.id = df.idFacility
//             									join Department d on d.id =df.idDepartment
//             									join MajorFacility mf on df.id = mf.idDepartmentFacility
//             									join Major m on m.id = mf.idMajor
//""")
//    List<MajorFacilityRespon> getAllMajorFacilities();
//    @Query("""
//""")
//    List<MajorFacilityRespon> getAllMajorFacilities();
    @Query("""
    select new com.example.test_xuong.respon.MajorFacilityRespon(
    mf.id,
    mf.idMajor.name,
    mf.idDepartmentFacility.idFacility.name,
    mf.idDepartmentFacility.idDepartment.name
    )
    from MajorFacility mf
    
""")
    List<MajorFacilityRespon> getAllMajorFacilityRespon();

//    @Query("""
//        select department  from Department department
//        where department.
//""")
//    List<Department> getAllByIdFacility();
}