package br.com.dojo.domain.event;

public class ApoliceDependenteIncluidoEvent {

  private Long numeroApolice;

  public ApoliceDependenteIncluidoEvent(Long numeroApolice) {
    this.numeroApolice = numeroApolice;
  }

  public Long getNumeroApolice() {
    return numeroApolice;
  }
}
