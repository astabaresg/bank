package com.vortexbird.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vortexbird.bank.domain.DocumentType;
import com.vortexbird.bank.repository.DocumentTypeRepository;

@Service
public class DocumentTypeServiceImpl implements DocumentTypeService{

    @Autowired
    DocumentTypeRepository documentTypeRepository;

    @Autowired
    Validator validator;

    @Override
    public void validate(DocumentType entity) throws ConstraintViolationException {
        Set<ConstraintViolation<DocumentType>> constraintViolations = validator.validate(entity);

        if(!constraintViolations.isEmpty()) {
            throw new ConstraintViolationException(constraintViolations);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<DocumentType> findAll() {
        return documentTypeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DocumentType> findById(Integer id) {
        return documentTypeRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Long count() {
        return documentTypeRepository.count();
    }

    @Override
    public DocumentType save(DocumentType entity) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DocumentType update(DocumentType entity) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(DocumentType entity) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteById(Integer id) throws Exception {
        // TODO Auto-generated method stub

    }

}

