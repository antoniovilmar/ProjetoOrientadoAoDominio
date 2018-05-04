package br.com.dojo.application;

import br.com.dojo.domain.Apolice;
import br.com.dojo.domain.Fatura;
import br.com.dojo.domain.event.ApoliceCriadaEvent;
import br.com.dojo.infra.ApoliceRepository;
import br.com.dojo.infra.FaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApoliceCriadaHandler {

  private FaturaRepository faturaRepository;
  private ApoliceRepository apoliceRepository;

  @Autowired
  public ApoliceCriadaHandler(FaturaRepository faturaRepository,
      ApoliceRepository apoliceRepository) {
    this.faturaRepository = faturaRepository;
    this.apoliceRepository = apoliceRepository;
  }

  @EventListener
  public void criarFatura(ApoliceCriadaEvent apoliceCriadaEvent) {
    final Apolice apolice = apoliceRepository
        .findByNumero(apoliceCriadaEvent.getNumeroApolice());
    final Fatura fatura = new Fatura(apolice);

    faturaRepository.save(fatura);

  }

}
