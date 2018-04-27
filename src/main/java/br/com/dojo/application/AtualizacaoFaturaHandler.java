package br.com.dojo.application;

import br.com.dojo.domain.Fatura;
import br.com.dojo.domain.event.ApoliceAtualizadaEvent;
import br.com.dojo.infra.FaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AtualizacaoFaturaHandler {

  private FaturaRepository faturaRepository;


  @Autowired
  public AtualizacaoFaturaHandler(FaturaRepository faturaRepository) {
    this.faturaRepository = faturaRepository;
  }

  @EventListener
  public void atualizarFatura(ApoliceAtualizadaEvent apoliceAtualizadaEvent) {
    final Fatura fatura = faturaRepository
        .findFaturaByApoliceId(apoliceAtualizadaEvent.getIdApolice());

    fatura.calcularValorDependentes();

    faturaRepository.save(fatura);


  }

}
