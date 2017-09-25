package br.com.dojo.domain.cliente;

public final class Cpf {

    private StringBuilder mensagemDeErroAoContruirCpf;

    private String numero;

    public Cpf(String numero) {
	this.numero = numero;
	validar();
    }

    public String getNumero() {
	return numero;
    }
    
    private void validar() {
	if (numero == null) {
	    mensagemDeErroAoContruirCpf.append("CPF obrigatório.");
	    return;
	}
	if (numero.length() != 11) {
	    mensagemDeErroAoContruirCpf.append("CPF informado  é inválido.");
	}
    }

    StringBuilder getMensagemDeErroAoContruirCpf() {
	return mensagemDeErroAoContruirCpf;
    }
}
