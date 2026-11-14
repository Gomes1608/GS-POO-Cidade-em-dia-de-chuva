package br.com.fiap.guilherme561910.repository;

import br.com.fiap.guilherme561910.domain.Trecho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrechoRepository extends JpaRepository<Trecho, Long> {

    // P4 = 0: ordenacao por logradouro (A a Z)
    List<Trecho> findAllByOrderByLogradouroAsc();

    List<Trecho> findByBairroIdOrderByLogradouroAsc(Long bairroId);

    boolean existsByBairroId(Long bairroId);

}
