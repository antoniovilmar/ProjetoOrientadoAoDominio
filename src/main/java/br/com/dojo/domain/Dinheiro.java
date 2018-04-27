package br.com.dojo.domain;

import java.math.BigDecimal;
import javax.persistence.Embeddable;

@Embeddable
public final class Dinheiro {

  private BigDecimal valor;

  protected Dinheiro(){
  }

  private Dinheiro(BigDecimal valor) {
    this.valor = valor;
  }

  public static Dinheiro criar(BigDecimal valor) {
    return new Dinheiro(valor);
  }

  public Dinheiro multiplicar(Integer valor) {
    return new Dinheiro(this.valor.multiply(BigDecimal.valueOf(valor)));
  }

  public Dinheiro somar(BigDecimal valor) {
    return new Dinheiro(this.valor.add(valor));
  }

  public BigDecimal getValor() {
    return valor;
  }
}
