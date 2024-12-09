package br.com.sad2.capacitacao.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sad2.capacitacao.dto.relatorios.TreinamentoOmDTO;
import br.com.sad2.capacitacao.dto.relatorios.TreinamentoStatusDTO;
import br.com.sad2.capacitacao.entities.Treinamento;
import br.com.sad2.capacitacao.entities.TreinamentoCapacitadoFiltro;

@Repository
public interface TreinamentoRepositorio extends JpaRepository<Treinamento, Long>{

	@Query("SELECT new br.com.sad2.capacitacao.entities.TreinamentoCapacitadoFiltro(t, c.nomeCompleto) "
		       + "FROM Treinamento t "
		       + "JOIN t.om o "
		       + "JOIN t.capacitados c "
		       + "LEFT JOIN t.instrutores i "
		       + "LEFT JOIN t.materiaisDidaticos md "
		       + "LEFT JOIN t.logisticaTreinamentos lt "
		       + "LEFT JOIN t.turmas tu "
		       + "WHERE LOWER(t.treinamento) LIKE LOWER(CONCAT('%',:treinamento,'%')) "
		       + "AND LOWER(o.sigla) LIKE LOWER(CONCAT('%',:sigla,'%')) "
		       + "AND LOWER(t.brigada) LIKE LOWER(CONCAT('%',:bda,'%')) "
		       + "AND LOWER(c.nomeCompleto) LIKE LOWER(CONCAT('%',:nomeCompleto,'%')) "
		       + "AND (:status IS NULL OR t.status = :status) "
		       + "GROUP BY t, c.nomeCompleto")
	List<TreinamentoCapacitadoFiltro> filtrarTreinamento(String treinamento, String sigla, String bda, String nomeCompleto, Integer status);
	
	@Query("SELECT new br.com.sad2.capacitacao.dto.relatorios.TreinamentoStatusDTO(CASE "
			+ "WHEN t.status = 1 THEN 'Cancelada' "
			+ "WHEN t.status = 2 THEN 'Realizada' "
			+ "WHEN t.status = 3 THEN 'Adiada' "
			+ "ELSE 'Desconhecido' "
			+ "END, "
			+ "COUNT(t)) "
			+ "FROM Treinamento t "
			+ "GROUP BY t.status")
	List<TreinamentoStatusDTO> relatorioQuantidadeTreinamentoPorStatus();
	
	@Query("SELECT new br.com.sad2.capacitacao.dto.relatorios.TreinamentoOmDTO(t.om.sigla, "
			+ "COUNT(t)) "
			+ "FROM Treinamento t "
			+ "GROUP BY t.om.sigla")
	List<TreinamentoOmDTO> relatorioQuantidadeTreinamentoPorOM();
}