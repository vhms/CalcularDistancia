package com.multicert.exercicio.service.distancia;

import com.multicert.exercicio.domain.distancia.DistanciaRequestDTO;
import com.multicert.exercicio.domain.distancia.DistanciaResponseDTO;

import java.util.Optional;

public interface DistanciaService {
    Optional<DistanciaResponseDTO> getRoute(DistanciaRequestDTO requestDto) throws Exception;
}
