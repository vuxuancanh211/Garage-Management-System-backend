package project.garage_oto.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.garage_oto.model.Employee;

public interface EmployeeService {
    Page<Employee> getAllEmployees(Pageable pageable);
    Employee getEmployeeById(String maNV);
    Employee saveEmployee(Employee employee);
    Employee updateEmployee(String maNV, Employee employee);
}
