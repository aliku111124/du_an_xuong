package com.example.test_xuong.controller;

import com.example.test_xuong.entity.*;
import com.example.test_xuong.request.MajorFacilityRequert;
import com.example.test_xuong.request.NhanVienRequert;
import com.example.test_xuong.respon.MajorFacilityRespon;
import com.example.test_xuong.respon.NhanVienRespon;
import com.example.test_xuong.respon.StaffFacilityRespon;
import com.example.test_xuong.respository.*;
import com.example.test_xuong.service.ImportService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Controller
@RequestMapping("/nhan-vien")
public class NhanVienController {
    @Autowired
    StaffRepository nhanVienRepository;
    @Autowired
    FacilityRepository coSoRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    DepartmentFacilityRepository departmentFacilityRepository;
    @Autowired
    MajorFacilityRepository majorFacilityRepository;
    @Autowired
    MajorRepository majorRepository;
    @Autowired
    StaffMajorFacilityRepository staffMajorFacilityRepository;

    @Autowired
    private ImportService excelService;

    UUID idStaff;
    @Autowired
    private StaffRepository staffRepository;

    //    @GetMapping("/hien-thi")
//    public List<NhanVienRespon> hienThi(){
//        return nhanVienRepository.getAll();
//    }
    @GetMapping("/hien-thi")
    public String hienThi(Model model) {
        model.addAttribute("listNhanVien", nhanVienRepository.getAll());
        model.addAttribute("staff", new Staff());
        return "Index";
    }

    @GetMapping("/viewAdd")
    public String viewAdd(Model model) {
        model.addAttribute("listNhanVien", nhanVienRepository.getAll());
        model.addAttribute("staff", new Staff());
        return "add";
    }

    @GetMapping("/viewUpdate/{id}")
    public String viewUpdate(@PathVariable("id") UUID id, Model model) {
        model.addAttribute("listNhanVien", nhanVienRepository.getAll());
        model.addAttribute("staff", nhanVienRepository.findById(id));
        return "update";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("staff") @Valid NhanVienRequert nVrequert, BindingResult result ,Model model) {
        if (result.hasErrors()) {
            return "add";
        }
        Staff staff = new Staff();
        staff.setStaffCode(nVrequert.getStaffCode());
        staff.setName(nVrequert.getName());
        staff.setAccountFpt(nVrequert.getAccountFpt().trim().replaceAll(" ",""));
        staff.setAccountFe(nVrequert.getAccountFe().trim().replaceAll(" ",""));
        staff.setStatus(Short.valueOf("1"));
        // Kiểm tra logic nghiệp vụ (mã nhân viên hoặc email đã tồn tại)
        List<Staff> allStaff = staffRepository.findAll();
        boolean hasErrors = false;

        for (Staff existingStaff : allStaff) {
            if (existingStaff.getStaffCode().equals(staff.getStaffCode())) {
                model.addAttribute("staffCode", "Mã nhân viên đã tồn tại.");
                hasErrors = true;
                break;
            }
            if (existingStaff.getAccountFpt().equals(staff.getAccountFpt())) {
                model.addAttribute("accountFpt", "Email FPT đã tồn tại.");
                hasErrors = true;
                break;
            }
            if (existingStaff.getAccountFe().equals(staff.getAccountFe())) {
                model.addAttribute("accountFe", "Email FE đã tồn tại.");
                hasErrors = true;
                break;
            }
        }
        if (hasErrors) {
            model.addAttribute("employee", staff);
            return "add";  // Trở lại trang biểu mẫu với thông báo lỗi
        }
        nhanVienRepository.save(staff);
        return "redirect:/nhan-vien/hien-thi";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("staff") @Valid NhanVienRequert nhanVienRequert, BindingResult result) {
        if (result.hasErrors()) {
            return "update";
        }
        Staff staff = new Staff();
        staff.setId(nhanVienRequert.getId());
        staff.setStaffCode(nhanVienRequert.getStaffCode());
        staff.setName(nhanVienRequert.getName());
        staff.setAccountFpt(nhanVienRequert.getAccountFpt());
        staff.setAccountFe(nhanVienRequert.getAccountFe());
        staff.setStatus(Short.valueOf("1"));
        nhanVienRepository.save(staff);
        return "redirect:/nhan-vien/hien-thi";
    }

    @GetMapping("/setStatus/{id}")
    public String setStatus(@PathVariable UUID id) {
        Staff staff = nhanVienRepository.getOne(id);
        if (staff.getStatus() == 1) {
            nhanVienRepository.getStatusByIdFail(id);
            return "redirect:/nhan-vien/hien-thi";
        } else {
            nhanVienRepository.getStatusById(id);
            return "redirect:/nhan-vien/hien-thi";
        }
    }

