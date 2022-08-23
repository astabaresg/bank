package com.vortexbird.bank.mapper;

import com.vortexbird.bank.domain.Customer;
import com.vortexbird.bank.dto.CustomerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CustomerMapper {

    @Mapping(source = "documentType.dotyId", target = "dotyId")
    public CustomerDTO toCustomerDTO(Customer customer);

    @Mapping(target = "documentType.dotyId", source = "dotyId")
    public Customer toCustomer(CustomerDTO customerDTO);

    public List<CustomerDTO> toCustomerDTOs(List<Customer> customers);

    public List<Customer> toCustomers(List<CustomerDTO> customerDTOS);
}
