package ru.hse.services;

import org.springframework.stereotype.Service;
import ru.hse.dto.AlgorithmDto;
import ru.hse.exceptions.NotFoundInDBException;
import ru.hse.models.Algorithm;
import ru.hse.repositories.AlgorithmRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AlgorithmService {

    private final AlgorithmRepository algorithmRepository;

    public AlgorithmService(AlgorithmRepository algorithmRepository) {
        this.algorithmRepository = algorithmRepository;
    }

    public AlgorithmDto getAlgorithmByName(String name) {
        Optional<Algorithm> optionalAlgorithm = algorithmRepository.getByName(name);
        if (optionalAlgorithm.isEmpty()) {
            throw new NotFoundInDBException("algorithm with name " + name + " not found");
        }

        Algorithm algorithm = optionalAlgorithm.get();
        return new AlgorithmDto()
                .setName(algorithm.getName())
                .setComplexity(algorithm.getComplexity())
                .setDescription(algorithm.getDescription());
    }

    public List<AlgorithmDto> getAlgorithmsByComplexity(Integer complexity) {
        List<Algorithm> algorithms = algorithmRepository.getByComplexity(complexity);
        if (algorithms.isEmpty()) {
            throw new NotFoundInDBException("algorithms with complexity " + complexity + " not found");
        }

        return algorithms.stream()
                .map(algorithm -> new AlgorithmDto()
                        .setName(algorithm.getName())
                        .setComplexity(algorithm.getComplexity())
                        .setDescription(algorithm.getDescription()))
                .toList();
    }
}
