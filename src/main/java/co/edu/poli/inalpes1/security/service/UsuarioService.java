package co.edu.poli.inalpes1.security.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.edu.poli.inalpes1.model.Usuario;



import co.edu.poli.inalpes1.repository.UsuarioRepository;

@Service
@Transactional
public class UsuarioService {
	
	@Autowired 
	UsuarioRepository usuarioRep;
	
	public Optional<Usuario> getByCorreo(String correo){
		return usuarioRep.findByCorreo(correo);
	}
	
	public boolean existsByCorreo(String correo) {
		return usuarioRep.existsByCorreo(correo);
	}
	
	public void save(Usuario usuario) {
		usuarioRep.save(usuario);
	}
	
}
