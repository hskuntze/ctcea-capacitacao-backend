package br.com.sad2.capacitacao.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sad2.capacitacao.dto.relatorios.TreinamentoOmDTO;
import br.com.sad2.capacitacao.dto.relatorios.TreinamentoStatusDTO;
import br.com.sad2.capacitacao.servicos.RelatorioServico;

@RestController
@RequestMapping(value = "/relatorios")
public class RelatorioControlador {

	@Autowired
	private RelatorioServico relatorioServico;
	
	@GetMapping(value = "/treinamentos/por-status")
	public ResponseEntity<List<TreinamentoStatusDTO>> relatorioQuantidadeTreinamentoPorStatus() {
		return ResponseEntity.ok().body(relatorioServico.relatorioQuantidadeTreinamentoPorStatus());
	}
	
	@GetMapping(value = "/treinamentos/por-om")
	public ResponseEntity<List<TreinamentoOmDTO>> relatorioQuantidadeTreinamentoPorOM() {
		return ResponseEntity.ok().body(relatorioServico.relatorioQuantidadeTreinamentoPorOM());
	}
}
