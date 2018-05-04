package br.com.dojo.domain;

import static java.util.Objects.isNull;

import br.com.dojo.domain.event.ApoliceCriadaEvent;
import br.com.dojo.domain.event.ApoliceDependenteIncluidoEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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
import javax.persistence.Transient;
import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

@Entity
public class Apolice implements Serializable {

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

  @Transient
  private List<Object> eventos;

  @DomainEvents
  Collection<Object> domainEvents() {
    return eventos;
  }

  @AfterDomainEventPublication
  void destroyEvents() {
    eventos.clear();
  }

  protected Apolice() {
    this.dependentes = new ArrayList<>();
    this.eventos = new ArrayList<>();
  }

  public Apolice(Segurado segurado, String seguradora, String carro) {
    this();
    this.numero = new Random().nextLong();
    this.segurado = segurado;
    this.seguradora = seguradora;
    this.carro = carro;
    this.eventos.add(
        new ApoliceCriadaEvent(this.numero));
  }

  public void incluirDependente(Dependente dependente) {
    if (isNull(dependente)) {
      return;
    }

    this.dependentes.add(dependente);
    this.eventos.add(
        new ApoliceDependenteIncluidoEvent(this.numero));
  }

  public Segurado getSegurado() {
    return segurado;
  }

  public List<Dependente> getDepententes() {
    return Collections.unmodifiableList(this.dependentes);
  }

}
