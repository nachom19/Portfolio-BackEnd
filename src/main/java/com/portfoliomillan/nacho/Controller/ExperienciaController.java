package com.portfoliomillan.nacho.Controller;

import com.portfoliomillan.nacho.Dto.dtoExperiencia;
import com.portfoliomillan.nacho.Entity.Experiencia;
import com.portfoliomillan.nacho.Security.Controller.Mensaje;
import com.portfoliomillan.nacho.Service.ImpExperienciaService;
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
@RequestMapping("/explab")
@CrossOrigin(origins = "http://localhost:4200")
public class ExperienciaController {

    @Autowired
    ImpExperienciaService iExperienciaService;

    @GetMapping("/listado")
    public ResponseEntity<List<Experiencia>> list() {
        List<Experiencia> list = iExperienciaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping ("/crear")
    public ResponseEntity<?> create(@RequestBody dtoExperiencia dtoexp) {
        if (StringUtils.isBlank(dtoexp.getNombreE())) 
            return new ResponseEntity(new Mensaje("El nombre de la Experiencia Laboral es obligatorio"), HttpStatus.BAD_REQUEST);
        if (iExperienciaService.existsByNombreE(dtoexp.getNombreE()))
            return new  ResponseEntity(new Mensaje("Esa experencia ya existe"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = new Experiencia (dtoexp.getNombreE(), dtoexp.getDescripcionE());
        iExperienciaService.save(experiencia);
        
        return new ResponseEntity(new Mensaje("Experiencia agregada"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Experiencia> getById(@PathVariable("id") int id){
        if(!iExperienciaService.existsById(id))
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Experiencia experiencia = iExperienciaService.getOne(id).get();
        return new ResponseEntity(experiencia, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoExperiencia dtoexp){
        //Chequeo de existenca de la experiencia a editar
        if (!iExperienciaService.existsById(id))
            return new ResponseEntity (new Mensaje ("El ID no existe"), HttpStatus.BAD_REQUEST);
        //Compara nombres de experiencias
        if (iExperienciaService.existsByNombreE(dtoexp.getNombreE())&& iExperienciaService.getByNombreE(dtoexp.getNombreE()).get().getId() != id)
            return new ResponseEntity (new Mensaje ("Esa experiencia ya existe"), HttpStatus.BAD_REQUEST);
        // No puede quedar vacio el campo
        if (StringUtils.isBlank(dtoexp.getNombreE()))
            return new ResponseEntity (new Mensaje ("El nombre de la experiencia es obligatorio"), HttpStatus.BAD_REQUEST);
        
        Experiencia experiencia = iExperienciaService.getOne(id).get();
        experiencia.setNombreE(dtoexp.getNombreE());
        experiencia.setDescripcionE((dtoexp.getDescripcionE()));
        
        iExperienciaService.save(experiencia);
        return new ResponseEntity(new Mensaje("Experiencia actualizada con exito"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        //Chequeo de existenca de la experiencia a editar
        if (!iExperienciaService.existsById(id))
            return new ResponseEntity (new Mensaje ("El ID no existe"), HttpStatus.BAD_REQUEST);
        
        iExperienciaService.delete(id);
        return new ResponseEntity(new Mensaje("Experiencia fue eliminada con exito"), HttpStatus.OK);
    }
}
