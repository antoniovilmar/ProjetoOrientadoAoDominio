package br.com.dojo.domain;

import static java.util.Objects.isNull;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Fatura {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Embedded
  private Dinheiro valorTotal;

  @OneToOne
  private Apolice apolice;

  @Transient
  private final BigDecimal VALOR_BASE = BigDecimal.valueOf(50);

  public Fatura(Apolice apolice) {
    if (isNull(apolice)) {
      throw new IllegalArgumentException("É necessário ter uma apólice para gerar uma fatura");
    }
    this.apolice = apolice;
    calcularValorInicialSeguro();
  }

  protected Fatura(){}

  public BigDecimal getValorTotal() {
    return valorTotal.getValor();
  }

  private void calcularValorInicialSeguro() {
    this.valorTotal = Dinheiro.criar(VALOR_BASE);
    calcularValorSegurado(this.apolice.getSegurado());
    calcularValorDependentes();
  }

  private void calcularValorSegurado(Segurado segurado) {
    this.valorTotal = this.valorTotal.multiplicar(segurado.getIdade());
  }

  public void calcularValorDependentes() {
    this.apolice.getDepententes().forEach((d -> {
      this.valorTotal = this.valorTotal.multiplicar(d.getIdade());
    }));
  }

  private void recalcularValorSeguro(Integer idadeDependente) {
    if (idadeDependente < 26) {
      this.valorTotal.somar(BigDecimal.valueOf(1000));
    }
  }

}
