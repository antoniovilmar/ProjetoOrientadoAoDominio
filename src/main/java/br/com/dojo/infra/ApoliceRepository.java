package br.com.dojo.infra;

import br.com.dojo.domain.Apolice;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ApoliceRepository extends JpaRepository<Apolice, Integer> {

}
