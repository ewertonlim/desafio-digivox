package br.com.digivox.desafio.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digivox.desafio.exception.ClienteInexistenteException;
import br.com.digivox.desafio.model.Cliente;
import br.com.digivox.desafio.repository.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> listarClientes(){
		return this.clienteRepository.findAll();
	}

	public Cliente obterCliente(Long id) {
		Cliente cliente = this.clienteRepository.findOne(id);

		if(cliente == null) {
			throw new ClienteInexistenteException();
		}
		return cliente;
	}

	public Cliente cadastrarCliente(Cliente cliente){
		return this.clienteRepository.save(cliente);
	}

	public void deletarCliente(Long id){
		Cliente cliente = obterCliente(id);
		if(cliente == null) {
			throw new ClienteInexistenteException();
		}
		this.clienteRepository.delete(id);
	}

	public Cliente atualizarCliente(Long id,Cliente cliente) {
		Cliente clienteAtualizar = this.obterCliente(id);
		BeanUtils.copyProperties(cliente, clienteAtualizar);
		clienteAtualizar.setId(id);
		return this.clienteRepository.save(clienteAtualizar);
	}
}
