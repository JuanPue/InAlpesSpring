package co.edu.poli.inalpes1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.poli.inalpes1.repository.UsuarioRepository;

import co.edu.poli.inalpes1.model.Usuario;


@RestController
@RequestMapping("/inalpes/api")
public class UsuarioController {

	@Autowired
	private UsuarioRepository usuariorepository;

	@GetMapping("/Wellcome")
	public String reWell() {
		return "Prueba";
	}
	
	@GetMapping("/usuario")
	public List<Usuario> getAllUsuarios() {
		return usuariorepository.findAll();
	}
	
	@GetMapping("/usuario/{id}")
	public Usuario getUsuarioId(@PathVariable Long id) {
		Usuario usuario = usuariorepository.findById(id).get();
		return usuario;
	}
	
	@GetMapping("/usuarioLogin/{correo}/{clave}")
	public Usuario getUsuarioLogin(@PathVariable String correo, @PathVariable String clave) {
		Usuario usuario = usuariorepository.login(correo,clave);
		return usuario;
	}
	
	@PostMapping("/usuario")
	public Usuario insertUsuario(@RequestBody Usuario usuario) {
		usuariorepository.save(usuario);
		return usuario;
		
	}
	
	@PutMapping("/usuario/{id}")
	public Usuario editarUsuario(@PathVariable Long id, @RequestBody Usuario updatedUsuario) {
		Usuario usuario = usuariorepository.findById(id).get();
		usuario.setIdentificacion(updatedUsuario.getIdentificacion());
		usuario.setClave(updatedUsuario.getClave());
		usuario.setCorreo(updatedUsuario.getCorreo());
		usuario.setNombre(updatedUsuario.getNombre());
		usuario.setApellido(updatedUsuario.getApellido());
		usuario.setTipo_Usuario(updatedUsuario.getTipo_Usuario());
		usuario.setTelefono(updatedUsuario.getTelefono());
		usuario.setEstado(updatedUsuario.getEstado());
		usuario.setFecha_nacimiento(updatedUsuario.getFecha_nacimiento());
		usuario.setOficina(updatedUsuario.getOficina());
		usuario.setPreferencias(updatedUsuario.getPreferencias());
		usuario.setRecibir_notificaciones(updatedUsuario.getRecibir_notificaciones());
		usuario.setRecibir_novedades(updatedUsuario.getRecibir_novedades());
		usuario.setInmueble(updatedUsuario.getInmueble());
		
		usuariorepository.save(usuario);
		return usuario;
	}
	
	@DeleteMapping("/usuario/{id}")
	public Usuario eliminarUsuario(@PathVariable Long id) {
		Usuario usuario = usuariorepository.findById(id).get();
		usuariorepository.deleteById(id);
		return usuario;
	}
	
	@GetMapping("usuarioIn/{id}")
	public Iterable<Usuario> usuarioId(@PathVariable Long id) {
		Iterable<Usuario> usuario = usuariorepository.GetUsuario(id);
		return usuario;
	}
}
