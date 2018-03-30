package br.com.digivox.desafio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.digivox.desafio.exception.LancamentoInexistenteException;
import br.com.digivox.desafio.model.Lancamento;
import br.com.digivox.desafio.repository.LancamentoRepository;
import br.com.digivox.desafio.util.GraficoDTO;
import br.com.digivox.desafio.util.TiposLancamento;

@Service
public class LancamentoService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private ClienteService clienteService;

	public List<Lancamento> listarLancamentos(){
		return this.lancamentoRepository.findAll();
	}

	public Lancamento obterLancamento(Long id) {
		Lancamento lancamento = this.lancamentoRepository.findOne(id);

		if(lancamento == null) {
			throw new LancamentoInexistenteException();
		}
		return lancamento;
	}

	public Lancamento cadastrarLancamento(Lancamento lancamento){
		return this.lancamentoRepository.save(lancamento);
	}

	public void deletarLancamento(Long id){
		Lancamento lancamento = obterLancamento(id);
		if(lancamento == null) {
			throw new LancamentoInexistenteException();
		}
		this.lancamentoRepository.delete(id);
	}

//	public Cliente atualizarLancamento(Long id,Cliente cliente) {
//		Cliente clienteAtualizar = this.obterCliente(id);
//		BeanUtils.copyProperties(cliente, clienteAtualizar);
//		clienteAtualizar.setId(id);
//		return this.clienteRepository.save(clienteAtualizar);
//	}
	
	public List<Lancamento> listarLancamentosCliente(Long id){
		return this.lancamentoRepository.findByCliente(this.clienteService.obterCliente(id));
	}
	
	public List<GraficoDTO> sumLancamentosClientesTipoLancamentoGrafico(TiposLancamento tiposLancamento){
	
		List<GraficoDTO> listaGraficoDTO = this.lancamentoRepository.findByTipoLancamentoPorCliente(tiposLancamento);
		return listaGraficoDTO;
	}
}
