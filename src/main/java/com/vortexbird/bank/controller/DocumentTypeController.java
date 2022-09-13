package com.vortexbird.bank.controller;

import com.vortexbird.bank.domain.DocumentType;
import com.vortexbird.bank.dto.DocumentTypeDTO;
import com.vortexbird.bank.mapper.DocumentTypeMapper;
import com.vortexbird.bank.service.DocumentTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/document-types")
@CrossOrigin("*")
public class DocumentTypeController {

    @Autowired
    DocumentTypeService documentTypeService;

    @Autowired
    DocumentTypeMapper documentTypeMapper;

    @GetMapping
    public ResponseEntity<?>findAll() throws Exception{

        List<DocumentType> documentTypes = documentTypeService.findAll();
        List<DocumentTypeDTO> documentTypeDTOs = documentTypeMapper.toDocumentTypeDTOs(documentTypes);

        return ResponseEntity.ok().body(documentTypeDTOs);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) throws Exception{

        DocumentType documentType = null;
        DocumentTypeDTO documentTypeDTO = null;
        Optional<DocumentType> optionalDocumentType = documentTypeService.findById(id);

        if(!optionalDocumentType.isPresent()){
            return ResponseEntity.ok().body(null);
        }

        documentType = optionalDocumentType.get();
        documentTypeDTO = documentTypeMapper.toDocumentTypeDTO(documentType);

        return ResponseEntity.ok().body(documentTypeDTO);

    }

}
