package br.com.dojo.domain.event;

public class ApoliceCriadaEvent {

  private Long numeroApolice;

  public ApoliceCriadaEvent(Long numeroApolice) {
    this.numeroApolice = numeroApolice;
  }

  public Long getNumeroApolice() {
    return numeroApolice;
  }

}