
package com.portfoliomillan.nacho.Dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class dtoExperiencia {
   @NotBlank 
   private String nombreE;
   @NotBlank 
   private String descripcionE;

    public dtoExperiencia() {
    }

    public dtoExperiencia(String nombreE, String descripcionE) {
        this.nombreE = nombreE;
        this.descripcionE = descripcionE;
    }
   
   
}
