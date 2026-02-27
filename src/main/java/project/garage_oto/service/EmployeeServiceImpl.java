package project.garage_oto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.garage_oto.exception.AlreadyExistException;
import project.garage_oto.exception.BadRequestException;
import project.garage_oto.exception.NotFoundException;
import project.garage_oto.model.Employee;
import project.garage_oto.model.Role;
import project.garage_oto.repository.EmployeeRepository;

import java.util.UUID;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    private String generateMaNV() {
        String maNV = employeeRepository.getMaxMaNV();
        if (maNV == null) {
            return "NV01";
        }
        int number = Integer.parseInt(maNV.substring((2)));
        number++;
        return String.format("NV%02d", number);
    }

    private String generateRandomPassword() {
        return UUID.randomUUID().toString().substring(0, 6);
    }

    private boolean existByEmail(String email) {
        return employeeRepository.existsByEmail(email);
    }

    private boolean existByPhone(String phone) {
        return employeeRepository.existsBySdt(phone);
    }

    @Override
    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    @Override
    public Employee getEmployeeById(String maNV) {
        return employeeRepository.findById(maNV).orElseThrow(
                () -> new NotFoundException("Employee not found")
        );
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        if (employee.getSdt() == null || employee.getSdt().isBlank()) {
            throw new BadRequestException("Phone number is required");
        }
        if (employee.getEmail() == null || employee.getEmail().isBlank()) {
            throw new BadRequestException("Email is required");
        }
        if (existByPhone(employee.getSdt())) {
            throw new AlreadyExistException("Phone number already exist");
        }
        if (existByEmail(employee.getEmail())) {
            throw new AlreadyExistException("Email already exist");
        }

        employee.setMaNV(generateMaNV());
        employee.setMatKhau(generateRandomPassword());
        if (employee.getRole() == null) {
            employee.setRole(Role.ROLE_EMPLOYEE);
        }
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(String maNV, Employee employee) {
        Employee oldEmployee = getEmployeeById(maNV);
        if (employee.getHoTen() != null) {
            oldEmployee.setHoTen(employee.getHoTen());
        }
        if (employee.getSdt() != null) {
            oldEmployee.setSdt(employee.getSdt());
        }
        if (employee.getEmail() != null) {
            oldEmployee.setEmail(employee.getEmail());
        }
        return employeeRepository.save(oldEmployee);
    }
}
