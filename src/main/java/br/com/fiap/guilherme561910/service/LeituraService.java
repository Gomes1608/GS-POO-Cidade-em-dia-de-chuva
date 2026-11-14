package br.com.fiap.guilherme561910.service;

import br.com.fiap.guilherme561910.domain.Leitura;
import br.com.fiap.guilherme561910.domain.Trecho;
import br.com.fiap.guilherme561910.dto.LeituraDTO;
import br.com.fiap.guilherme561910.mapper.LeituraMapper;
import br.com.fiap.guilherme561910.repository.LeituraRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LeituraService {

    // Parametro P2 da equipe (rm = 1686032): L = 10 + 2 * (1686032 % 4) = 10 cm
    public static final int LIMIAR_ALERTA = 10;
    public static final int LIMIAR_ATENCAO = LIMIAR_ALERTA / 2;

    private final LeituraRepository leituraRepository;
    private final TrechoService trechoService;
    private final LeituraMapper leituraMapper;

    public LeituraService(LeituraRepository leituraRepository,
                          TrechoService trechoService,
                          LeituraMapper leituraMapper) {
        this.leituraRepository = leituraRepository;
        this.trechoService = trechoService;
        this.leituraMapper = leituraMapper;
    }

    public List<LeituraDTO> listar() {
        return leituraRepository.findAllByOrderByDataHoraDesc()
                .stream()
                .map(leituraMapper::toDTO)
                .toList();
    }

    public LeituraDTO buscarPorId(Long id) {
        Leitura leitura = buscarEntidade(id);
        return leituraMapper.toDTO(leitura);
    }

    public Leitura buscarEntidade(Long id) {
        return leituraRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Leitura não encontrada com o id: " + id));
    }

    /**
     * Regra R1: Calcula o status da leitura com base no limiar L = 10 cm (P2).
     * - ALERTA: laminaCm >= 10
     * - ATENCAO: 5 <= laminaCm < 10
     * - NORMAL: laminaCm < 5
     */
    public String calcularStatus(int laminaCm) {
        if (laminaCm >= LIMIAR_ALERTA) {
            return "ALERTA";
        } else if (laminaCm >= LIMIAR_ATENCAO) {
            return "ATENCAO";
        } else {
            return "NORMAL";
        }
    }

    @Transactional
    public LeituraDTO salvar(LeituraDTO dto) {
        if (dto.getId() != null) {
            buscarEntidade(dto.getId());
        }
        Trecho trecho = trechoService.buscarEntidade(dto.getTrechoId());
        String status = calcularStatus(dto.getLaminaCm());
        Leitura leitura = leituraMapper.toEntity(dto, trecho, status);
        Leitura salva = leituraRepository.save(leitura);
        return leituraMapper.toDTO(salva);
    }

    @Transactional
    public void excluir(Long id) {
        buscarEntidade(id);
        leituraRepository.deleteById(id);
    }

}
