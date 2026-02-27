package project.garage_oto.dto.Employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.garage_oto.model.Role;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmployeeDTO {
    private String maNV;
    private String hoTen;
    private String sdt;
    private String email;
    private Role role;
}
