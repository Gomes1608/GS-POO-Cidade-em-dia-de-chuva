package br.com.fiap.guilherme561910.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name = "GS032_LEITURA")
public class Leitura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = false)
    private Integer laminaCm;

    @Column(nullable = false, length = 10)
    private String status;

    @ManyToOne(optional = false)
    @JoinColumn(name = "trecho_id", nullable = false)
    private Trecho trecho;

    public Leitura() {
    }

    public Leitura(Long id, LocalDateTime dataHora, Integer laminaCm, String status, Trecho trecho) {
        this.id = id;
        this.dataHora = dataHora;
        this.laminaCm = laminaCm;
        this.status = status;
        this.trecho = trecho;
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

    public Trecho getTrecho() {
        return trecho;
    }

    public void setTrecho(Trecho trecho) {
        this.trecho = trecho;
    }

}
