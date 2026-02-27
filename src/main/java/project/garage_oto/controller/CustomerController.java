package project.garage_oto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.garage_oto.dto.Customer.CustomerCreateDTO;
import project.garage_oto.dto.Customer.CustomerDTO;
import project.garage_oto.mapper.CustomerMapper;
import project.garage_oto.model.Customer;
import project.garage_oto.service.CustomerService;


@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private CustomerMapper customerMapper;

    @GetMapping("/customers")
    public ResponseEntity<Page<CustomerDTO>> getCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("maKH"));
        Page<Customer> customerPage = customerService.getAllCustomers(pageable);
        Page<CustomerDTO> dtoPage = customerPage.map(customerMapper::toCustomerDTO);
        return ResponseEntity.ok(dtoPage);
    }

    @GetMapping("/customers/{maKH}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable String maKH) {
        Customer customer = customerService.getCustomerById(maKH);
        return ResponseEntity.ok(customerMapper.toCustomerDTO(customer));
    }

    @PutMapping("/customers/{maKH}")
    public ResponseEntity<CustomerDTO> updateCustomer(
            @PathVariable String maKH,
            @RequestBody CustomerDTO customerDTO) {
        Customer updatedCustomer = customerService.updateCustomer(maKH, customerMapper.toCustomer(customerDTO));
        return ResponseEntity.ok(customerMapper.toCustomerDTO(updatedCustomer));
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDTO> addCustomer(@RequestBody CustomerCreateDTO customerCreateDTO) {
        Customer customer = customerService.saveCustomer(customerMapper.customerCreareDTOtoCustomer(customerCreateDTO));
        return ResponseEntity.ok(customerMapper.toCustomerDTO(customer));
    }
}
