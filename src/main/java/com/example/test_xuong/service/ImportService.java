package com.example.test_xuong.service;

import com.example.test_xuong.entity.Staff;
import com.example.test_xuong.respository.StaffMajorFacilityRepository;
import com.example.test_xuong.respository.StaffRepository;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class ImportService {
    @Autowired
    private StaffRepository staffRepository;

    public void importStaffFromExcel(MultipartFile file) throws IOException {
        List<Staff> staffList = new ArrayList<>();

        // Đọc file Excel
        Workbook workbook = new XSSFWorkbook(file.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);

        Iterator<Row> rows = sheet.iterator();
        int rowNumber = 0;

        while (rows.hasNext()) {
            Row currentRow = rows.next();

            // Bỏ qua hàng tiêu đề
            if (rowNumber == 0) {
                rowNumber++;
                continue;
            }

            Staff staff = new Staff();
            staff.setStaffCode(currentRow.getCell(0).getStringCellValue());
            staff.setName(currentRow.getCell(1).getStringCellValue());
            staff.setAccountFpt(currentRow.getCell(2).getStringCellValue());
            staff.setAccountFe(currentRow.getCell(3).getStringCellValue());
            staff.setStatus(Short.valueOf("1"));

            staffList.add(staff);
        }

        workbook.close();

        // Lưu danh sách nhân viên vào cơ sở dữ liệu
        staffRepository.saveAll(staffList);
    }
}
