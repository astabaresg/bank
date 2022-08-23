package com.vortexbird.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.management.RuntimeErrorException;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.bank.domain.Customer;
import com.vortexbird.bank.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    Validator validator;

    @Override
    public void validate(Customer entity) throws ConstraintViolationException {
        Set<ConstraintViolation<Customer>> constraintViolations = validator.validate(entity);

        if(!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }

    }

    @Override
    @Transactional( readOnly = true )
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    @Transactional( readOnly = true )
    public Optional<Customer> findById(Integer id) {
        return customerRepository.findById(id);
    }

    @Override
    @Transactional( readOnly = true )
    public Long count() {
        return customerRepository.count();
    }

    @Override
    @Transactional( readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class )
    public Customer save(Customer entity) throws Exception {
        if(entity == null) {
            throw new Exception("El customer es nulo");
        }

        validate(entity);

        if(customerRepository.existsById(entity.getCustId()) == true) {
            throw new Exception("El customer con id: " +entity.getCustId()+" ya existe" );
        }

        return customerRepository.save(entity);
    }

    @Override
    @Transactional( readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class )
    public Customer update(Customer entity) throws Exception {
        if(entity == null) {
            throw new Exception("El customer es nulo");
        }

        validate(entity);

        if(customerRepository.existsById(entity.getCustId()) == false) {
            throw new Exception("El customer con id: " +entity.getCustId()+" no existe" );
        }

        return customerRepository.save(entity);
    }

    @Override
    @Transactional( readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class )
    public void delete(Customer entity) throws Exception {
        if(entity == null) {
            throw new Exception("El customer es nulo");
        }

        if(entity.getCustId() == null || entity.getCustId() == 0 ) {
            throw new Exception("El customer id es nulo");
        }

        if(!customerRepository.existsById(entity.getCustId())) {
            throw new Exception("El customer con id: " +entity.getCustId()+" no existe" );
        }

        customerRepository.findById(entity.getCustId()).ifPresent(customer ->{
            if (!customer.getAccounts().isEmpty()) {
                throw new RuntimeException( "El customer tiene accounts asociadas");
            }
            if (!customer.getRegisteredAccounts().isEmpty()) {
                throw new RuntimeException( "El customer tiene RegisteredAccounts asociadas");
            }
        });

        customerRepository.deleteById(entity.getCustId());

    }

    @Override
    @Transactional( readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class )
    public void deleteById(Integer id) throws Exception {

        if(id == null || id<= 0) {
            throw new Exception("El id es nulo o menor igual que 0");
        }

        if(!customerRepository.existsById(id)) {
            throw new Exception("El customer con id: " +id+" no existe" );
        }

        delete(customerRepository.findById(id).get());

    }

}
