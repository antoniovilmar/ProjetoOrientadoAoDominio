package br.com.dojo.domain.cliente;

public final class Cnh {

    private String numero;

    private boolean permanente;

    private StringBuilder mensagemDeErroAoCriarCnh;

    public Cnh(String numero, boolean permanente) {
	this.numero = numero;
	this.permanente = permanente;
	validar();
    }

    public String getNumero() {
	return numero;
    }

    StringBuilder getMensagemDeErroAoCriarCnh() {
	return mensagemDeErroAoCriarCnh;
    }

    private void validar() {
	mensagemDeErroAoCriarCnh = new StringBuilder();
	if (numero != null && numero.length() > 20) {
	    mensagemDeErroAoCriarCnh.append("CNH não pode ter mais que 20 caracteres.");
	}
    }

    public boolean isPermanente() {
	return this.permanente;
    }

}