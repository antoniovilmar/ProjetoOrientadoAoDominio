package br.com.dojo.infra;

import br.com.dojo.domain.Fatura;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FaturaRepository extends JpaRepository<Fatura, Integer> {

  Fatura findFaturaByApoliceId(Integer idApolice);

}
