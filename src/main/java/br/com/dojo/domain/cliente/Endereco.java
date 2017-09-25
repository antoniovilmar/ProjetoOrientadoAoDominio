package br.com.dojo.domain.cliente;

public final class Endereco {

    private String rua;

    private String numero;

    private String complemento;

    private String bairro;

    private String cep;

    private String cidade;

    private String estado;

    private StringBuilder mensagensDeErroAoContruirEndereco;

    public Endereco(String rua, String numero, String complemento, String bairro, String cep, String cidade, String estado) {
	this.rua = rua;
	this.numero = numero;
	this.complemento = complemento;
	this.bairro = bairro;
	this.cep = cep;
	this.cidade = cidade;
	this.estado = estado;
	validar();
    }

    public String getRua() {
	return rua;
    }

    public String getNumero() {
	return numero;
    }

    public String getComplemento() {
	return complemento;
    }

    public String getBairro() {
	return bairro;
    }

    public String getCep() {
	return cep;
    }

    public String getCidade() {
	return cidade;
    }

    public String getEstado() {
	return estado;
    }

    private void validar() {
	validarRua();
	validarNumero();
	validarBairro();
	validarComplemento();
	validarCep();
	validarCidade();
	validarEstado();
    }

    private void validarEstado() {
	if (estado == null || estado.isEmpty()) {
	    mensagensDeErroAoContruirEndereco.append("Estado obrigatório.");
	}
    }

    private void validarCidade() {
	if (cidade == null || cidade.isEmpty()) {
	    mensagensDeErroAoContruirEndereco.append("Cidade obrigatória.");
	}
    }

    private void validarCep() {
	if (cep == null || cep.isEmpty()) {
	    mensagensDeErroAoContruirEndereco.append("Cep obrigatório.");
	    return;
	}
	
	if (cep.length() > 8) {
	    mensagensDeErroAoContruirEndereco.append("CEP deve ter no máximo 8 caracteres");
	}
    }

    private void validarBairro() {
	if (bairro == null || bairro.isEmpty()) {
	    mensagensDeErroAoContruirEndereco.append("Bairro obrigatório.");
	    return;
	}
	
	if (rua.length() > 60) {
	    mensagensDeErroAoContruirEndereco.append("Bairro deve ter no máximo 60 caracteres");
	}
    }

    private void validarNumero() {
	if (numero != null && numero.length() > 6) {
	    mensagensDeErroAoContruirEndereco.append("Número não pode ter mais que 6 caracteres.");
	}
    }

    private void validarComplemento() {
	if (complemento != null && complemento.length() > 100) {
	    mensagensDeErroAoContruirEndereco.append("Complemento não pode ter mais que 100 caracteres.");
	}
    }
    
    private void validarRua() {
	if (rua == null || rua.isEmpty()) {
	    mensagensDeErroAoContruirEndereco.append("Rua obrigatório.");
	    return;
	}

	if (rua.length() > 120) {
	    mensagensDeErroAoContruirEndereco.append("Rua deve ter no máximo 120 caracteres");
	}
    }

    StringBuilder getMensagensDeErroAoContruirEndereco() {
	return mensagensDeErroAoContruirEndereco;
    }
}
