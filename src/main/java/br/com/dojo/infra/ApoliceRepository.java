package br.com.dojo.infra;

import br.com.dojo.domain.Apolice;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface ApoliceRepository extends JpaRepository<Apolice, Integer> {

  Apolice findByNumero(Long numeroApolice);

}
