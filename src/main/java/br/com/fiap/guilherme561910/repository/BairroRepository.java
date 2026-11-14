package br.com.fiap.guilherme561910.repository;

import br.com.fiap.guilherme561910.domain.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BairroRepository extends JpaRepository<Bairro, Long> {

    List<Bairro> findAllByOrderByNomeAsc();

}
