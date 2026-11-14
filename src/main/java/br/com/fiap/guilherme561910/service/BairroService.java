package br.com.fiap.guilherme561910.service;

import br.com.fiap.guilherme561910.domain.Bairro;
import br.com.fiap.guilherme561910.dto.BairroDTO;
import br.com.fiap.guilherme561910.mapper.BairroMapper;
import br.com.fiap.guilherme561910.repository.BairroRepository;
import br.com.fiap.guilherme561910.repository.TrechoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BairroService {

    private final BairroRepository bairroRepository;
    private final TrechoRepository trechoRepository;
    private final BairroMapper bairroMapper;

    public BairroService(BairroRepository bairroRepository,
                         TrechoRepository trechoRepository,
                         BairroMapper bairroMapper) {
        this.bairroRepository = bairroRepository;
        this.trechoRepository = trechoRepository;
        this.bairroMapper = bairroMapper;
    }

    public List<BairroDTO> listar() {
        return bairroRepository.findAllByOrderByNomeAsc()
                .stream()
                .map(bairroMapper::toDTO)
                .toList();
    }

    public BairroDTO buscarPorId(Long id) {
        Bairro bairro = buscarEntidade(id);
        return bairroMapper.toDTO(bairro);
    }

    public Bairro buscarEntidade(Long id) {
        return bairroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bairro não encontrado com o id: " + id));
    }

    @Transactional
    public BairroDTO salvar(BairroDTO dto) {
        if (dto.getId() != null) {
            buscarEntidade(dto.getId());
        }
        Bairro bairro = bairroMapper.toEntity(dto);
        Bairro salvo = bairroRepository.save(bairro);
        return bairroMapper.toDTO(salvo);
    }

    @Transactional
    public void excluir(Long id) {
        buscarEntidade(id);
        // Regra R2: Um bairro que tem trechos cadastrados nao pode ser excluido
        if (trechoRepository.existsByBairroId(id)) {
            throw new IllegalStateException("Não é possível excluir este bairro pois existem trechos de calçada cadastrados nele.");
        }
        bairroRepository.deleteById(id);
    }

}
