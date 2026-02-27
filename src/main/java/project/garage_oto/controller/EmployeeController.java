package project.garage_oto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.garage_oto.dto.Employee.EmployeeDTO;
import project.garage_oto.mapper.EmployeeMapper;
import project.garage_oto.model.Employee;
import project.garage_oto.service.EmployeeService;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeMapper employeeMapper;

    @GetMapping("/employees")
    public ResponseEntity<Page<EmployeeDTO>> getEmployees(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("maNV"));
        Page<Employee> employeePage = employeeService.getAllEmployees(pageable);
        Page<EmployeeDTO> dtoPage = employeePage.map(employeeMapper::toEmployeeDTO);
        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/employees/{maNV}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable String maNV) {
        Employee employee = employeeService.getEmployeeById(maNV);
        return ResponseEntity.ok(employeeMapper.toEmployeeDTO(employee));
    }

    @PutMapping("/employees/{maNV}")
    public ResponseEntity<EmployeeDTO> updateEmployee(
            @PathVariable String maNV,
            @RequestBody EmployeeDTO employeeDTO) {
        Employee updatedEmployee = employeeService.updateEmployee(maNV, employeeMapper.toEmployee(employeeDTO));
        return ResponseEntity.ok(employeeMapper.toEmployeeDTO(updatedEmployee));
    }

    @PostMapping("/employees")
    public ResponseEntity<EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeService.saveEmployee(employeeMapper.toEmployee(employeeDTO));
        return ResponseEntity.ok(employeeMapper.toEmployeeDTO(employee));
    }
}
