package com.example.test_xuong.request;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.NotFound;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NhanVienRequert {

    private UUID id;

    private Short status;

    @NotBlank(message = "khong dc de trong")
    @Size(max = 100, message = "Nhập phải nhỏ 100 ký tự")
    @Pattern(regexp = "^[\\w.-]+@fe.edu.vn$", message = "Tài khoản FPT phải có định dạng đúng và kết thúc bằng @fe.edu.vn")
    private String accountFe;




    @NotBlank(message = "khong dc de trong")
    @Size(max = 100, message = "Nhập phải nhỏ 100 ký tự")
    @Pattern(regexp = "^[\\w.-]+@fpt.edu.vn$", message = "Tài khoản FPT phải có định dạng đúng và kết thúc bằng @fpt.edu.vn")

    private String accountFpt;

    @NotBlank(message = "khong dc de trong")
    @Size(max = 100, message = "Nhập phải nhỏ 100 ký tự")
    private String name;


    @Size( max = 15, message = "Mã phải nhỏ 15 ký tự")
    @NotBlank(message = "khong dc de trong")
    private String staffCode;
}
