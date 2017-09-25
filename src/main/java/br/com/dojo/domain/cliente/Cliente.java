package br.com.dojo.domain.cliente;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Cliente {

    private Integer id;

    private String nome;

    private String nomePai;

    private String nomeMae;

    private Cnh cnh;

    private Cpf cpf;

    private Endereco endereco;

    private String naturalidade;

    private Calendar dataNascimento;

    private List<Contato> contatos;

    private List<Dependente> depententes;

    private StringBuilder mensagemDeErroAoConstruirCliente;

    protected Cliente() {
	// dominio complexo para construir, sua construção será com um Builder
	// inner class
	this.contatos = new ArrayList<>();
	this.depententes = new ArrayList<>();
	this.mensagemDeErroAoConstruirCliente = new StringBuilder();
    }

    public class ClienteBuilder {

	private Cliente cliente;

	public ClienteBuilder() {
	    this.cliente = new Cliente();
	}

	public ClienteBuilder comId(Integer clienteId) {
	    this.cliente.id = clienteId;
	    return this;
	}

	public ClienteBuilder comNome(String nome) {
	    this.cliente.nome = nome;
	    return this;
	}

	public ClienteBuilder comFiliacao(String nomePai, String nomeMae) {
	    this.cliente.nomePai = nomePai;
	    this.cliente.nomeMae = nomeMae;
	    return this;
	}

	public ClienteBuilder comCnh(Cnh cnh) {
	    this.cliente.cnh = cnh;
	    return this;
	}

	public ClienteBuilder portadorDoCpf(Cpf cpf) {
	    this.cliente.cpf = cpf;
	    return this;
	}

	public ClienteBuilder comResidenciaEm(Endereco endereco) {
	    this.cliente.endereco = endereco;
	    return this;
	}

	public ClienteBuilder naturalDe(String naturalidade) {
	    this.cliente.naturalidade = naturalidade;
	    return this;
	}

	public ClienteBuilder nascidoNoDiaDe(Calendar dataNascimento) {
	    this.cliente.dataNascimento = dataNascimento;
	    return this;
	}

	public ClienteBuilder comContatos(List<Contato> contatos) {
	    this.cliente.adicionarContato(contatos);
	    return this;
	}

	public ClienteBuilder comDependentes(List<Dependente> dependentes) {
	    this.cliente.adicionarDependente(dependentes);
	    return this;
	}

	public Cliente instance() {
	    cliente.validar();
	    return cliente;
	}
    }

    public void adicionarContato(List<Contato> contatos) {
	if (contatos == null) {
	    return;
	}

	for (Contato contato : contatos) {
	    adicionarContato(contato);
	}
    }

    public void adicionarContato(Contato contato) {
	this.contatos.add(contato);
    }

    public void adicionarDependente(List<Dependente> dependentes) {
	if (dependentes == null) {
	    return;
	}

	for (Dependente dependente : dependentes) {
	    adicionarDependente(dependente);
	}

    }

    public void adicionarDependente(Dependente dependente) {
	if (dependente.isMaiorDeIdade() && dependente.isCnhPermanente()) {
	    this.depententes.add(dependente);
	    return;
	}
	throw new AdicionarDependenteException("Dependente deve ser maior de idade e ter CNH permanente");
    }

    public Integer getId() {
	return id;
    }

    public String getNome() {
	return nome;
    }

    public String getNomePai() {
	return nomePai;
    }

    public String getNomeMae() {
	return nomeMae;
    }

    public Cnh getCnh() {
	return cnh;
    }

    public Cpf getCpf() {
	return cpf;
    }

    public Endereco getEndereco() {
	return endereco;
    }

    public String getNaturalidade() {
	return naturalidade;
    }

    public Calendar getDataNascimento() {
	return dataNascimento;
    }

    public List<Contato> getContatos() {
	return Collections.unmodifiableList(contatos);
    }

    public List<Dependente> getDepententes() {
	return Collections.unmodifiableList(depententes);
    }

    protected void validar() {
	validarNome();
	validarEndereco();
	validarContato();
	validarCpf();
	validarCnh();
    }

    private void validarCnh() {
	mensagemDeErroAoConstruirCliente.append(cnh.getMensagemDeErroAoCriarCnh());
    }

    private void validarCpf() {
	mensagemDeErroAoConstruirCliente.append(cpf.getMensagemDeErroAoContruirCpf());	
    }

    private void validarContato() {
	if(contatos.size() < 2) {
	    mensagemDeErroAoConstruirCliente.append("Informe ao menos dois contatos");  
	}
	for(Contato contato : contatos) {
	    mensagemDeErroAoConstruirCliente.append(contato.getMensagemDeErroAoConstruirContato());
	}
    }

    private void validarEndereco() {
	mensagemDeErroAoConstruirCliente.append(endereco.getMensagensDeErroAoContruirEndereco());
    }

    private void validarNome() {
	if (nome == null || nome.isEmpty()) {
	    mensagemDeErroAoConstruirCliente.append("Nome obrigatório.");
	    return;
	}

	if (nome.length() > 180) {
	    mensagemDeErroAoConstruirCliente.append("Nome não pode ter mais que 180 caracteres.");
	}
    }

}