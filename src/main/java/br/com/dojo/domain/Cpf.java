package br.com.dojo.domain;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public final class Cpf {

  @Transient
  private StringBuilder mensagemDeErroAoContruirCpf;

  private String numero;

  protected Cpf(){
  }

  private Cpf(String numero) {
    this.numero = numero;
    validar();
  }

  public static Cpf criar(String numero) {
    return new Cpf(numero);
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
