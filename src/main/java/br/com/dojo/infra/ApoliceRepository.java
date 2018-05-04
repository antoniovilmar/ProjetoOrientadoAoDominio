package br.com.dojo.infra;

import br.com.dojo.domain.Apolice;
import br.com.dojo.domain.IApoliceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ApoliceRepository implements IApoliceRepository {

  private ApoliceRepositorySpringData apoliceRepositorySpringData;

  @Autowired
  public ApoliceRepository(
      ApoliceRepositorySpringData apoliceRepositorySpringData) {
    this.apoliceRepositorySpringData = apoliceRepositorySpringData;
  }

  @Override
  public Apolice buscar(Long numeroApolice) {
    return apoliceRepositorySpringData.findByNumero(numeroApolice);
  }

  @Override
  public Apolice buscar(Integer id) {
    return apoliceRepositorySpringData.findOne(id);
  }

  @Override
  public void salvar(Apolice apolice) {
    apoliceRepositorySpringData.save(apolice);
  }

  interface ApoliceRepositorySpringData extends JpaRepository<Apolice, Integer> {

    Apolice findByNumero(Long numeroApolice);
  }

}
