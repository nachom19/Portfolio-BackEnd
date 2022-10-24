
package com.portfoliomillan.nacho.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Persona {
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private int id;
    
    @NotNull
    @Size (min = 1, max = 50, message = "Error en la longitud del campo")
    private String nombre;
    
    @NotNull
    @Size (min = 1, max = 50, message = "Error en la longitud del campo")
    private String apellido;
    
    @NotNull
    private String descripcion;
        
    private String img;

    //Constructores
    public Persona() {
    }

    public Persona(String nombre, String apellido, String descripcion, String img) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.descripcion = descripcion;
        this.img = img;
    }
}
