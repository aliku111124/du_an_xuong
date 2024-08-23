package com.example.test_xuong.respository;

import com.example.test_xuong.entity.DepartmentFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface DepartmentFacilityRepository extends JpaRepository<DepartmentFacility, UUID> {
    @Query("""
        select d from DepartmentFacility d 
        where d.idStaff = :id
""")
//    order by d.createdDate desc
    List<DepartmentFacility> getByIDStaff(UUID id);


}