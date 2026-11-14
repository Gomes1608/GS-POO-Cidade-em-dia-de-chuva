package br.com.fiap.guilherme561910.dto;

public class PainelItemDTO {

    private String logradouro;
    private String tipoCalcada;
    private Integer altitude;
    private Integer ultimaLamina;
    private String ultimoStatus;

    public PainelItemDTO() {
    }

    public PainelItemDTO(String logradouro, String tipoCalcada, Integer altitude, Integer ultimaLamina, String ultimoStatus) {
        this.logradouro = logradouro;
        this.tipoCalcada = tipoCalcada;
        this.altitude = altitude;
        this.ultimaLamina = ultimaLamina;
        this.ultimoStatus = ultimoStatus;
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

    public Integer getUltimaLamina() {
        return ultimaLamina;
    }

    public void setUltimaLamina(Integer ultimaLamina) {
        this.ultimaLamina = ultimaLamina;
    }

    public String getUltimoStatus() {
        return ultimoStatus;
    }

    public void setUltimoStatus(String ultimoStatus) {
        this.ultimoStatus = ultimoStatus;
    }

}
