package br.com.dojo.application;

import br.com.dojo.domain.Fatura;
import br.com.dojo.domain.IFaturaRepository;
import br.com.dojo.domain.event.ApoliceDependenteIncluidoEvent;
import br.com.dojo.infra.FaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApoliceDependenteIncluidoHandler {

  private IFaturaRepository faturaRepository;


  @Autowired
  public ApoliceDependenteIncluidoHandler(FaturaRepository faturaRepository) {
    this.faturaRepository = faturaRepository;
  }

  @EventListener
  public void atualizarFatura(ApoliceDependenteIncluidoEvent apoliceDependenteIncluidoEvent) {
    final Fatura fatura = faturaRepository
        .buscar(apoliceDependenteIncluidoEvent.getNumeroApolice());

    fatura.calcularValorDependentes();

    faturaRepository.salvar(fatura);


  }

}
