package br.com.botWarning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.botWarning.domains.Aviso;

@Repository
public interface AvisoRepository extends JpaRepository<Aviso, Long> {

}
