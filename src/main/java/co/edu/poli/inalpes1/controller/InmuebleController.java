package co.edu.poli.inalpes1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import co.edu.poli.inalpes1.model.Inmueble;
import co.edu.poli.inalpes1.model.Usuario;
import co.edu.poli.inalpes1.repository.InmuebleRepository;
import co.edu.poli.inalpes1.repository.UsuarioRepository;

@RestController
@RequestMapping("/inalpes/api")
public class InmuebleController {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private InmuebleRepository inmuebleRepository;

	@GetMapping("/Inmuebles")
	public List<Inmueble> getAllInmuebles() {
		return inmuebleRepository.findAll();
	}
	
	@GetMapping("/Inmuebles/{id}")
	public Inmueble getInmuebleId(@PathVariable Long id) {
		Inmueble inmueble = inmuebleRepository.findById(id).get();
		return inmueble;
	}
	
	@PostMapping("/Inmuebles/{id}")
	public Inmueble insertInmueble(@RequestBody Inmueble inmueble, @PathVariable Long id) {
			Usuario usuario = usuarioRepository.findById(id).get();
			inmueble.setUsuario(usuario);
			usuario.getInmueble().add(inmueble);
			usuarioRepository.save(usuario);
			inmuebleRepository.save(inmueble);
		return inmueble;
	}
	
	@PutMapping("/Inmuebles")
	public Inmueble editInmueble(@RequestBody Inmueble newInmueble) {
		Inmueble inmueble = inmuebleRepository.findById(newInmueble.getId()).get();
		
		inmueble.setEstado(newInmueble.getEstado());
		inmueble.setTipo(newInmueble.getTipo());
		inmueble.setDireccion(newInmueble.getDireccion());
		inmueble.setNum_habitaciones(newInmueble.getNum_habitaciones());
		inmueble.setNum_banos(newInmueble.getNum_banos());
		inmueble.setArea(newInmueble.getArea());
		inmueble.setPrecio(newInmueble.getPrecio());
		inmueble.setEstrato(newInmueble.getEstrato());
		inmueble.setDescripcion(newInmueble.getDescripcion());
		inmueble.setCertificado(newInmueble.getCertificado());
		inmueble.setRuta(newInmueble.getRuta());
		inmueble.setTipo_inmueble(newInmueble.getTipo_inmueble());
		inmueble.setCiudad(newInmueble.getCiudad());
		inmueble.setBarrio(newInmueble.getBarrio());
		
		inmuebleRepository.save(inmueble);
		return inmueble; 
	}

	@DeleteMapping("/Inmuebles/{id}")
	public Inmueble deleteInmueble(@PathVariable Long id) {
		Inmueble inmueble = inmuebleRepository.findById(id).get();
		inmueble.setEstado("Inactivo");
		inmuebleRepository.save(inmueble);
		return inmueble; 
	}
	@DeleteMapping("/Inmueblesvender/{id}")
	public Inmueble venderInmueble(@PathVariable Long id) {
		Inmueble inmueble = inmuebleRepository.findById(id).get();
		inmueble.setEstado("Vendido");
		inmuebleRepository.save(inmueble);
		return inmueble; 
	}
	
	@GetMapping("/InmueblesTipo/{tipo}")
	public List <Inmueble> findByTipo(@PathVariable String tipo) {
		return inmuebleRepository.findByTipo(tipo);
	}
	
	@GetMapping("/InmueblesA")
	public List <Inmueble> getActivos() {
		return inmuebleRepository.getActivos();
	}
	
	
}
