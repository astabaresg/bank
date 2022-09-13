package com.vortexbird.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DocumentTypeDTO {

    @NotNull
    @Min(1)
    private Integer dotyId;

    @NotNull
    private Boolean enable;

    @NotNull
    @NotEmpty
    @Size(min = 4, max=255)
    private String name;

}
