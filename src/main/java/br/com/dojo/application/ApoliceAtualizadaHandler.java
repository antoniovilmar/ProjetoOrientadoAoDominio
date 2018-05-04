package br.com.dojo.application;

import br.com.dojo.domain.Fatura;
import br.com.dojo.domain.event.ApoliceAtualizadaEvent;
import br.com.dojo.infra.FaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApoliceAtualizadaHandler {

  private FaturaRepository faturaRepository;


  @Autowired
  public ApoliceAtualizadaHandler(FaturaRepository faturaRepository) {
    this.faturaRepository = faturaRepository;
  }

  @EventListener
  public void atualizarFatura(ApoliceAtualizadaEvent apoliceAtualizadaEvent) {
    final Fatura fatura = faturaRepository
        .findFaturaByApoliceNumero(apoliceAtualizadaEvent.getNumeroApolice());

    fatura.calcularValorDependentes();

    faturaRepository.save(fatura);


  }

}
