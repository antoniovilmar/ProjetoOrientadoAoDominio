package br.com.dojo.application.dto;

public class DependenteDto {

  private String nome;
  private String cpf;
  private Integer idade;

  public DependenteDto(String nome, String cpf, Integer idade) {
    this.nome = nome;
    this.cpf = cpf;
    this.idade = idade;
  }

  public String getNome() {
    return nome;
  }

  public String getCpf() {
    return cpf;
  }

  public Integer getIdade() {
    return idade;
  }
}
