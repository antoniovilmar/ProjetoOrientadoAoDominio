package br.com.dojo.application;

import br.com.dojo.application.dto.ApoliceDto;
import br.com.dojo.application.dto.DependenteDto;
import br.com.dojo.domain.Apolice;
import br.com.dojo.domain.Cpf;
import br.com.dojo.domain.Dependente;
import br.com.dojo.domain.IApoliceRepository;
import br.com.dojo.domain.ISeguradoRepository;
import br.com.dojo.domain.Segurado;
import br.com.dojo.infra.ApoliceRepository;
import br.com.dojo.infra.SeguradoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApoliceService {

  private ISeguradoRepository seguradoRepository;
  private IApoliceRepository apoliceRepository;

  @Autowired
  public ApoliceService(SeguradoRepository seguradoRepository,
      ApoliceRepository apoliceRepository) {
    this.seguradoRepository = seguradoRepository;
    this.apoliceRepository = apoliceRepository;
  }

  public void efetuarSeguro(ApoliceDto apoliceDto) {
    final Segurado segurado = seguradoRepository.buscar(apoliceDto.getSeguradoId());
    final Apolice apolice = new Apolice(segurado, apoliceDto.getSeguradora(),
        apoliceDto.getCarro());

    apoliceRepository.salvar(apolice);
  }

  public void adicionarDependente(Integer idApolice, DependenteDto dependenteDto) {
    final Apolice apolice = apoliceRepository.buscar(idApolice);
    final Dependente dependente = new Dependente(dependenteDto.getNome(),
        Cpf.criar(dependenteDto.getCpf()), dependenteDto.getIdade());
    apolice.incluirDependente(dependente);
    apoliceRepository.salvar(apolice);
  }

}
