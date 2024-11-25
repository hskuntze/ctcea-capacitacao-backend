package br.com.sad2.capacitacao.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sad2.capacitacao.entities.Capacitado;

@Repository
public interface CapacitadoRepository extends JpaRepository<Capacitado, Long>{
	
	Optional<Capacitado> findByEmail(String email);
}
