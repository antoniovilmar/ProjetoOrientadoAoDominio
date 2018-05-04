package br.com.dojo.infra;

import br.com.dojo.domain.Fatura;
import br.com.dojo.domain.IFaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class FaturaRepository implements IFaturaRepository {

  private FaturaRepositorySpringData faturaRepositorySpringData;

  @Autowired
  public FaturaRepository(
      FaturaRepositorySpringData faturaRepositorySpringData) {
    this.faturaRepositorySpringData = faturaRepositorySpringData;
  }

  @Override
  public Fatura buscar(Long numeroApolice) {
    return faturaRepositorySpringData.findFaturaByApoliceNumero(numeroApolice);
  }

  @Override
  public Fatura buscar(Integer id) {
    return faturaRepositorySpringData.findOne(id);
  }

  @Override
  public void salvar(Fatura fatura) {
    faturaRepositorySpringData.save(fatura);

  }

  interface FaturaRepositorySpringData extends JpaRepository<Fatura, Integer> {

    Fatura findFaturaByApoliceNumero(Long numeroApolice);
  }

}
