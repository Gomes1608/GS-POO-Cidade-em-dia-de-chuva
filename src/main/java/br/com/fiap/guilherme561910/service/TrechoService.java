package br.com.fiap.guilherme561910.service;

import br.com.fiap.guilherme561910.domain.Bairro;
import br.com.fiap.guilherme561910.domain.Trecho;
import br.com.fiap.guilherme561910.dto.TrechoDTO;
import br.com.fiap.guilherme561910.mapper.TrechoMapper;
import br.com.fiap.guilherme561910.repository.LeituraRepository;
import br.com.fiap.guilherme561910.repository.TrechoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TrechoService {

    private final TrechoRepository trechoRepository;
    private final LeituraRepository leituraRepository;
    private final BairroService bairroService;
    private final TrechoMapper trechoMapper;

    public TrechoService(TrechoRepository trechoRepository,
                         LeituraRepository leituraRepository,
                         BairroService bairroService,
                         TrechoMapper trechoMapper) {
        this.trechoRepository = trechoRepository;
        this.leituraRepository = leituraRepository;
        this.bairroService = bairroService;
        this.trechoMapper = trechoMapper;
    }

    public List<TrechoDTO> listar() {
        // P4 = 0: ordenacao por logradouro (A a Z)
        return trechoRepository.findAllByOrderByLogradouroAsc()
                .stream()
                .map(trechoMapper::toDTO)
                .toList();
    }

    public TrechoDTO buscarPorId(Long id) {
        Trecho trecho = buscarEntidade(id);
        return trechoMapper.toDTO(trecho);
    }

    public Trecho buscarEntidade(Long id) {
        return trechoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Trecho não encontrado com o id: " + id));
    }

    @Transactional
    public TrechoDTO salvar(TrechoDTO dto) {
        if (dto.getId() != null) {
            buscarEntidade(dto.getId());
        }
        Bairro bairro = bairroService.buscarEntidade(dto.getBairroId());
        Trecho trecho = trechoMapper.toEntity(dto, bairro);
        Trecho salvo = trechoRepository.save(trecho);
        return trechoMapper.toDTO(salvo);
    }

    @Transactional
    public void excluir(Long id) {
        buscarEntidade(id);
        // Regra R3 com P5 = 0: bloquear a exclusao de trecho que tem leituras
        if (leituraRepository.existsByTrechoId(id)) {
            throw new IllegalStateException("Não é possível excluir este trecho pois existem leituras registradas para ele.");
        }
        trechoRepository.deleteById(id);
    }

}
