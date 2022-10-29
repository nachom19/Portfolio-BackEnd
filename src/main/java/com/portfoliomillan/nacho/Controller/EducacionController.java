/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfoliomillan.nacho.Controller;

import com.portfoliomillan.nacho.Dto.dtoEducacion;
import com.portfoliomillan.nacho.Entity.Educacion;
import com.portfoliomillan.nacho.Security.Controller.Mensaje;
import com.portfoliomillan.nacho.Service.ImpEducacionService;
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
@RequestMapping("/edu")
@CrossOrigin (origins = {"https://portfoliomillan-frontend.web.app", "http://localhost:4200"}) 
public class EducacionController {
    @Autowired ImpEducacionService iEducacionService;
    
    @GetMapping("/listado")
    public ResponseEntity<List<Educacion>> list() {
        List<Educacion> list = iEducacionService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping ("/crear")
    public ResponseEntity<?> create(@RequestBody dtoEducacion dtoedu) {
        if (StringUtils.isBlank(dtoedu.getNombreE())) 
            return new ResponseEntity(new Mensaje("El nombre de la Educacion es obligatorio"), HttpStatus.BAD_REQUEST);
        if (iEducacionService.existsByNombreE(dtoedu.getNombreE()))
            return new  ResponseEntity(new Mensaje("Esa educacion ya existe"), HttpStatus.BAD_REQUEST);
        
        Educacion educacion = new Educacion (dtoedu.getNombreE(), dtoedu.getDescripcionE());
        iEducacionService.save(educacion);
        
        return new ResponseEntity(new Mensaje("Educación agregada"), HttpStatus.OK);
    }
    
    @GetMapping("/detail/{id}")
    public ResponseEntity<Educacion> getById(@PathVariable("id") int id){
        if(!iEducacionService.existsById(id)){
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        }
        Educacion educacion = iEducacionService.getOne(id).get();
        return new ResponseEntity(educacion, HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody dtoEducacion dtoedu){
        //Chequeo de existenca de la educacion a editar
        if (!iEducacionService.existsById(id)){
            return new ResponseEntity (new Mensaje ("El ID no existe"), HttpStatus.BAD_REQUEST);
        }
        //Compara nombres de edicacion
        if (iEducacionService.existsByNombreE(dtoedu.getNombreE())&& iEducacionService.getByNombreE(dtoedu.getNombreE()).get().getId() != id){
            return new ResponseEntity (new Mensaje ("Esa educación ya existe"), HttpStatus.BAD_REQUEST);
        }
        // No puede quedar vacio el campo
        if (StringUtils.isBlank(dtoedu.getNombreE())){
            return new ResponseEntity (new Mensaje ("El nombre de la educacion es obligatorio"), HttpStatus.BAD_REQUEST);
        }
        
        Educacion educacion = iEducacionService.getOne(id).get();
        educacion.setNombreE(dtoedu.getNombreE());
        educacion.setDescripcionE((dtoedu.getDescripcionE()));
        
        iEducacionService.save(educacion);
        return new ResponseEntity(new Mensaje("Educación actualizada con exito"), HttpStatus.OK);
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") int id){
        //Chequeo de existenca de la educacion a editar
        if (!iEducacionService.existsById(id))
            return new ResponseEntity (new Mensaje ("El ID no existe"), HttpStatus.NOT_FOUND);
        
        iEducacionService.delete(id);
        return new ResponseEntity(new Mensaje("Educación fue eliminada con exito"), HttpStatus.OK);
    }
}
