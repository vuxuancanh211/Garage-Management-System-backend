package project.garage_oto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "nhan_vien")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee {
    @Id
    @Column(length = 10)
    private String maNV;
    private String hoTen;
    private String sdt;
    private String email;
    private String matKhau;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_EMPLOYEE;
}
