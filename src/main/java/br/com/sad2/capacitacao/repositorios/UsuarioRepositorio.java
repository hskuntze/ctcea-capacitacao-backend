package br.com.sad2.capacitacao.repositorios;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sad2.capacitacao.entities.Usuario;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

	Usuario findByEmail(String email);
	
	@Query("SELECT u FROM Usuario u JOIN u.treinamentos t WHERE t.id = :id")
	Set<Usuario> findUsuarioByTreinamento(Long id);
}
