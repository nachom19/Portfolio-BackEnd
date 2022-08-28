
package com.portfoliomillan.nacho.Interface;

import com.portfoliomillan.nacho.Entity.Persona;
import java.util.List;


public interface IPersonaService {
    //Armar lista de persona
    public List<Persona> getPersona();
    
    //Guardar una persona
    public void savePersona (Persona persona);
    
    //Eliminar una persona usando ID
    public void deletePersona (Long id);
    
    //Buscar una persona usando ID
    public Persona findPersona (Long id);
}
