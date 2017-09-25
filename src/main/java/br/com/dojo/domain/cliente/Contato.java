package br.com.dojo.domain.cliente;

public final class Contato {

    private Email email;
    
    private String telefone;
    
    private String celular;
    
    private StringBuilder mensagemDeErroAoConstruirContato;
    
    public Contato(Email email, String telefone, String celular) {
	this.email = email;
	this.telefone = telefone;
	this.celular = celular;
	validarContato();
    }

    public Email getEmail() {
        return email;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCelular() {
        return celular;
    }
    
    StringBuilder getMensagemDeErroAoConstruirContato() {
	return mensagemDeErroAoConstruirContato;
    }
    
    private void validarContato() {
	mensagemDeErroAoConstruirContato = new StringBuilder();
	mensagemDeErroAoConstruirContato.append(email.getMensagemDeErroAoConstruirEmail());
    }
    
}
