package com.vortexbird.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vortexbird.bank.domain.DocumentType;

public interface DocumentTypeRepository extends JpaRepository<DocumentType, Integer> {

}
