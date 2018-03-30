package br.com.digivox.desafio.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digivox.desafio.model.Cliente;
import br.com.digivox.desafio.model.Lancamento;
import br.com.digivox.desafio.service.ClienteService;
import br.com.digivox.desafio.service.LancamentoService;
import br.com.digivox.desafio.util.GraficoDTO;
import br.com.digivox.desafio.util.TiposLancamento;

@RestController
@RequestMapping(value="/lancamento")
public class LancamentoController {
	
	@Autowired
	private LancamentoService lancamentoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/cliente/{id}")
	public List<Lancamento> listarLancamentosCliente(@PathVariable Long id) {
		return this.lancamentoService.listarLancamentosCliente(id);
	}
	
	@GetMapping("/{tipoLancamento}/grafico")
	public List<GraficoDTO> listarLancamentosDespesaPorClienteGrafico(@PathVariable TiposLancamento tipoLancamento) {
		return this.lancamentoService.sumLancamentosClientesTipoLancamentoGrafico(tipoLancamento);
	}
	
	@PostMapping("/cliente/{id}")
	public ResponseEntity<Lancamento> criarLancamento(@PathVariable Long id,@Valid @RequestBody Lancamento lancamento) {
		
		Lancamento lancamentoCriado = null;
		Cliente cliente =  clienteService.obterCliente(id);
		lancamento.setCliente(cliente);
		lancamentoCriado = this.lancamentoService.cadastrarLancamento(lancamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoCriado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarLancamento(@PathVariable Long id) {
		this.lancamentoService.deletarLancamento(id);
		return ResponseEntity.status(HttpStatus.OK).body("lancamento deletado!");
	}
	
	

}
