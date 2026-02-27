package project.garage_oto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import project.garage_oto.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
    @Query("select max(c.maKH) from Customer c")
    String getMaxMaKH();

    boolean existsByEmail(String email);
    boolean existsBySdt(String phone);
}
