package com.algaworks.socialbooks.domain;

public class DetalhesErro {

    private String titulo;

    private Long status;

    private Long timestamp;

    private String mensagemDesenvolvedor;

    public String getTitulo() {
        return this.titulo;
    }

    public Long getStatus() {
        return this.status;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public String getMensagemDesenvolvedor() {
        return this.mensagemDesenvolvedor;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public void setMensagemDesenvolvedor(String mensagemDesenvolvedor) {
        this.mensagemDesenvolvedor = mensagemDesenvolvedor;
    }
}
