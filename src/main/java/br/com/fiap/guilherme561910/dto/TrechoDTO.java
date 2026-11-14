package br.com.fiap.guilherme561910.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TrechoDTO {

    private Long id;

    @NotBlank(message = "Informe o logradouro do trecho.")
    @Size(max = 120, message = "O logradouro deve ter no máximo 120 caracteres.")
    private String logradouro;

    @NotBlank(message = "Selecione o tipo de calçada.")
    @Size(max = 20, message = "O tipo de calçada deve ter no máximo 20 caracteres.")
    private String tipoCalcada;

    @NotNull(message = "Informe a altitude do trecho.")
    @Min(value = 0, message = "A altitude deve estar entre 0 e 2000 metros.")
    @Max(value = 2000, message = "A altitude deve estar entre 0 e 2000 metros.")
    private Integer altitude;

    @NotNull(message = "Selecione o bairro do trecho.")
    private Long bairroId;

    private String bairroNome;

    public TrechoDTO() {
    }

    public TrechoDTO(Long id, String logradouro, String tipoCalcada, Integer altitude, Long bairroId, String bairroNome) {
        this.id = id;
        this.logradouro = logradouro;
        this.tipoCalcada = tipoCalcada;
        this.altitude = altitude;
        this.bairroId = bairroId;
        this.bairroNome = bairroNome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getTipoCalcada() {
        return tipoCalcada;
    }

    public void setTipoCalcada(String tipoCalcada) {
        this.tipoCalcada = tipoCalcada;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
    }

    public Long getBairroId() {
        return bairroId;
    }

    public void setBairroId(Long bairroId) {
        this.bairroId = bairroId;
    }

    public String getBairroNome() {
        return bairroNome;
    }

    public void setBairroNome(String bairroNome) {
        this.bairroNome = bairroNome;
    }

}
