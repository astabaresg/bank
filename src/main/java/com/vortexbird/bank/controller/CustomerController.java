package com.vortexbird.bank.controller;

import com.vortexbird.bank.domain.Customer;
import com.vortexbird.bank.dto.CustomerDTO;
import com.vortexbird.bank.mapper.CustomerMapper;
import com.vortexbird.bank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin("*")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    CustomerMapper customerMapper;

    @GetMapping()
    public ResponseEntity<?> findAll() throws Exception{
        List<Customer> customers = customerService.findAll();
        List<CustomerDTO> customerDTOS = customerMapper.toCustomerDTOs(customers);

        return ResponseEntity.ok().body(customerDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) throws Exception{
        Customer customer = null;
        CustomerDTO customerDTO = null;
        Optional<Customer> customerOptional = customerService.findById(id);
        if(!customerOptional.isPresent()){
            return ResponseEntity.ok().body(null);
        }

        customer = customerOptional.get();
        customerDTO = customerMapper.toCustomerDTO(customer);
        return  ResponseEntity.ok().body(customerDTO);
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody @Valid CustomerDTO customerDTO) throws Exception{
        Customer customer = customerMapper.toCustomer(customerDTO);

        customer = customerService.save(customer);
        customerDTO = customerMapper.toCustomerDTO(customer);

        return ResponseEntity.ok().body(customerDTO);
    }

    @PutMapping()
    public ResponseEntity<?> update(@RequestBody @Valid CustomerDTO customerDTO) throws Exception{
        Customer customer = customerMapper.toCustomer(customerDTO);

        customer = customerService.update(customer);
        customerDTO = customerMapper.toCustomerDTO(customer);

        return ResponseEntity.ok().body(customerDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) throws Exception{
        customerService.deleteById(id);
        return ResponseEntity.ok().body(null);
    }

}
