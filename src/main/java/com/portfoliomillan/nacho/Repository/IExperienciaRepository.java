
package com.portfoliomillan.nacho.Repository;

import com.portfoliomillan.nacho.Entity.Experiencia;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExperienciaRepository extends JpaRepository<Experiencia, Integer>{
    public Optional<Experiencia> findByNombreE (String nombreE);
    public boolean existsByNombreE (String nombreE);
}
