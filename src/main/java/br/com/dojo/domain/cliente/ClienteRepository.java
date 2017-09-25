package br.com.dojo.domain.cliente;

import java.util.List;

public interface ClienteRepository {

    void salvar(Cliente cliente);
    
    void atualizar(Cliente cliente);
    
    Cliente porId(Integer id);
    
    List<Cliente> todos();
    
    List<Dependente> todosDependentes();
}
