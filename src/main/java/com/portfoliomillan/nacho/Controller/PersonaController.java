package com.portfoliomillan.nacho.Controller;

import com.portfoliomillan.nacho.Dto.dtoPersona;
import com.portfoliomillan.nacho.Entity.Persona;
import com.portfoliomillan.nacho.Security.Controller.Mensaje;
import com.portfoliomillan.nacho.Service.ImpPersonaService;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personas")
@CrossOrigin(origins = {"https://portfoliomillan-frontend.web.app", "http://localhost:4200"})
public class PersonaController {

    @Autowired
    ImpPersonaService iPersonaService;

    @GetMapping("/listado")
    public ResponseEntity<List<Persona>> list() {
        List<Persona> list = iPersonaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Persona> getById(@PathVariable("id") int id) {
        if (!iPersonaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Persona persona = iPersonaService.getOne(id).get();
        return new ResponseEntity(persona, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping ("/crear")
    public ResponseEntity<?> create(@RequestBody dtoPersona dtopersona) {
        if (StringUtils.isBlank(dtopersona.getNombre())) 
            return new ResponseEntity(new Mensaje("El nombre de la Persona es obligatorio"), HttpStatus.BAD_REQUEST);
        if (iPersonaService.existsByNombre(dtopersona.getNombre()))
            return new  ResponseEntity(new Mensaje("Esa persona ya existe"), HttpStatus.BAD_REQUEST);
        
        Persona persona = new Persona (dtopersona.getNombre(), dtopersona.getApellido(), dtopersona.getDescripcion(), dtopersona.getImg());
        iPersonaService.save(persona);
        
        return new ResponseEntity(new Mensaje("Persona agregada"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoPersona dtopersona) {
        //Chequeo de existenca de la persona a editar
        if (!iPersonaService.existsById(id)) {
            return new ResponseEntity(new Mensaje("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        //Compara nombres de persona
        if (iPersonaService.existsByNombre(dtopersona.getNombre()) && iPersonaService.getByNombre(dtopersona.getNombre()).get().getId() != id) {
            return new ResponseEntity(new Mensaje("Esa persona ya existe"), HttpStatus.BAD_REQUEST);
        }
        // No puede quedar vacio el campo
        if (StringUtils.isBlank(dtopersona.getNombre())) {
            return new ResponseEntity(new Mensaje("El nombre de la persona es obligatorio"), HttpStatus.BAD_REQUEST);
        }

        Persona persona = iPersonaService.getOne(id).get();
        persona.setNombre(dtopersona.getNombre());
        persona.setApellido(dtopersona.getApellido());
        persona.setDescripcion((dtopersona.getDescripcion()));
        persona.setImg(dtopersona.getImg());

        iPersonaService.save(persona);
        return new ResponseEntity(new Mensaje("Persona actualizada con exito"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        //Chequeo de existenca de la persona a editar
        if (!iPersonaService.existsById(id))
            return new ResponseEntity (new Mensaje ("El ID no existe"), HttpStatus.NOT_FOUND);
        
        iPersonaService.delete(id);
        return new ResponseEntity(new Mensaje("Educación fue eliminada con exito"), HttpStatus.OK);
    }
}
