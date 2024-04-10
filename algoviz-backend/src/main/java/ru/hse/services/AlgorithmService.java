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

    public List<AlgorithmDto> getAll() {
        List<Algorithm> algorithms = algorithmRepository.findAll();
        if (algorithms.isEmpty()) {
            throw new NotFoundInDBException("algorithms not found");
        }

        return algorithms.stream()
                .map(algorithm -> new AlgorithmDto()
                        .setName(algorithm.getName())
                        .setSimpleName(algorithm.getSimpleName())
                        .setComplexity(algorithm.getComplexity())
                        .setDescription(algorithm.getDescription())
                        .setAdditionalInfo(algorithm.getAdditionalInfo())
                        .setSourceCodeJava(algorithm.getSourceCodeJava())
                        .setSourceCodePython(algorithm.getSourceCodePython())
                        .setSourceCodeCpp(algorithm.getSourceCodeCpp()))
                .toList();
    }

    public AlgorithmDto getAlgorithmBySimpleName(String simpleName) {
        Optional<Algorithm> optionalAlgorithm = algorithmRepository.getBySimpleName(simpleName);
        if (optionalAlgorithm.isEmpty()) {
            throw new NotFoundInDBException("algorithm with name " + simpleName + " not found");
        }

        Algorithm algorithm = optionalAlgorithm.get();
        return new AlgorithmDto()
                .setName(algorithm.getName())
                .setSimpleName(algorithm.getSimpleName())
                .setComplexity(algorithm.getComplexity())
                .setDescription(algorithm.getDescription())
                .setAdditionalInfo(algorithm.getAdditionalInfo())
                .setSourceCodeJava(algorithm.getSourceCodeJava())
                .setSourceCodePython(algorithm.getSourceCodePython())
                .setSourceCodeCpp(algorithm.getSourceCodeCpp());
    }

    public List<AlgorithmDto> getAlgorithmsByComplexity(Integer complexity) {
        List<Algorithm> algorithms = algorithmRepository.getByComplexity(complexity);
        if (algorithms.isEmpty()) {
            throw new NotFoundInDBException("algorithms with complexity " + complexity + " not found");
        }

        return algorithms.stream()
                .map(algorithm -> new AlgorithmDto()
                        .setName(algorithm.getName())
                        .setSimpleName(algorithm.getSimpleName())
                        .setComplexity(algorithm.getComplexity())
                        .setDescription(algorithm.getDescription())
                        .setAdditionalInfo(algorithm.getAdditionalInfo())
                        .setSourceCodeJava(algorithm.getSourceCodeJava())
                        .setSourceCodePython(algorithm.getSourceCodePython())
                        .setSourceCodeCpp(algorithm.getSourceCodeCpp()))
                .toList();
    }
}
