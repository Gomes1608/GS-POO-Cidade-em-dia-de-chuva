package br.com.fiap.guilherme561910.mapper;

import br.com.fiap.guilherme561910.domain.Bairro;
import br.com.fiap.guilherme561910.dto.BairroDTO;
import org.springframework.stereotype.Component;

@Component
public class BairroMapper {

    public Bairro toEntity(BairroDTO dto) {
        if (dto == null) {
            return null;
        }
        Bairro bairro = new Bairro();
        bairro.setId(dto.getId());
        bairro.setNome(dto.getNome() != null ? dto.getNome().trim() : null);
        bairro.setZona(dto.getZona());
        bairro.setAreaKm2(dto.getAreaKm2());
        return bairro;
    }

    public BairroDTO toDTO(Bairro bairro) {
        if (bairro == null) {
            return null;
        }
        BairroDTO dto = new BairroDTO();
        dto.setId(bairro.getId());
        dto.setNome(bairro.getNome());
        dto.setZona(bairro.getZona());
        dto.setAreaKm2(bairro.getAreaKm2());
        return dto;
    }

}
