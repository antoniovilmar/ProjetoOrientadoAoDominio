package br.com.dojo.domain;

import static java.util.Objects.isNull;

import br.com.dojo.domain.event.ApoliceDependenteIncluidoEvent;
import br.com.dojo.domain.event.ApoliceCriadaEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
public class Apolice extends AbstractAggregateRoot implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @OneToOne
  private Segurado segurado;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Dependente> dependentes;

  private String seguradora;

  private String carro;

  private Long numero;

  protected Apolice() {
    this.dependentes = new ArrayList<>();
  }

  public Apolice(Segurado segurado, String seguradora, String carro) {
    this();
    this.numero = new Random().nextLong();
    this.segurado = segurado;
    this.seguradora = seguradora;
    this.carro = carro;
    this.registerEvent(
        new ApoliceCriadaEvent(this.numero));
  }

  public void incluirDependente(Dependente dependente) {
    if (isNull(dependente)) {
      return;
    }

    this.dependentes.add(dependente);
    this.registerEvent(
        new ApoliceDependenteIncluidoEvent(this.numero));
  }

  public Segurado getSegurado() {
    return segurado;
  }

  public List<Dependente> getDepententes() {
    return Collections.unmodifiableList(this.dependentes);
  }

}
