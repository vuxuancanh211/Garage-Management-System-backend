package project.garage_oto.mapper;

import org.mapstruct.Mapper;
import project.garage_oto.dto.Employee.EmployeeDTO;
import project.garage_oto.model.Employee;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    Employee toEmployee(EmployeeDTO employeeDTO);
    EmployeeDTO toEmployeeDTO(Employee employee);
}
