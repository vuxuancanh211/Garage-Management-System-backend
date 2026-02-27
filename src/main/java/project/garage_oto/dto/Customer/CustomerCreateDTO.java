package project.garage_oto.dto.Customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.garage_oto.model.Role;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerCreateDTO {
    private String hoTen;
    private String sdt;
    private String email;
    private String diaChi;
    private String matKhau;
    private Role role;
}
