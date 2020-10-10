package com.multicert.exercicio.domain.distancia;

import javax.validation.constraints.NotNull;

public class DistanciaRequestDTO {

    @NotNull
    private String origem;

    @NotNull
    private String destino;

    @NotNull
    private String matricula;


    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
