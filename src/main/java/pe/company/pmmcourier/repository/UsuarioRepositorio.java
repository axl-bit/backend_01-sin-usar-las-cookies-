package pe.company.pmmcourier.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.company.pmmcourier.model.Colaborador;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Colaborador, Integer> {
	
	Optional<Colaborador> findByUsuario(String usuario);;

}
