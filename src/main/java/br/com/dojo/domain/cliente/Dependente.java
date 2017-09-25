package br.com.dojo.domain.cliente;

import java.util.Calendar;

public final class Dependente {

    public enum StatusDependentente {
	ATIVO, INATIVO;
    }

    private String apelido;

    private String nomeCompleto;

    private Cpf cpf;

    private Cnh cnh;

    private Calendar dataNascimento;

    private Endereco endereco;

    private StatusDependentente status;

    public Dependente(String nomeCompleto, Cpf cpf, Cnh cnh, Calendar dataNascimento, Endereco endereco,
	    StatusDependentente status) {
	this.nomeCompleto = nomeCompleto;
	this.cpf = cpf;
	this.cnh = cnh;
	this.dataNascimento = dataNascimento;
	this.endereco = endereco;
	this.status = status;
    }

    public String getApelido() {
	return apelido;
    }

    public void informarApelido(String apelido) {
	this.apelido = apelido;
    }

    public String getNomeCompleto() {
	return nomeCompleto;
    }

    public Cpf getCpf() {
	return cpf;
    }

    public Calendar getDataNascimento() {
	return dataNascimento;
    }

    public Endereco getEndereco() {
	return endereco;
    }

    public StatusDependentente getStatus() {
	return status;
    }

    public void ativar() {
	this.status = StatusDependentente.ATIVO;
    }

    public void desativar() {
	this.status = StatusDependentente.INATIVO;
    }

    public boolean isMaiorDeIdade() {
	Calendar hoje = Calendar.getInstance();
	int anoAtual = hoje.get(Calendar.YEAR);

	int anoNascimentoDependente = this.dataNascimento.get(Calendar.YEAR);

	return (anoAtual - anoNascimentoDependente) >= 18;
    }

    public boolean isCnhPermanente() {
	return this.cnh.isPermanente();
    }
    
}