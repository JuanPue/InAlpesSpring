package co.edu.poli.inalpes1.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

import co.edu.poli.inalpes1.repository.InmuebleRepository;
import co.edu.poli.inalpes1.repository.UsuarioRepository;
import co.edu.poli.inalpes1.model.Inmueble;
import co.edu.poli.inalpes1.model.Usuario;


@RestController
@RequestMapping("/inalpes/api")
public class UsuarioController {

	@Autowired
	private InmuebleRepository inmuebleRepository;
	
	@Autowired
	private UsuarioRepository usuariorepository;
	
	@GetMapping("/usuario")
	public List<Usuario> getAllUsuarios() {
		return usuariorepository.findAll();
	}
	
	@GetMapping("/usuario/{id}")
	public Usuario getUsuarioId(@PathVariable Long id) {
		Usuario usuario = usuariorepository.findById(id).get();
		return usuario;
	}
	
	@GetMapping("/usuario2/{id}")
	public Iterable<Usuario> getUsuarioId2(@PathVariable Long id) {
		Iterable<Usuario> usuario = usuariorepository.findByI2d(id);
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
	
	@PutMapping("/usuario")
	public Usuario editarUsuario(@RequestBody Usuario updatedUsuario) {
		Usuario usuario = usuariorepository.findById(updatedUsuario.getId()).get();
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
		usuario.setCiudad(updatedUsuario.getCiudad());	
		usuariorepository.save(usuario);
		return usuario;
	}
	
	@DeleteMapping("/usuario/{id}")
	public Usuario eliminarUsuario(@PathVariable Long id) {
		Usuario usuario = usuariorepository.findById(id).get();
		Set<Inmueble> inmuebles = usuario.getInmueble();
		Set<Inmueble> newInmueble = new HashSet<Inmueble>();
		for(Inmueble inmueble: inmuebles) {
			inmueble.setEstado("Inactivo");
			newInmueble.add(inmueble);
		}
		inmuebleRepository.saveAll(newInmueble);
		usuario.setEstado("Inactivo");
		usuariorepository.save(usuario);
		return usuario;
	}
	
	@GetMapping("usuarioIn/{id}")
	public Iterable<Usuario> usuarioId(@PathVariable Long id) {
		Iterable<Usuario> usuario = usuariorepository.GetUsuario(id);
		return usuario;
	}
	
	@GetMapping("usuarioCo/{correo}")
	public Iterable<Usuario> getByCorreo(@PathVariable String correo)	{
		return usuariorepository.usuCorreo(correo);
	}
	
}
