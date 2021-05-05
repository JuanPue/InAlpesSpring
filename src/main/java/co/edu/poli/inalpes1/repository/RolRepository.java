package co.edu.poli.inalpes1.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.edu.poli.inalpes1.model.Erol;
import co.edu.poli.inalpes1.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{

    Optional<Rol> findByRolNombre(Erol rolNombre);
}
