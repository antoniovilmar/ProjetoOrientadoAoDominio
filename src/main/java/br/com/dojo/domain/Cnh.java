package br.com.dojo.domain;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
public final class Cnh {

  private String numeroRegistro;

  private boolean permanente;

  @Transient
  private StringBuilder mensagemDeErroAoCriarCnh;

  protected Cnh(){
  }

  private Cnh(String numeroRegistro, boolean permanente) {
    this.numeroRegistro = numeroRegistro;
    this.permanente = permanente;
    validar();
  }

  public static Cnh criar(String numero, boolean permanente) {
    return new Cnh(numero, permanente);
  }

  public String getNumeroRegistro() {
    return numeroRegistro;
  }

  StringBuilder getMensagemDeErroAoCriarCnh() {
    return mensagemDeErroAoCriarCnh;
  }

  private void validar() {
    mensagemDeErroAoCriarCnh = new StringBuilder();
    if (numeroRegistro != null && numeroRegistro.length() > 20) {
      mensagemDeErroAoCriarCnh.append("CNH não pode ter mais que 20 caracteres.");
    }
  }

  public boolean isPermanente() {
    return this.permanente;
  }

}