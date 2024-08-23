package com.example.test_xuong.respository;

import com.example.test_xuong.entity.Major;
import com.example.test_xuong.entity.MajorFacility;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface MajorRepository extends JpaRepository<Major, UUID> {

}