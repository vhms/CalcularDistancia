package com.multicert.exercicio.controller.Veiculo;

import com.multicert.exercicio.domain.veiculo.Veiculo;
import com.multicert.exercicio.service.veiculo.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("veiculo")
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Veiculo>> findAll() {
        return ResponseEntity.ok(veiculoService.findAll());
    }

    @GetMapping("/{matricula}")
    public ResponseEntity<Veiculo> findByMatricula(@PathVariable String matricula) {
        Optional<Veiculo> veiculo = veiculoService.findByMatricula(matricula);
        return veiculo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    @PostMapping
    public ResponseEntity<Veiculo> addNewVeiculo(@RequestBody Veiculo veiculo) {
        Optional<Veiculo> newVeiculo = veiculoService.addNewVeiculo(veiculo);

        return newVeiculo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().body(veiculo));
    }

    @DeleteMapping("/{matricula}")
    public ResponseEntity removeVeiculo(@PathVariable String matricula) {
        Optional<Veiculo> veiculo = veiculoService.removeVeiculo(matricula);

        return veiculo.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }
}
