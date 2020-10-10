package com.multicert.exercicio.service.veiculo;

import com.multicert.exercicio.domain.veiculo.Veiculo;

import java.util.Optional;

public interface VeiculoService {
    Iterable<Veiculo> findAll();

    Optional<Veiculo> findByMatricula(String matricula);

    Optional<Veiculo> addNewVeiculo(Veiculo veiculo);

    Optional<Veiculo> removeVeiculo(String matricula);
}
