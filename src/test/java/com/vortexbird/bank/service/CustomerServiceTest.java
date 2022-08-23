package com.vortexbird.bank.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.Rollback;

import com.vortexbird.bank.domain.Customer;
import com.vortexbird.bank.domain.DocumentType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Rollback(false)
class CustomerServiceTest {


    @Autowired
    CustomerService customerService;

    @Autowired
    DocumentTypeService documentTypeService;

    @Test
    @Order(1)
    void debeCrearUnCustomer() throws Exception{
        //Arange
        DocumentType documentType = null;
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

        Optional<DocumentType> documentTypeOptional = documentTypeService.findById(1);

        if(documentTypeOptional.isPresent()) {
            documentType = documentTypeOptional.get();
        }

        customer.setDocumentType(documentType);

        //Act
        customerService.save(customer);

        //Assert
        assertNotNull(customer);


    }

    @Test
    @Order(2)
    void debeConsultarUnCustomer() throws Exception{
        //Arrange
        Optional<Customer> customerOptional = null;

        //Act
        customerOptional = customerService.findById(2020);


        //Assert
        assertTrue(customerOptional.isPresent());
    }

    @Test
    @Order(3)
    void debeModificarUnCustomer() throws Exception {

        //Arrange
        Customer customer = null;
        Optional<Customer> customerOptional = null;
        customerOptional = customerService.findById(2020);
        assertTrue(customerOptional.isPresent());

        customer = customerOptional.get();
        customer.setEnable(false);

        //Act
        customerService.update(customer);

        //Assert
        assertFalse(customer.getEnable());

    }

    @Test
    @Order(4)
    void debeBorrarUnCustomer() throws Exception{
        //Arrange
        Customer customer = null;
        Optional<Customer> customerOptional = null;
        customerOptional = customerService.findById(2020);
        assertTrue(customerOptional.isPresent());
        customer = customerOptional.get();

        //Act
        customerService.delete(customer);
    }

}
