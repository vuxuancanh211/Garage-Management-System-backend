package project.garage_oto.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "khach_hang")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {
    @Id
    @Column(length = 10)
    private String maKH;

    private String hoTen;
    private String sdt;
    private String email;
    private String diaChi;
    private String matKhau;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_CUSTOMER;
}
