package br.com.dojo.domain.cliente;

public class AdicionarDependenteException extends RuntimeException {

    private static final long serialVersionUID = -9160230276463618659L;

    public AdicionarDependenteException(String mensagemErro) {
	super(mensagemErro);
    }
    
}