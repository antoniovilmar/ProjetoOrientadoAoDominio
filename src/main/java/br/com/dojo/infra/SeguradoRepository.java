package br.com.dojo.infra;

import br.com.dojo.domain.ISeguradoRepository;
import br.com.dojo.domain.Segurado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SeguradoRepository implements ISeguradoRepository {

  private SeguradoRepositorySpringData seguradoRepositorySpringData;

  @Autowired
  public SeguradoRepository(
      SeguradoRepositorySpringData seguradoRepositorySpringData) {
    this.seguradoRepositorySpringData = seguradoRepositorySpringData;
  }

  @Override
  public Segurado buscar(Integer id) {
    return seguradoRepositorySpringData.findOne(id);
  }

  @Override
  public void salvar(Segurado apolice) {
    seguradoRepositorySpringData.save(apolice);
  }

  interface SeguradoRepositorySpringData extends JpaRepository<Segurado, Integer> {

  }

}
