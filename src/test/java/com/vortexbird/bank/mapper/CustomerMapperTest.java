package com.vortexbird.bank.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.vortexbird.bank.domain.Customer;
import com.vortexbird.bank.domain.DocumentType;
import com.vortexbird.bank.dto.CustomerDTO;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerMapperTest {

    @Autowired
    CustomerMapper customerMapper;

    @Test
    void debeMappearDeCustomeraCustomerDTO(){
        //Arrange
        Customer customer = new Customer();
        customer.setAccounts(null);
        customer.setAddress("AV Siempre Viva");
        customer.setCustId(2020);
        customer.setEmail("homerojsimson@mapu.com");
        customer.setEnable(true);
        customer.setName("Homero J Simpson");
        customer.setPhone("123123123");
        customer.setRegisteredAccounts(null);
        customer.setToken(UUID.randomUUID().toString().toUpperCase());

        CustomerDTO customerDTO = null;

        //Act
        customerDTO = customerMapper.toCustomerDTO(customer);

        assertNotNull(customerDTO);

    }

    @Test
    void debeMappearDeCustomerDTOaCustomer(){
        //Arrange
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setAddress("AV Siempre Viva");
        customerDTO.setCustId(2020);
        customerDTO.setEmail("homerojsimson@mapu.com");
        customerDTO.setEnable(true);
        customerDTO.setName("Homero J Simpson");
        customerDTO.setPhone("123123123");
        customerDTO.setToken(UUID.randomUUID().toString().toUpperCase());
        customerDTO.setDotyId(1);
        Customer customer = null;

        //Act
        customer = customerMapper.toCustomer(customerDTO);

        //Assert
        assertNotNull(customer);
    }


}
