package br.com.fiap.guilherme561910.service;

import br.com.fiap.guilherme561910.domain.Leitura;
import br.com.fiap.guilherme561910.dto.PainelItemDTO;
import br.com.fiap.guilherme561910.repository.LeituraRepository;
import br.com.fiap.guilherme561910.repository.TrechoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PainelService {

    private final TrechoRepository trechoRepository;
    private final LeituraRepository leituraRepository;
    private final BairroService bairroService;

    public PainelService(TrechoRepository trechoRepository,
                         LeituraRepository leituraRepository,
                         BairroService bairroService) {
        this.trechoRepository = trechoRepository;
        this.leituraRepository = leituraRepository;
        this.bairroService = bairroService;
    }

    public List<PainelItemDTO> montarPainel(Long bairroId) {
        bairroService.buscarEntidade(bairroId);
        return trechoRepository.findByBairroIdOrderByLogradouroAsc(bairroId)
                .stream()
                .map(trecho -> {
                    Optional<Leitura> ultimaLeituraOpt = leituraRepository
                            .findFirstByTrechoIdOrderByDataHoraDesc(trecho.getId());
                    Integer ultimaLamina = ultimaLeituraOpt.map(Leitura::getLaminaCm).orElse(null);
                    String ultimoStatus = ultimaLeituraOpt.map(Leitura::getStatus).orElse(null);
                    return new PainelItemDTO(
                            trecho.getLogradouro(),
                            trecho.getTipoCalcada(),
                            trecho.getAltitude(),
                            ultimaLamina,
                            ultimoStatus
                    );
                })
                .toList();
    }

    public long contarPorStatus(List<PainelItemDTO> itens, String status) {
        return itens.stream()
                .filter(item -> status.equals(item.getUltimoStatus()))
                .count();
    }

}
