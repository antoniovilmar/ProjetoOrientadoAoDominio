package br.com.dojo.domain;

import static java.util.Objects.isNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Segurado implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String nome;

  private String nomePai;

  private String nomeMae;

  @Embedded
  private Cnh cnh;

  @Embedded
  private Cpf cpf;

  private String endereco;

  private Integer idade;

  @OneToMany(cascade = CascadeType.ALL)
  private List<Contato> contatos;

  @Transient
  private StringBuilder mensagemDeErroAoConstruirCliente;

  protected Segurado() {
    this.contatos = new ArrayList<>();
    this.mensagemDeErroAoConstruirCliente = new StringBuilder();
  }

  public static class SeguradoBuilder {

    private Segurado segurado;

    public SeguradoBuilder() {
      this.segurado = new Segurado();
    }

    public SeguradoBuilder comNome(String nome) {
      this.segurado.nome = nome;
      return this;
    }

    public SeguradoBuilder comFiliacao(String nomePai, String nomeMae) {
      this.segurado.nomePai = nomePai;
      this.segurado.nomeMae = nomeMae;
      return this;
    }

    public SeguradoBuilder enderecoEm(String endereco) {
      this.segurado.endereco = endereco;
      return this;
    }

    public SeguradoBuilder portadorDoCpf(Cpf cpf) {
      this.segurado.cpf = cpf;
      return this;
    }

    public SeguradoBuilder comIdade(Integer idade) {
      this.segurado.idade = idade;
      return this;
    }

    public SeguradoBuilder comCnh(Cnh cnh) {
      this.segurado.cnh = cnh;
      return this;
    }

    public SeguradoBuilder comContatos(List<Contato> contatos) {
      this.segurado.adicionarContato(contatos);
      return this;
    }

    public Segurado instance() {
      segurado.validar();
      return segurado;
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

  public String getNome() {
    return nome;
  }

  public String getNomePai() {
    return nomePai;
  }

  public String getNomeMae() {
    return nomeMae;
  }

  public Integer getId() {
    return id;
  }

  public Cnh getCnh() {
    return cnh;
  }

  public Cpf getCpf() {
    return cpf;
  }

  public String getEndereco() {
    return endereco;
  }

  public Integer getIdade() {
    return this.idade;
  }


  public List<Contato> getContatos() {
    return Collections.unmodifiableList(contatos);
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

  private void validarEndereco() {
    if (isNull(this.endereco)) {
      mensagemDeErroAoConstruirCliente.append("Informe o Endereço");
    }
  }

  private void validarContato() {
    if (contatos.size() < 2) {
      mensagemDeErroAoConstruirCliente.append("Informe ao menos dois contatos");
    }
    for (Contato contato : contatos) {
      mensagemDeErroAoConstruirCliente.append(contato.getMensagemDeErroAoConstruirContato());
    }
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