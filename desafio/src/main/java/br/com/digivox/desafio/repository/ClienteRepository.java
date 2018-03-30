package br.com.digivox.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.digivox.desafio.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
