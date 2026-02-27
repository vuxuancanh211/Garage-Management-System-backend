package project.garage_oto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.garage_oto.exception.AlreadyExistException;
import project.garage_oto.exception.BadRequestException;
import project.garage_oto.exception.NotFoundException;
import project.garage_oto.model.Customer;
import project.garage_oto.model.Role;
import project.garage_oto.repository.CustomerRepository;


@Service
@Transactional
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CustomerRepository customerRepository;

    private String generateMaKH() {
        String maKH = customerRepository.getMaxMaKH();
        if (maKH == null) {
            return "KH0001";
        }
        int number = Integer.parseInt(maKH.substring((2)));
        number++;
        return String.format("KH%04d", number);
    }

    private boolean existByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }

    private boolean existByPhone(String phone) {
        return customerRepository.existsBySdt(phone);
    }

    @Override
    public Page<Customer> getAllCustomers(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Override
    public Customer getCustomerById(String maKH) {
        return customerRepository.findById(maKH).orElseThrow(
                () -> new NotFoundException("Customer not found")
        );
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        if (customer.getMatKhau() == null || customer.getMatKhau().isBlank()) {
            throw new BadRequestException("Password is required");
        }
        if (customer.getSdt() == null || customer.getSdt().isBlank()) {
            throw new BadRequestException("Phone number is required");
        }
        if (customer.getEmail() == null || customer.getEmail().isBlank()) {
            throw new BadRequestException("Email is required");
        }
        if (existByPhone(customer.getSdt())) {
            throw new AlreadyExistException("Phone number already exist");
        }
        if (existByEmail(customer.getEmail())) {
            throw new AlreadyExistException("Email already exist");
        }

        customer.setMaKH(generateMaKH());
        if (customer.getRole() == null) {
            customer.setRole(Role.ROLE_CUSTOMER);
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(String maKH, Customer customer) {
        Customer oldCustomer = getCustomerById(maKH);
        if (customer.getHoTen() != null) {
            oldCustomer.setHoTen(customer.getHoTen());
        }
        if (customer.getSdt() != null) {
            oldCustomer.setSdt(customer.getSdt());
        }
        if (customer.getEmail() != null) {
            oldCustomer.setEmail(customer.getEmail());
        }
        if (customer.getDiaChi() != null) {
            oldCustomer.setDiaChi(customer.getDiaChi());
        }
        return customerRepository.save(oldCustomer);
    }
}
