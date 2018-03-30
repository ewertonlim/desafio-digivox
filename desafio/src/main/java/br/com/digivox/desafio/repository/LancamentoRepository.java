package br.com.digivox.desafio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.digivox.desafio.model.Cliente;
import br.com.digivox.desafio.model.Lancamento;
import br.com.digivox.desafio.util.GraficoDTO;
import br.com.digivox.desafio.util.TiposLancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
	
	List<Lancamento> findByCliente(Cliente cliente);
	
	@Query(value = "select new br.com.digivox.desafio.util.GraficoDTO(l.cliente.nome,sum(l.valor)) from Lancamento l where l.tipoLancamento = ?1 group by l.cliente.nome")
	List<GraficoDTO> findByTipoLancamentoPorCliente(TiposLancamento tipoLancamento);
	
}
