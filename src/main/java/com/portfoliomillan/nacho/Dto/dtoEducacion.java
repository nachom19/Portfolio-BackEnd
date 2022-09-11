
package com.portfoliomillan.nacho.Dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class dtoEducacion {
   @NotBlank 
   private String nombreE;
   @NotBlank 
   private String descripcionE;  

    public dtoEducacion() {
    }

    public dtoEducacion(String nombreE, String descripcionE) {
        this.nombreE = nombreE;
        this.descripcionE = descripcionE;
    }
   
   
}
