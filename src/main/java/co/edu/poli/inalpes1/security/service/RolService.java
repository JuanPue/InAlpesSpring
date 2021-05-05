package co.edu.poli.inalpes1.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.poli.inalpes1.model.Erol;
import co.edu.poli.inalpes1.model.Rol;
import co.edu.poli.inalpes1.repository.RolRepository;


@Service
@Transactional
public class RolService {
	
	@Autowired
	RolRepository rolRep;
	
	public Optional<Rol> getByRolNombre(Erol rolNombre){
		return rolRep.findByRolNombre(rolNombre);
		
	}

}
