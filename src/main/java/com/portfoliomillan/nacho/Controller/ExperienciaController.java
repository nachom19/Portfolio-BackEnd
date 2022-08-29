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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/explab")
@CrossOrigin(origins = "http://localhost:4200")
public class ExperienciaController {

    @Autowired
    ImpExperienciaService iexperienciaService;

    @GetMapping("/listado")
    public ResponseEntity<List<Experiencia>> list() {
        List<Experiencia> list = iexperienciaService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }

    public ResponseEntity<?> create(@RequestBody dtoExperiencia dtoexp) {
        if (StringUtils.isBlank(dtoexp.getNombreE())) {
            return new ResponseEntity(new Mensaje("El nombre de la Experiencia Laboral es obligatorio"), HttpStatus.BAD_REQUEST);
     
        }
    }

}
