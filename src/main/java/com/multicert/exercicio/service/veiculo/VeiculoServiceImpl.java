package com.multicert.exercicio.service.veiculo;

import com.multicert.exercicio.domain.veiculo.Veiculo;
import com.multicert.exercicio.repository.veiculo.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VeiculoServiceImpl implements VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Override
    public Iterable<Veiculo> findAll() {
        return veiculoRepository.findAll();
    }

    @Override
    public Optional<Veiculo> findByMatricula(String matricula) {
        return veiculoRepository.findById(matricula);
    }

    @Override
    public Optional<Veiculo> addNewVeiculo(Veiculo veiculo) {
        Optional<Veiculo> optional = findByMatricula(veiculo.getMatricula());
        if (optional.isPresent()) {
            return Optional.empty();
        }

        Veiculo newVeiculo = veiculoRepository.save(veiculo);
        return Optional.of(newVeiculo);
    }

    @Override
    public Optional<Veiculo> removeVeiculo(String matricula) {
        Optional<Veiculo> optional = findByMatricula(matricula);
        if (!optional.isPresent()) {
            return Optional.empty();
        }

        veiculoRepository.deleteById(matricula);
        return optional;
    }
}
