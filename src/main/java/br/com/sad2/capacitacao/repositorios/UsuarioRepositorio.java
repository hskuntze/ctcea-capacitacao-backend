package br.com.sad2.capacitacao.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sad2.capacitacao.entities.Usuario;


@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long>{

	Usuario findByEmail(String email);
}
