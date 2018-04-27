package br.com.dojo.application;

import br.com.dojo.domain.Fatura;
import br.com.dojo.domain.event.ApoliceCriadaEvent;
import br.com.dojo.infra.ApoliceRepository;
import br.com.dojo.infra.FaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class CriacaoFaturaHandler {

  private FaturaRepository faturaRepository;

  @Autowired
  public CriacaoFaturaHandler(ApoliceRepository apoliceRepository,
      FaturaRepository faturaRepository) {
    this.faturaRepository = faturaRepository;
  }

  @EventListener
  public void criarFatura(ApoliceCriadaEvent apoliceCriadaEvent) {
    final Fatura fatura = new Fatura(apoliceCriadaEvent.getApolice());
    faturaRepository.save(fatura);

  }

}
