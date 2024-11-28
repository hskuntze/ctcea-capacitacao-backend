package br.com.sad2.capacitacao.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.sad2.capacitacao.entities.Turma;

@Repository
public interface TurmaRepositorio extends JpaRepository<Turma, Long> {
}