package com.example.test_xuong.respository;

import com.example.test_xuong.entity.StaffMajorFacility;
import com.example.test_xuong.respon.StaffFacilityRespon;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface StaffMajorFacilityRepository extends JpaRepository<StaffMajorFacility, UUID> {

    @Query("""
        select new com.example.test_xuong.respon.StaffFacilityRespon(
        sf.id,
        sf.idMajorFacility.idMajor.name,
        sf.idMajorFacility.idDepartmentFacility.idFacility.name,
        sf.idMajorFacility.idDepartmentFacility.idDepartment.name
        )
        from StaffMajorFacility sf
        where sf.idStaff.id = :idStaff
    """)
    List<StaffFacilityRespon> getAllStaffMajorFacilities(UUID idStaff);
    @Transactional
    @Modifying
    @Query("""
        delete from StaffMajorFacility sf
        where sf.id = :id
""")
    void deleteByIDStaff(UUID id);
}
