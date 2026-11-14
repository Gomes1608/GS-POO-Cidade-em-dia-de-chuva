package br.com.fiap.guilherme561910.mapper;

import br.com.fiap.guilherme561910.domain.Leitura;
import br.com.fiap.guilherme561910.domain.Trecho;
import br.com.fiap.guilherme561910.dto.LeituraDTO;
import org.springframework.stereotype.Component;

@Component
public class LeituraMapper {

    public Leitura toEntity(LeituraDTO dto, Trecho trecho, String status) {
        if (dto == null) {
            return null;
        }
        Leitura leitura = new Leitura();
        leitura.setId(dto.getId());
        leitura.setDataHora(dto.getDataHora());
        leitura.setLaminaCm(dto.getLaminaCm());
        leitura.setStatus(status);
        leitura.setTrecho(trecho);
        return leitura;
    }

    public LeituraDTO toDTO(Leitura leitura) {
        if (leitura == null) {
            return null;
        }
        LeituraDTO dto = new LeituraDTO();
        dto.setId(leitura.getId());
        dto.setDataHora(leitura.getDataHora());
        dto.setLaminaCm(leitura.getLaminaCm());
        dto.setStatus(leitura.getStatus());
        if (leitura.getTrecho() != null) {
            dto.setTrechoId(leitura.getTrecho().getId());
            dto.setTrechoLogradouro(leitura.getTrecho().getLogradouro());
            if (leitura.getTrecho().getBairro() != null) {
                String nomeBairro = leitura.getTrecho().getBairro().getNome();
                dto.setBairroNome(nomeBairro);
                dto.setTrechoDescricao(leitura.getTrecho().getLogradouro() + " (" + nomeBairro + ")");
            } else {
                dto.setTrechoDescricao(leitura.getTrecho().getLogradouro());
            }
        }
        return dto;
    }

}
