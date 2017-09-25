package br.com.dojo.domain.cliente;

public final class Email {

    private StringBuilder mensagemDeErroAoConstruirEmail;

    private String email;

    public Email(String email) {
	this.email = email;
	validar();
    }

    public String getEmail() {
	return email;
    }

    private void validar() {
	if (email == null) {
	    mensagemDeErroAoConstruirEmail.append("E-mail obrigatório.");
	    return;
	}
	if (email.contains("@")) {
	    mensagemDeErroAoConstruirEmail.append("E-mail informado é inválido.");
	}
    }
    
    StringBuilder getMensagemDeErroAoConstruirEmail() {
	return mensagemDeErroAoConstruirEmail;
    }
    
}