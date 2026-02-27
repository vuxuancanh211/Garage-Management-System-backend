package project.garage_oto.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.garage_oto.model.Customer;

import java.util.List;

public interface CustomerService {
    Page<Customer> getAllCustomers(Pageable pageable);
    Customer getCustomerById(String maKH);
    Customer saveCustomer(Customer customer);
    Customer updateCustomer(String maKH, Customer customer);
}
