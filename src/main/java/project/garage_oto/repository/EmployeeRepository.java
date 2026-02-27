package project.garage_oto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.garage_oto.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
    @Query("select max(e.maNV) from Employee e")
    String getMaxMaNV();

    boolean existsByEmail(String email);
    boolean existsBySdt(String phone);
}
