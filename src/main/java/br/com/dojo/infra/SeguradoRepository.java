package br.com.dojo.infra;

import br.com.dojo.domain.Segurado;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SeguradoRepository extends JpaRepository<Segurado, Integer> {
}
