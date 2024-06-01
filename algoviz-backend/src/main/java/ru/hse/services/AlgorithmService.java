package ru.hse.services;

import org.springframework.stereotype.Service;
import ru.hse.dto.AlgorithmDto;
import ru.hse.exceptions.AlreadyExistsException;
import ru.hse.exceptions.EmptyRequiredFieldsException;
import ru.hse.exceptions.NotFoundInDBException;
import ru.hse.models.Algorithm;
import ru.hse.models.DataStructure;
import ru.hse.repositories.AlgorithmRepository;
import ru.hse.repositories.DataStructureRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AlgorithmService {

    private final AlgorithmRepository algorithmRepository;
    private final DataStructureRepository dataStructureRepository;

    public AlgorithmService(AlgorithmRepository algorithmRepository, DataStructureRepository dataStructureRepository) {
        this.algorithmRepository = algorithmRepository;
        this.dataStructureRepository = dataStructureRepository;
    }

    public AlgorithmDto create(AlgorithmDto dto) {
        if (dto.getSimpleName() == null || dto.getSimpleName().isEmpty() || dto.getName() == null || dto.getName().isEmpty()) {
            throw new EmptyRequiredFieldsException("simple name and name can not be empty");
        }

        if (dto.getComplexity() != 1 && dto.getComplexity() != 2 && dto.getComplexity() != 3 && dto.getComplexity() != 4 && dto.getComplexity() != 5) {
            throw new IllegalArgumentException("wrong complexity value");
        }

        Optional<Algorithm> optionalAlgorithm = algorithmRepository.getBySimpleName(dto.getSimpleName());
        if (optionalAlgorithm.isPresent()) {
            throw new AlreadyExistsException("algorithm with this simple name already exists");
        }

        Optional<DataStructure> optionalDataStructure = dataStructureRepository.getBySimpleName("mock");
        DataStructure datastructure = optionalDataStructure.orElseGet(
                () -> dataStructureRepository.save(new DataStructure().setName("mock").setSimpleName("mock"))
        );

        Algorithm algorithm = new Algorithm()
                .setName(dto.getName())
                .setSimpleName(dto.getSimpleName())
                .setComplexity(dto.getComplexity())
                .setDescription(dto.getDescription())
                .setAdditionalInfo(dto.getAdditionalInfo())
                .setSourceCodeJava(dto.getSourceCodeJava())
                .setSourceCodeCpp(dto.getSourceCodeCpp())
                .setSourceCodePython(dto.getSourceCodePython())
                .setDataStructure(datastructure);
        algorithmRepository.save(algorithm);
        return dto;
    }

    public AlgorithmDto change(String simpleName, AlgorithmDto dto) {
        Optional<Algorithm> optionalAlgorithm = algorithmRepository.getBySimpleName(simpleName);
        if (optionalAlgorithm.isEmpty()) {
            throw new NotFoundInDBException("algorithm with this simple name is not found");
        }

        String name = dto.getName() == null || dto.getName().isEmpty() ? optionalAlgorithm.get().getName() : dto.getName();
        Integer complexity = dto.getComplexity() == null ? optionalAlgorithm.get().getComplexity() : dto.getComplexity();
        String description = dto.getDescription() == null || dto.getDescription().isEmpty() ? optionalAlgorithm.get().getDescription() : dto.getDescription();
        String additionalInfo = dto.getAdditionalInfo() == null || dto.getAdditionalInfo().isEmpty() ? optionalAlgorithm.get().getAdditionalInfo() : dto.getAdditionalInfo();
        String sourceCodeJava = dto.getSourceCodeJava() == null || dto.getSourceCodeJava().isEmpty() ? optionalAlgorithm.get().getSourceCodeJava() : dto.getSourceCodeJava();
        String sourceCodePython = dto.getSourceCodePython() == null || dto.getSourceCodePython().isEmpty() ? optionalAlgorithm.get().getSourceCodePython() : dto.getSourceCodePython();
        String sourceCodeCpp = dto.getSourceCodeCpp() == null || dto.getSourceCodeCpp().isEmpty() ? optionalAlgorithm.get().getSourceCodeCpp() : dto.getSourceCodeCpp();
        DataStructure dataStructure = optionalAlgorithm.get().getDataStructure();

        Algorithm algorithm = optionalAlgorithm.get()
                .setName(name)
                .setSimpleName(simpleName)
                .setComplexity(complexity)
                .setDescription(description)
                .setAdditionalInfo(additionalInfo)
                .setSourceCodeJava(sourceCodeJava)
                .setSourceCodePython(sourceCodePython)
                .setSourceCodeCpp(sourceCodeCpp)
                .setDataStructure(dataStructure);

        algorithmRepository.save(algorithm);
        return dto.setSimpleName(simpleName);
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

    public void deleteBySimpleName(String name) {
        Optional<Algorithm> optionalAlgorithm = algorithmRepository.getBySimpleName(name);
        if (optionalAlgorithm.isEmpty()) {
            throw new NotFoundInDBException("no algorithms with name " + name + " was found in db");
        }

        algorithmRepository.deleteById(optionalAlgorithm.get().getId());
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
