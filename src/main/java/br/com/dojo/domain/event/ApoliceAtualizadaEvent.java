package br.com.dojo.domain.event;

public class ApoliceAtualizadaEvent {

  private Long numeroApolice;

  public ApoliceAtualizadaEvent(Long numeroApolice) {
    this.numeroApolice = numeroApolice;
  }

  public Long getNumeroApolice() {
    return numeroApolice;
  }
}
