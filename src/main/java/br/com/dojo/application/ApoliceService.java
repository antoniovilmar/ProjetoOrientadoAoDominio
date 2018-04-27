package br.com.dojo.application;

import br.com.dojo.domain.Apolice;
import br.com.dojo.domain.Cnh;
import br.com.dojo.domain.Contato;
import br.com.dojo.domain.Cpf;
import br.com.dojo.domain.Dependente;
import br.com.dojo.domain.Segurado;
import br.com.dojo.infra.ApoliceRepository;
import br.com.dojo.infra.SeguradoRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApoliceService {

  private SeguradoRepository seguradoRepository;
  private ApoliceRepository apoliceRepository;

  @Autowired
  public ApoliceService(SeguradoRepository seguradoRepository,
      ApoliceRepository apoliceRepository) {
    this.seguradoRepository = seguradoRepository;
    this.apoliceRepository = apoliceRepository;
  }

  public void efetuarSeguro(ApoliceDto apoliceDto) {
    Segurado segurado = new Segurado.SeguradoBuilder()
        .comCnh(Cnh.criar(apoliceDto.getNumeroCnh(), apoliceDto.isCnhPermanente()))
        .comFiliacao(apoliceDto.getNomePai(), apoliceDto.getNomeMae())
        .comNome(apoliceDto.getNome())
        .comIdade(apoliceDto.getIdade())
        .enderecoEm(apoliceDto.getEndereco())
        .portadorDoCpf(Cpf.criar(apoliceDto.getCpf()))
        .comContatos(Arrays
            .asList(
                Contato.criar(apoliceDto.getTelefonePrincipal(), apoliceDto.getCelularPrincipal()),
                Contato
                    .criar(apoliceDto.getTelefoneSecundario(), apoliceDto.getTelefoneSecundario())))
        .instance();
    seguradoRepository.save(segurado);
    Apolice apolice = new Apolice(segurado, apoliceDto.getSeguradora(), apoliceDto.getCarro());
    apoliceRepository.save(apolice);
  }

  public void adicionarDependente(Integer idApolice, DependenteDto dependenteDto) {
    final Apolice apolice = apoliceRepository.findOne(idApolice);
    final Dependente dependente = new Dependente(dependenteDto.getNome(),
        Cpf.criar(dependenteDto.getCpf()), dependenteDto.getIdade());
    apolice.incluirDependente(dependente);
    apoliceRepository.save(apolice);
  }

}
