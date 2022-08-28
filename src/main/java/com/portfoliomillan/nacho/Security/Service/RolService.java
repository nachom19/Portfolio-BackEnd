
package com.portfoliomillan.nacho.Security.Service;

import com.portfoliomillan.nacho.Security.Entity.Rol;
import com.portfoliomillan.nacho.Security.Enums.RolNombre;
import com.portfoliomillan.nacho.Security.Repository.IRolRepository;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolService {
    @Autowired IRolRepository irolRepository;
    
    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return irolRepository.findByRolNombre(rolNombre);
    }
    
    public void save(Rol rol){
        irolRepository.save(rol);
    }
}
