package co.edu.poli.inalpes1.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.edu.poli.inalpes1.model.Inmueble;


public interface InmuebleRepository extends JpaRepository<Inmueble, Long>{
	
	@Query ("FROM Inmueble where tipo = ?1")
	List<Inmueble> findByTipo(String tipo);
	
	@Query ("FROM Inmueble where estado = 'Activo'")
	List<Inmueble> getActivos();
	
}
