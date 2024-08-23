package com.example.test_xuong.respository;

import com.example.test_xuong.entity.Department;
import com.example.test_xuong.entity.Facility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface DepartmentRepository extends JpaRepository<Department, UUID> {
    @Query("""
        select d.id from Department d 
        where d.id = :id
        order by d.createdDate desc
""")
    Optional<Department> getByIDDepartment(UUID id);
}