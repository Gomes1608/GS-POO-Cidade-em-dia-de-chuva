package br.com.fiap.guilherme561910.mapper;

import br.com.fiap.guilherme561910.domain.Bairro;
import br.com.fiap.guilherme561910.domain.Trecho;
import br.com.fiap.guilherme561910.dto.TrechoDTO;
import org.springframework.stereotype.Component;

@Component
public class TrechoMapper {

    public Trecho toEntity(TrechoDTO dto, Bairro bairro) {
        if (dto == null) {
            return null;
        }
        Trecho trecho = new Trecho();
        trecho.setId(dto.getId());
        trecho.setLogradouro(dto.getLogradouro() != null ? dto.getLogradouro().trim() : null);
        trecho.setTipoCalcada(dto.getTipoCalcada());
        trecho.setAltitude(dto.getAltitude());
        trecho.setBairro(bairro);
        return trecho;
    }

    public TrechoDTO toDTO(Trecho trecho) {
        if (trecho == null) {
            return null;
        }
        TrechoDTO dto = new TrechoDTO();
        dto.setId(trecho.getId());
        dto.setLogradouro(trecho.getLogradouro());
        dto.setTipoCalcada(trecho.getTipoCalcada());
        dto.setAltitude(trecho.getAltitude());
        if (trecho.getBairro() != null) {
            dto.setBairroId(trecho.getBairro().getId());
            dto.setBairroNome(trecho.getBairro().getNome());
        }
        return dto;
    }

}
