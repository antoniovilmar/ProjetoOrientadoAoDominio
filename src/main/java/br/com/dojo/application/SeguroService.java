package br.com.dojo.application;

import br.com.dojo.application.dto.SeguradoDto;
import br.com.dojo.domain.Cnh;
import br.com.dojo.domain.Contato;
import br.com.dojo.domain.Cpf;
import br.com.dojo.domain.Segurado;
import br.com.dojo.infra.SeguradoRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;

public class SeguroService {

  private SeguradoRepository seguradoRepository;

  @Autowired
  public SeguroService(SeguradoRepository seguradoRepository) {
    this.seguradoRepository = seguradoRepository;
  }

  public void salvar(SeguradoDto seguradoDto) {

    Segurado segurado = new Segurado.SeguradoBuilder()
        .comCnh(Cnh.criar(seguradoDto.getNumeroCnh(), seguradoDto.isCnhPermanente()))
        .comFiliacao(seguradoDto.getNomePai(), seguradoDto.getNomeMae())
        .comNome(seguradoDto.getNome())
        .comIdade(seguradoDto.getIdade())
        .enderecoEm(seguradoDto.getEndereco())
        .portadorDoCpf(Cpf.criar(seguradoDto.getCpf()))
        .comContatos(Arrays
            .asList(
                Contato
                    .criar(seguradoDto.getTelefonePrincipal(), seguradoDto.getCelularPrincipal()),
                Contato
                    .criar(seguradoDto.getTelefoneSecundario(),
                        seguradoDto.getTelefoneSecundario())))
        .instance();
    seguradoRepository.save(segurado);

  }

}
