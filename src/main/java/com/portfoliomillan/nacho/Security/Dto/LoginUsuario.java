
package com.portfoliomillan.nacho.Security.Dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUsuario {
    @NotBlank
    private String NombreUsuario;
    @NotBlank
    private String password;
    
}
