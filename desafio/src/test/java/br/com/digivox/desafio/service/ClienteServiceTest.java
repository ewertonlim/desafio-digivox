package br.com.digivox.desafio.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.digivox.desafio.exception.ClienteInexistenteException;

@RunWith(SpringRunner.class)
@RestClientTest({ ClienteService.class })
@DataJpaTest
public class ClienteServiceTest {

    @Autowired
    private ClienteService clienteService;


    @Test(expected = ClienteInexistenteException.class)
    public void buscarClienteInexistente() {
         clienteService.obterCliente(99L);
    }
    
    //Outros casos de teste
}
