package br.com.dojo.application.dto;

public class ApoliceDto {

  private Integer seguradoId;
  private String seguradora;
  private String carro;

  public ApoliceDto(Integer seguradoId, String seguradora, String carro) {
    this.seguradoId = seguradoId;
    this.seguradora = seguradora;
    this.carro = carro;
  }

  public Integer getSeguradoId() {
    return seguradoId;
  }

  public String getSeguradora() {
    return seguradora;
  }

  public String getCarro() {
    return carro;
  }
}

