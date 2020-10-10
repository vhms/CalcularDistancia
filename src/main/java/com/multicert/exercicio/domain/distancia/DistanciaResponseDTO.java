package com.multicert.exercicio.domain.distancia;

import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsRoute;

import java.text.DecimalFormat;
import java.time.Duration;

public class DistanciaResponseDTO {
    private Coordenada origem;
    private Coordenada destino;
    private Duration duracao;
    private double custoCombustivel;

    public Coordenada getOrigem() {
        return origem;
    }

    public void setOrigem(Coordenada origem) {
        this.origem = origem;
    }

    public Coordenada getDestino() {
        return destino;
    }

    public void setDestino(Coordenada destino) {
        this.destino = destino;
    }

    public Duration getDuracao() {
        return duracao;
    }

    public void setDuracao(Duration duracao) {
        this.duracao = duracao;
    }

    public double getCustoCombustivel() {
        DecimalFormat formatter = new DecimalFormat("#.##");
        custoCombustivel = Double.valueOf(formatter.format(custoCombustivel));
        return custoCombustivel;
    }

    public void setCustoCombustivel(double custoCombustivel) {
        this.custoCombustivel = custoCombustivel;
    }

    public boolean isValid() {
        return origem != null && destino != null && duracao != null && custoCombustivel > 0;
    }
}
