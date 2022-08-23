package com.vortexbird.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    @NotNull
    @Min(1)
    private Integer custId;

    @NotNull
    @NotEmpty
    @Size(min = 4, max=255)
    private String address;

    @NotNull
    @Size(min = 4, max=255)
    @Email
    private String email;

    @NotNull
    private Boolean enable;

    @NotNull
    @NotEmpty
    @Size(min = 4, max=255)
    private String name;

    @NotNull
    @NotEmpty
    @Size(min = 4, max=255)
    private String phone;

    private String token;

    @NotNull
    @Min(0)
    private Integer dotyId;

}
