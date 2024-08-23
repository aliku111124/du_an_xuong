package com.example.test_xuong.respository;

import com.example.test_xuong.entity.DepartmentFacility;
import com.example.test_xuong.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface FacilityRepository extends JpaRepository<Facility, UUID> {
    @Query("""
        select d.id from Facility d 
        where d.id = :id
        order by d.createdDate desc
""")
    Optional<Facility> getByIDFacility(UUID id);
}