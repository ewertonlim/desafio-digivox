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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.digivox.desafio.model.Cliente;
import br.com.digivox.desafio.service.ClienteService;

@RestController
@RequestMapping(value="/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<Cliente> listar() {
		return this.clienteService.listarClientes();
	}
	
	@GetMapping("/{id}")
	public Cliente obterCliente(@PathVariable Long id) {
		return this.clienteService.obterCliente(id);
	}
	
	@PostMapping
	public ResponseEntity<Cliente> criarCliente(@Valid @RequestBody Cliente cliente){
		Cliente clienteCriado = this.clienteService.cadastrarCliente(cliente);
		return ResponseEntity.status(HttpStatus.CREATED).body(clienteCriado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletarConta(@PathVariable Long id) {
		this.clienteService.deletarCliente(id);
		return ResponseEntity.status(HttpStatus.OK).body("cliente deletado!");
	}
		
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizaCliente( @PathVariable Long id,@Valid @RequestBody Cliente cliente) {
		Cliente clienteAtualizado = clienteService.atualizarCliente(id,cliente);
		return ResponseEntity.ok(clienteAtualizado);   
	}
}
