package co.edu.poli.inalpes1.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import co.edu.poli.inalpes1.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	@Query ("FROM Usuario where correo = ?1 and clave = ?2")
	Usuario login(String correo, String clave);
	
	@Query (value = "Select * From Usuario as us join Inmueble as inm on us.id= inm.usuario_id where inm.id = :id", nativeQuery = true)
	Iterable<Usuario> GetUsuario(@Param("id")Long id);
	
	Optional<Usuario> findByCorreo(String correo);
	
	@Query("FROM Usuario where correo = ?1")
	Iterable<Usuario> usuCorreo(String correo);
	
	
	boolean existsByCorreo(String correo);
}
