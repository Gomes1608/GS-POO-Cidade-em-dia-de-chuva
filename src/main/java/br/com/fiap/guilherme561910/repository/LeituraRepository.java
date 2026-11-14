package br.com.fiap.guilherme561910.repository;

import br.com.fiap.guilherme561910.domain.Leitura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeituraRepository extends JpaRepository<Leitura, Long> {

    List<Leitura> findAllByOrderByDataHoraDesc();

    Optional<Leitura> findFirstByTrechoIdOrderByDataHoraDesc(Long trechoId);

    boolean existsByTrechoId(Long trechoId);

    // P5 = 0 (bloquear exclusao): nao declara deleteByTrechoId

}