    UUID getIdStaff;

    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") UUID id, Model model) {
        Optional<Staff> staffOptional = nhanVienRepository.findById(id);

        if (staffOptional.isPresent()) {

            Staff staff = staffOptional.get();
            getIdStaff = staff.getId();
            model.addAttribute("staff", staff);
            model.addAttribute("listNhanVien", nhanVienRepository.getAll());
            model.addAttribute("listCoSo", coSoRepository.findAll());
            model.addAttribute("listMajor", majorRepository.findAll());
            model.addAttribute("listMajorFacility", majorFacilityRepository.findAll());
            model.addAttribute("listDepartmentFacility", departmentFacilityRepository.findAll());

            // Fetching facilities with the corrected UUID reference
            List<StaffFacilityRespon> facilities = staffMajorFacilityRepository.getAllStaffMajorFacilities(id);
            System.out.println("Querying with UUID: " + id);
            model.addAttribute("listStaffMajorFacility" , staffMajorFacilityRepository.getAllStaffMajorFacilities(id));
            model.addAttribute("listDepartment", departmentRepository.findAll());
            model.addAttribute("majorFacility", new MajorFacility());
        } else {
            return "redirect:/nhan-vien/hien-thi";
        }
        return "quanly";
    }



    //    Quản Lý Nhân Viên
//    @GetMapping("/viewAddBMCN")
//    public String viewAddBMCN(Model model) {
////        model.addAttribute("listNhanVien", nhanVienRepository.getAll());
////        model.addAttribute("listCoSo", coSoRepository.findAll());
////        model.addAttribute("listMajor", majorRepository.findAll());
////        model.addAttribute("listDepartmentFacility", departmentFacilityRepository.findAll());
////        model.addAttribute("listDepartment", departmentRepository.findAll());
////        model.addAttribute("departmentFacility", new DepartmentFacility());
////        model.addAttribute("idStaff", id);
//        return "addBMCN";
//    }

    //    @PostMapping("/addBMCN")
//    public String addBMCN(@ModelAttri)
    @Autowired
    HttpServletRequest rq;

