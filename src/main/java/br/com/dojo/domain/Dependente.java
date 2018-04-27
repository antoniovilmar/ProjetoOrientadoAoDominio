package br.com.dojo.domain;

import static br.com.dojo.domain.Dependente.StatusDependentente.ATIVO;
import static br.com.dojo.domain.Dependente.StatusDependentente.INATIVO;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public final class Dependente {

  public enum StatusDependentente {
    ATIVO, INATIVO;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String nomeCompleto;

  private Cpf cpf;

  private Integer idade;

  @Enumerated(EnumType.STRING)
  private StatusDependentente status;

  @Transient
  private StringBuilder mensagensDeErros;

  protected Dependente() {
    this.mensagensDeErros = new StringBuilder();
  }

  public Dependente(String nomeCompleto, Cpf cpf, Integer idade) {
    super();
    setCpf(cpf);
    setNomeCompleto(nomeCompleto);
    setIdade(idade);
    this.status = ATIVO;
  }

  public Integer getId() {
    return id;
  }

  public String getNomeCompleto() {
    return nomeCompleto;
  }

  public Cpf getCpf() {
    return cpf;
  }

  public Integer getIdade() {
    return idade;
  }

  public StatusDependentente getStatus() {
    return status;
  }

  public void ativar() {
    this.status = ATIVO;
  }

  public void desativar() {
    this.status = INATIVO;
  }

  private void setNomeCompleto(String nomeCompleto) {
    if (isNull(nomeCompleto)) {
      this.mensagensDeErros.append("Informe o nome do dependente");
      return;
    }

    this.nomeCompleto = nomeCompleto;
  }

  private void setCpf(Cpf cpf) {
    if (isNull(cpf)) {
      this.mensagensDeErros.append("Informe o CPF");
      return;
    }
    if (nonNull(cpf.getMensagemDeErroAoContruirCpf())) {
      this.mensagensDeErros.append(cpf.getMensagemDeErroAoContruirCpf());
      return;
    }

    this.cpf = cpf;
  }

  private void setIdade(Integer idade) {
    if (isNull(idade)) {
      this.mensagensDeErros.append("Informe a idade");
      return;
    }
    this.idade = idade;
  }

  private boolean isMaiorDeIdade() {
    return idade >= 18;
  }

}