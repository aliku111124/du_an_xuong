package com.example.test_xuong.respository;

import com.example.test_xuong.entity.DepartmentFacility;
import com.example.test_xuong.entity.Staff;
import com.example.test_xuong.request.NhanVienRequert;
import com.example.test_xuong.respon.MajorFacilityRespon;
import com.example.test_xuong.respon.NhanVienRespon;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface StaffRepository extends JpaRepository<Staff, UUID> {
    @Query("""
        select new com.example.test_xuong.respon.NhanVienRespon(
        nv.id,
        nv.status,
        nv.accountFe, 
        nv.accountFpt,
        nv.name,
        nv.staffCode)
        from Staff nv
""")
    List<NhanVienRespon> getAll();

    @Transactional
    @Modifying
    @Query("""
        update Staff nv 
        set nv.status = 1
        where nv.id = :id
""")
    void getStatusById(UUID id);

    @Transactional
    @Modifying
    @Query("""
        update Staff nv 
        set nv.status = 0
        where nv.id = :id
""")
    void getStatusByIdFail(UUID id);

    @Query("""
        select new com.example.test_xuong.respon.NhanVienRespon(
        nv.id,
        nv.status,
        nv.accountFe, 
        nv.accountFpt,
        nv.name,
        nv.staffCode)
        from Staff nv
        where nv.id = :id
""")
    List<NhanVienRespon> detailStaff(UUID id);


}