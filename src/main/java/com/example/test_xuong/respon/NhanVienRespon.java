package com.example.test_xuong.respon;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class NhanVienRespon {

    private UUID id;

    @NotBlank
    private Short status;

    @NotBlank
    private String accountFe;

    @NotBlank
    private String accountFpt;

    @NotBlank
    private String name;

    @NotBlank
    private String staffCode;
}
