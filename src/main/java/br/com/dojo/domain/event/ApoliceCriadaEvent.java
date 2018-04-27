package br.com.dojo.domain.event;

import br.com.dojo.domain.Apolice;

public class ApoliceCriadaEvent {

  private Apolice apolice;

  public ApoliceCriadaEvent(Apolice apolice) {
    this.apolice = apolice;
  }

  public Apolice getApolice() {
    return apolice;
  }

}