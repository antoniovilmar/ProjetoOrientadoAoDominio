package br.com.dojo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public final class Contato {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String telefone;

  private String celular;

  @Transient
  private StringBuilder mensagemDeErroAoConstruirContato;

  protected Contato() {
  }

  private Contato(String telefone, String celular) {
    this.telefone = telefone;
    this.celular = celular;
    validarContato();
  }

  public static Contato criar(String telefone, String celular) {
    return new Contato(telefone, celular);
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
  }

}
