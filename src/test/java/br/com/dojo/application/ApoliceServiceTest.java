package br.com.dojo.application;

import br.com.dojo.domain.Fatura;
import br.com.dojo.infra.FaturaRepository;
import java.math.BigDecimal;
import org.junit.Assert;
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
  private FaturaRepository faturaRepository;

  @Test
  public void deveCriarFatura() throws Exception {

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
    String carro = "Gol";
    String seguradora = "Azul";

    final ApoliceDto apoliceDto = new ApoliceDto(numeroCnh, cnhPermanente, nomePai, nomeMae, nome,
        endereco, idade, cpf,
        celularPrincipal, telefonePrincipal, celularAlternativo, telefoneAlternativo, carro,
        seguradora);

    apoliceService.efetuarSeguro(apoliceDto);
    Fatura faturaCriada = faturaRepository.findOne(1);

    Assert.assertNotNull(faturaCriada);
    Assert.assertEquals(BigDecimal.valueOf(1350), faturaCriada.getValorTotal());
  }

  @Test
  public void deveAtualizarFatura() throws Exception {
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
    String carro = "Gol";
    String seguradora = "Azul";

    final ApoliceDto apoliceDto = new ApoliceDto(numeroCnh, cnhPermanente, nomePai, nomeMae, nome,
        endereco, idade, cpf,
        celularPrincipal, telefonePrincipal, celularAlternativo, telefoneAlternativo, carro,
        seguradora);

    apoliceService.efetuarSeguro(apoliceDto);

    apoliceService.adicionarDependente(1, new DependenteDto("Antonio", "02315258098", 27));

    Fatura faturaCriada = faturaRepository.findOne(1);

    Assert.assertEquals(BigDecimal.valueOf(36450), faturaCriada.getValorTotal());
  }

}