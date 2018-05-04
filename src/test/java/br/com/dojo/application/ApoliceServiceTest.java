package br.com.dojo.application;

import br.com.dojo.application.dto.ApoliceDto;
import br.com.dojo.application.dto.DependenteDto;
import br.com.dojo.domain.Cnh;
import br.com.dojo.domain.Contato;
import br.com.dojo.domain.Cpf;
import br.com.dojo.domain.Fatura;
import br.com.dojo.domain.IFaturaRepository;
import br.com.dojo.domain.ISeguradoRepository;
import br.com.dojo.domain.Segurado;
import java.math.BigDecimal;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ApoliceServiceTest {

  @Autowired
  private ApoliceService apoliceService;

  @Autowired
  private IFaturaRepository faturaRepository;

  @Autowired
  private ISeguradoRepository seguradoRepository;

  private Integer seguradoId;

  private final String carro = "Gol";
  private final String seguradora = "Azul";

  @Before
  public void setUp() throws Exception {
    String numeroCnh = "0102-312";
    boolean cnhPermanente = true;
    String nomeMae = "Maria";
    String nomePai = "Osvaldo Oliveira";
    String nome = "Antonio Castro";
    Integer idade = 27;
    String endereco = "Rua Washington Luiz 100, Centro Histório - Porto Alegre/RS";
    String cpf = "41805944126";
    String celularPrincipal = "965765900";
    String telefonePrincipal = "33153287";
    String celularAlternativo = "996578121";
    String telefoneAlternativo = "331832922";

    Segurado segurado = new Segurado.SeguradoBuilder()
        .comCnh(Cnh.criar(numeroCnh, cnhPermanente))
        .comFiliacao(nomePai, nomeMae)
        .comNome(nome)
        .comIdade(idade)
        .enderecoEm(endereco)
        .portadorDoCpf(Cpf.criar(cpf))
        .comContatos(Arrays
            .asList(
                Contato
                    .criar(telefonePrincipal, celularAlternativo),
                Contato
                    .criar(telefoneAlternativo, celularPrincipal)))
        .instance();

    seguradoRepository.salvar(segurado);

    seguradoId = segurado.getId();


  }

  @Test
  public void deveCriarFatura() throws Exception {

    final ApoliceDto apoliceDto = new ApoliceDto(seguradoId, carro,
        seguradora);

    apoliceService.efetuarSeguro(apoliceDto);
    Fatura faturaCriada = faturaRepository.buscar(1);

    Assert.assertEquals(BigDecimal.valueOf(1350), faturaCriada.getValorTotal());
  }

  @Test
  public void deveAtualizarFatura() throws Exception {

    final ApoliceDto apoliceDto = new ApoliceDto(this.seguradoId, carro,
        seguradora);

    apoliceService.efetuarSeguro(apoliceDto);

    apoliceService.adicionarDependente(1, new DependenteDto("Antonio", "02315258098", 27));

    Fatura faturaCriada = faturaRepository.buscar(1);

    Assert.assertEquals(BigDecimal.valueOf(36450), faturaCriada.getValorTotal());
  }

}