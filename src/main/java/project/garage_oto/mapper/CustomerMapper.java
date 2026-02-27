package project.garage_oto.mapper;

import org.mapstruct.Mapper;
import project.garage_oto.dto.Customer.CustomerCreateDTO;
import project.garage_oto.dto.Customer.CustomerDTO;
import project.garage_oto.model.Customer;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    CustomerDTO toCustomerDTO(Customer customer);
    Customer toCustomer(CustomerDTO customerDTO);

    Customer customerCreareDTOtoCustomer(CustomerCreateDTO customerCreateDTO);
}