//    @PostMapping("/addBMCN")
//    public String addBMCN(@ModelAttribute("majorFacility") MajorFacilityRequert requert, Model model) {
//        model.addAttribute("staff", nhanVienRepository.findById(idStaff));
//        DepartmentFacility departmentFacility = new DepartmentFacility();
////        Facility facility = new Facility();
////        Department department = new Department();
////        List<Department> listDepartment = departmentRepository.getByIDDepartment(department.getId());
////        List<Facility> listFacility = coSoRepository.getByIDFacility(facility.getId());
////        Department department1 = listDepartment.get(0);
////        Facility facility1 = listFacility.get(0);
//        departmentFacility.setIdFacility(coSoRepository.getOne(UUID.fromString(rq.getParameter("id"))));
//        departmentFacility.setIdDepartment(departmentRepository.getOne(UUID.fromString(rq.getParameter("id"))));
////        System.out.println(departmentRepository.getOne(UUID.fromString(rq.getParameter("idDepartment"))));
////        System.out.println(coSoRepository.getOne(UUID.fromString(rq.getParameter("idFacility"))));
////        System.out.println(majorRepository.getOne(UUID.fromString(rq.getParameter("id"))));
//        departmentFacility.setIdStaff(nhanVienRepository.getOne(idStaff));
//        departmentFacility.setStatus(Short.valueOf("1"));
////        departmentFacilityRepository.save(departmentFacility);
//////        model.addAttribute("MajorFacility", new MajorFacility());
////        DepartmentFacility departmentFacility = new DepartmentFacility();
//        //làm nốt phan duoi , trên fix đc rồi
//        //kiếm id DepartmentFacility
//        List<DepartmentFacility> listDepartmentFacility = departmentFacilityRepository.getByIDStaff(idStaff);
//        DepartmentFacility departmentFacility1 = listDepartmentFacility.get(0);
//        MajorFacility majorFacility = new MajorFacility();
//        majorFacility.setIdMajor(majorRepository.getOne(UUID.fromString(rq.getParameter("idMajor"))));//phan nay làm rồi
//        majorFacility.setIdDepartmentFacility(departmentFacility1);
//        majorFacility.setStatus(requert.getStatus());
//        majorFacilityRepository.save(majorFacility);
//        return "quanly";
//    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id , Model model) {
        model.addAttribute("staff", new Staff());

        model.addAttribute("listNhanVien", nhanVienRepository.getAll());
        model.addAttribute("listCoSo", coSoRepository.findAll());
        model.addAttribute("listMajor", majorRepository.findAll());
        model.addAttribute("listMajorFacility", majorFacilityRepository.findAll());
        model.addAttribute("listDepartmentFacility", departmentFacilityRepository.findAll());

        // Fetching facilities with the corrected UUID reference
        List<StaffFacilityRespon> facilities = staffMajorFacilityRepository.getAllStaffMajorFacilities(id);
        System.out.println("Querying with UUID: " + id);
        model.addAttribute("listStaffMajorFacility" , staffMajorFacilityRepository.getAllStaffMajorFacilities(id));
        model.addAttribute("listDepartment", departmentRepository.findAll());
        model.addAttribute("majorFacility", new MajorFacility());
        try {
            staffMajorFacilityRepository.deleteByIDStaff(id);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/error";
        }
        return "redirect:/nhan-vien/detail/"+getIdStaff.toString();
    }


    @PostMapping("/addBMCN")
    public String addBMCN(@ModelAttribute("majorFacility") MajorFacilityRequert requert, Model model) {
        return "quanly";
    }

    @GetMapping("/download/excel-template")
    public ResponseEntity<InputStreamResource> downloadEmployeeData() throws IOException {
        List<Staff> employees = staffRepository.findAll();

        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Staff");

            // Tạo tiêu đề cột
            Row headerRow = sheet.createRow(0);
            String[] columns = {"Staff Code", "Name", "Account FPT", "Account FE", "Bộ môn cơ sở"};
            for (int i = 0; i < columns.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columns[i]);
            }

            // Thêm dữ liệu vào các hàng
            int rowNum = 1;
            for (Staff employee : employees) {
                if (employee == null) {
                    continue; // Bỏ qua nhân viên nếu là null
                }

                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(employee.getStaffCode() != null ? employee.getStaffCode() : "");
                row.createCell(1).setCellValue(employee.getName() != null ? employee.getName() : "");
                row.createCell(2).setCellValue(employee.getAccountFpt() != null ? employee.getAccountFpt() : "");
                row.createCell(3).setCellValue(employee.getAccountFe() != null ? employee.getAccountFe() : "");

                StringBuilder majorFacilityNames = new StringBuilder();
                if (employee.getStaffMajorFacilities() != null) {
                    for (StaffMajorFacility majorFacility : employee.getStaffMajorFacilities()) {
                        if (majorFacility != null && majorFacility.getIdMajorFacility() != null && majorFacility.getIdMajorFacility().getIdMajor() != null) {
                            majorFacilityNames.append(majorFacility.getIdMajorFacility().getIdMajor().getName() != null ? majorFacility.getIdMajorFacility().getIdMajor().getName() : "").append("_");
                            majorFacilityNames.append(majorFacility.getIdMajorFacility().getIdDepartmentFacility().getIdDepartment().getName() != null ? majorFacility.getIdMajorFacility().getIdDepartmentFacility().getIdDepartment().getName() : "").append("_");
                            majorFacilityNames.append(majorFacility.getIdMajorFacility().getIdDepartmentFacility().getIdFacility().getName() != null ? majorFacility.getIdMajorFacility().getIdDepartmentFacility().getIdFacility().getName() : "").append("_");
                        }
                    }
                }

                // Loại bỏ dấu phân cách cuối cùng nếu có
                if (majorFacilityNames.length() > 0) {
                    majorFacilityNames.setLength(majorFacilityNames.length() - 1); // Loại bỏ dấu phân cách "_" cuối cùng
                }

                row.createCell(4).setCellValue(majorFacilityNames.toString());
            }

            // Ghi workbook ra stream
            workbook.write(out);

            ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());

            // Thiết lập headers để download file
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=employees.xlsx");

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(new InputStreamResource(in));
        }
    }

    @PostMapping("/import-staff")
    public String importStaff(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes, Model model) {
        try {
            excelService.importStaffFromExcel(file);
            // Ghi lại thời gian hiện tại
            String currentTime = getCurrentTime();

            // Ghi lại tên file
            String fileName = file.getOriginalFilename();

            System.out.println(currentTime);
            System.out.println(fileName);
            model.addAttribute("thoigian",currentTime);
            model.addAttribute("noidung",fileName);
            model.addAttribute("listNhanVien", nhanVienRepository.getAll());
            model.addAttribute("staff" , new Staff());
            redirectAttributes.addFlashAttribute("message", "Import thành công!");
        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", "Import thất bại! Vui lòng thử lại.");
        }
        return "redirect:/nhan-vien/hien-thi";
    }

    private static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
