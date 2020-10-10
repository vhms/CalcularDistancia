package com.multicert.exercicio.service.distancia;

import com.google.maps.DirectionsApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DirectionsLeg;
import com.google.maps.model.DirectionsResult;
import com.google.maps.model.DirectionsRoute;
import com.multicert.exercicio.domain.distancia.Coordenada;
import com.multicert.exercicio.domain.distancia.DistanciaRequestDTO;
import com.multicert.exercicio.domain.distancia.DistanciaResponseDTO;
import com.multicert.exercicio.domain.veiculo.Veiculo;
import com.multicert.exercicio.service.veiculo.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
public class DistanciaServiceImpl implements DistanciaService {

    @Autowired
    private GeoApiContext context;

    @Autowired
    private VeiculoService veiculoService;

    @Override
    public Optional<DistanciaResponseDTO> getRoute(DistanciaRequestDTO requestDto) throws Exception {
        DistanciaResponseDTO distancaResponse = new DistanciaResponseDTO();
        DirectionsResult directionsResult = DirectionsApi.getDirections(context, requestDto.getOrigem(), requestDto.getDestino()).await();
        Optional<DirectionsLeg> legOptional = getLegFromDirections(directionsResult);
        Veiculo veiculo = veiculoService.findByMatricula(requestDto.getMatricula()).get();

        legOptional.ifPresent(directionsLeg -> {
            distancaResponse.setOrigem(new Coordenada(directionsLeg.startLocation.lat, directionsLeg.startLocation.lng));
            distancaResponse.setDestino(new Coordenada(directionsLeg.endLocation.lat, directionsLeg.endLocation.lng));
            distancaResponse.setDuracao(Duration.ofSeconds(directionsLeg.duration.inSeconds));
            distancaResponse.setCustoCombustivel((veiculo.getConsumo() * directionsLeg.distance.inMeters) / 100000);
        });

        if (distancaResponse.isValid()) {
            return Optional.of(distancaResponse);
        } else {
            return Optional.empty();
        }
    }

    private Optional<DirectionsLeg> getLegFromDirections(DirectionsResult result) {
        if (result.routes.length != 0) {
            DirectionsRoute route = result.routes[0];
            if (route.legs.length != 0) {
                DirectionsLeg leg = route.legs[0];
                return Optional.of(leg);
            }
        }
        return Optional.empty();
    }
}
