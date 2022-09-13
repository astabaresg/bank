package com.vortexbird.bank.mapper;

import com.vortexbird.bank.domain.DocumentType;
import com.vortexbird.bank.dto.DocumentTypeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface DocumentTypeMapper {

    public DocumentTypeDTO toDocumentTypeDTO(DocumentType documentType);

    public DocumentType toDocumentType(DocumentTypeDTO documentTypeDTO);

    public List<DocumentTypeDTO> toDocumentTypeDTOs (List<DocumentType> documentTypes);

    public List<DocumentType> toDocumentTypes (List<DocumentTypeDTO> documentTypeDTOs);


}
