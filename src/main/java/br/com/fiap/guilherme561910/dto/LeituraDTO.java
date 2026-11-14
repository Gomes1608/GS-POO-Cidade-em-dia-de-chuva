package br.com.fiap.guilherme561910.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class LeituraDTO {

    private Long id;

    @NotNull(message = "Informe a data e a hora da leitura.")
    @PastOrPresent(message = "A data e a hora da leitura não podem estar no futuro.")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dataHora;

    @NotNull(message = "Informe a lâmina de água (cm).")
    @Min(value = 0, message = "A lâmina deve estar entre 0 e 300 cm.")
    @Max(value = 300, message = "A lâmina deve estar entre 0 e 300 cm.")
    private Integer laminaCm;

    private String status;

    @NotNull(message = "Selecione o trecho da leitura.")
    private Long trechoId;

    private String trechoLogradouro;

    private String bairroNome;

    private String trechoDescricao;

    public LeituraDTO() {
    }

    public LeituraDTO(Long id, LocalDateTime dataHora, Integer laminaCm, String status,
                      Long trechoId, String trechoLogradouro, String bairroNome, String trechoDescricao) {
        this.id = id;
        this.dataHora = dataHora;
        this.laminaCm = laminaCm;
        this.status = status;
        this.trechoId = trechoId;
        this.trechoLogradouro = trechoLogradouro;
        this.bairroNome = bairroNome;
        this.trechoDescricao = trechoDescricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Integer getLaminaCm() {
        return laminaCm;
    }

    public void setLaminaCm(Integer laminaCm) {
        this.laminaCm = laminaCm;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTrechoId() {
        return trechoId;
    }

    public void setTrechoId(Long trechoId) {
        this.trechoId = trechoId;
    }

    public String getTrechoLogradouro() {
        return trechoLogradouro;
    }

    public void setTrechoLogradouro(String trechoLogradouro) {
        this.trechoLogradouro = trechoLogradouro;
    }

    public String getBairroNome() {
        return bairroNome;
    }

    public void setBairroNome(String bairroNome) {
        this.bairroNome = bairroNome;
    }

    public String getTrechoDescricao() {
        return trechoDescricao;
    }

    public void setTrechoDescricao(String trechoDescricao) {
        this.trechoDescricao = trechoDescricao;
    }

}
