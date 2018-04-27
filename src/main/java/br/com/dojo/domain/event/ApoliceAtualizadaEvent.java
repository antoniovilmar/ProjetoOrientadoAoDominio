package br.com.dojo.domain.event;

public class ApoliceAtualizadaEvent {

  private Integer idApolice;

  public ApoliceAtualizadaEvent(Integer idApolice){
    this.idApolice = idApolice;
  }

  public Integer getIdApolice() {
    return idApolice;
  }
}
