package com.multicert.exercicio.controller.distancia;

import com.multicert.exercicio.domain.distancia.DistanciaRequestDTO;
import com.multicert.exercicio.domain.distancia.DistanciaResponseDTO;
import com.multicert.exercicio.domain.veiculo.Veiculo;
import com.multicert.exercicio.service.distancia.DistanciaService;
import com.multicert.exercicio.service.veiculo.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class DistanciaController {

    @Autowired
    private VeiculoService veiculoService;
    @Autowired
    private DistanciaService distanciaService;

    @GetMapping
    public String distanciaForm(Model model) {
        model.addAttribute("distanciaDto", new DistanciaRequestDTO());
        model.addAttribute("veiculoList", findAllVeiculos());
        return "distancia";
    }

    @PostMapping
    public String distanciaSubmit(@Valid @ModelAttribute DistanciaRequestDTO distanciaRequestDTO, Model model) {
        try {
            Optional<DistanciaResponseDTO> distanciaResponse = distanciaService.getRoute(distanciaRequestDTO);
            if (distanciaResponse.isPresent()) {
                model.addAttribute("responseDto", distanciaResponse.get());
            } else {
                model.addAttribute("noResponse", "Não foi possível calcular a distância");
            }
        } catch (Exception e) {
            model.addAttribute("noResponse", e.getMessage());
        }

        return "result";
    }

    private List<Veiculo> findAllVeiculos() {
        List<Veiculo> veiculos = new ArrayList<>();
        Iterable<Veiculo> veiculoIterator = veiculoService.findAll();
        veiculoIterator.forEach(veiculos::add);
        return veiculos;
    }

}
