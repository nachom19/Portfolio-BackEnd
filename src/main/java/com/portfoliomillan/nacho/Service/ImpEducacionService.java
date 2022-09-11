/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.portfoliomillan.nacho.Service;

import com.portfoliomillan.nacho.Entity.Educacion;
import com.portfoliomillan.nacho.Repository.IEducacionRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ImpEducacionService {
    @Autowired IEducacionRepository ieducacionRepository;
    
    public List<Educacion> list(){
        return ieducacionRepository.findAll();
    }
    
    public Optional<Educacion> getOne(int id){
        return ieducacionRepository.findById(id);
    }
    
    public Optional<Educacion> getByNombreE(String nombreE){
        return ieducacionRepository.findByNombreE(nombreE);
    }
    
    public void save(Educacion educacion){
        ieducacionRepository.save(educacion);
    }
    
    public void delete (int id){
        ieducacionRepository.deleteById(id);
    }
    
    public boolean existsById (int id){
        return ieducacionRepository.existsById(id);
    }
    
    public boolean existsByNombreE (String nombreE){
        return ieducacionRepository.existsByNombreE (nombreE);
    }
}
