package br.com.dojo.application;

public class ApoliceDto {

  private String numeroCnh;
  private boolean cnhPermanente;
  private String nomePai;
  private String nomeMae;
  private String nome;
  private String endereco;
  private Integer idade;
  private String cpf;
  private String celularPrincipal;
  private String telefonePrincipal;
  private String celularSecundario;
  private String telefoneSecundario;
  private String seguradora;
  private String carro;

  public ApoliceDto(String numeroCnh, boolean cnhPermanente, String nomePai, String nomeMae,
      String nome, String endereco, Integer idade, String cpf, String celularPrincipal,
      String telefonePrincipal, String celularSecundario, String telefoneSecundario,
      String seguradora, String carro) {
    this.numeroCnh = numeroCnh;
    this.cnhPermanente = cnhPermanente;
    this.nomePai = nomePai;
    this.nomeMae = nomeMae;
    this.nome = nome;
    this.endereco = endereco;
    this.idade = idade;
    this.cpf = cpf;
    this.celularPrincipal = celularPrincipal;
    this.telefonePrincipal = telefonePrincipal;
    this.celularSecundario = celularSecundario;
    this.telefoneSecundario = telefoneSecundario;
    this.seguradora = seguradora;
    this.carro = carro;
  }

  public String getNumeroCnh() {
    return numeroCnh;
  }

  public boolean isCnhPermanente() {
    return cnhPermanente;
  }

  public String getNomePai() {
    return nomePai;
  }

  public String getNomeMae() {
    return nomeMae;
  }

  public String getNome() {
    return nome;
  }

  public Integer getIdade() {
    return idade;
  }

  public String getCpf() {
    return cpf;
  }

  public String getCelularPrincipal() {
    return celularPrincipal;
  }

  public String getTelefonePrincipal() {
    return telefonePrincipal;
  }

  public String getCelularSecundario() {
    return celularSecundario;
  }

  public String getTelefoneSecundario() {
    return telefoneSecundario;
  }

  public String getEndereco() {
    return endereco;
  }

  public String getSeguradora() {
    return seguradora;
  }

  public String getCarro() {
    return carro;
  }
}
