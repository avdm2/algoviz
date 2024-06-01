package ru.hse.services;

import org.springframework.stereotype.Service;
import ru.hse.dto.AlgorithmDto;
import ru.hse.dto.DataStructureDto;
import ru.hse.exceptions.AlreadyExistsException;
import ru.hse.exceptions.EmptyRequiredFieldsException;
import ru.hse.exceptions.NotFoundInDBException;
import ru.hse.models.Algorithm;
import ru.hse.models.DataStructure;
import ru.hse.repositories.DataStructureRepository;

import javax.xml.crypto.Data;
import java.util.List;
import java.util.Optional;

@Service
public class DataStructureService {

    private final DataStructureRepository dataStructureRepository;

    public DataStructureService(DataStructureRepository dataStructureRepository) {
        this.dataStructureRepository = dataStructureRepository;
    }

    public DataStructureDto create(DataStructureDto dto) {
        if (dto.getSimpleName() == null || dto.getSimpleName().isEmpty() || dto.getName() == null || dto.getName().isEmpty()) {
            throw new EmptyRequiredFieldsException("simple name and name can not be empty");
        }

        if (dto.getComplexity() != 1 && dto.getComplexity() != 2 && dto.getComplexity() != 3 && dto.getComplexity() != 4 && dto.getComplexity() != 5) {
            throw new IllegalArgumentException("wrong complexity value");
        }

        Optional<DataStructure> optionalDataStructure = dataStructureRepository.getBySimpleName(dto.getSimpleName());
        if (optionalDataStructure.isPresent()) {
            throw new AlreadyExistsException("data structure with this simple name already exists");
        }

        DataStructure dataStructure = new DataStructure()
                .setName(dto.getName())
                .setSimpleName(dto.getSimpleName())
                .setComplexity(dto.getComplexity())
                .setDescription(dto.getDescription())
                .setAdditionalInfo(dto.getAdditionalInfo())
                .setSourceCodeJava(dto.getSourceCodeJava())
                .setSourceCodeCpp(dto.getSourceCodeCpp())
                .setSourceCodePython(dto.getSourceCodePython());
        dataStructureRepository.save(dataStructure);
        return dto;
    }

    public DataStructureDto change(String simpleName, DataStructureDto dto) {
        Optional<DataStructure> optionalDataStructure = dataStructureRepository.getBySimpleName(simpleName);
        if (optionalDataStructure.isEmpty()) {
            throw new NotFoundInDBException("data structure with this simple name is not found");
        }

        String name = dto.getName() == null || dto.getName().isEmpty() ? optionalDataStructure.get().getName() : dto.getName();
        Integer complexity =  dto.getComplexity() == null ? optionalDataStructure.get().getComplexity() : dto.getComplexity();
        String description = dto.getDescription() == null || dto.getDescription().isEmpty() ? optionalDataStructure.get().getDescription() : dto.getDescription();
        String additionalInfo = dto.getAdditionalInfo() == null || dto.getAdditionalInfo().isEmpty() ? optionalDataStructure.get().getAdditionalInfo() : dto.getAdditionalInfo();
        String sourceCodeJava = dto.getSourceCodeJava() == null || dto.getSourceCodeJava().isEmpty() ? optionalDataStructure.get().getSourceCodeJava() : dto.getSourceCodeJava();
        String sourceCodePython = dto.getSourceCodePython() == null || dto.getSourceCodePython().isEmpty() ? optionalDataStructure.get().getSourceCodePython() : dto.getSourceCodePython();
        String sourceCodeCpp = dto.getSourceCodeCpp() == null || dto.getSourceCodeCpp().isEmpty() ? optionalDataStructure.get().getSourceCodeCpp() : dto.getSourceCodeCpp();

        DataStructure dataStructure = optionalDataStructure.get()
                .setName(name)
                .setSimpleName(simpleName)
                .setComplexity(complexity)
                .setDescription(description)
                .setAdditionalInfo(additionalInfo)
                .setSourceCodeJava(sourceCodeJava)
                .setSourceCodePython(sourceCodePython)
                .setSourceCodeCpp(sourceCodeCpp);

        dataStructureRepository.save(dataStructure);
        return dto.setSimpleName(simpleName);
    }

    public void deleteBySimpleName(String name) {
        Optional<DataStructure> optionalDataStructure = dataStructureRepository.getBySimpleName(name);
        if (optionalDataStructure.isEmpty()) {
            throw new NotFoundInDBException("no data structures with name " + name + " was found in db");
        }

        dataStructureRepository.deleteById(optionalDataStructure.get().getId());
    }

    public List<DataStructureDto> getAll() {
        List<DataStructure> dataStructures = dataStructureRepository.findAll();
        if (dataStructures.isEmpty()) {
            throw new NotFoundInDBException("data structures not found");
        }

        return dataStructures.stream()
                .map(dataStructure -> new DataStructureDto()
                        .setName(dataStructure.getName())
                        .setSimpleName(dataStructure.getSimpleName())
                        .setComplexity(dataStructure.getComplexity())
                        .setDescription(dataStructure.getDescription())
                        .setAdditionalInfo(dataStructure.getAdditionalInfo())
                        .setSourceCodeJava(dataStructure.getSourceCodeJava())
                        .setSourceCodePython(dataStructure.getSourceCodePython())
                        .setSourceCodeCpp(dataStructure.getSourceCodeCpp())
                        .setAlgorithms(dataStructure.getAlgorithms()))
                .toList();
    }

    public DataStructureDto getDataStructureBySimpleName(String simpleName) {
        Optional<DataStructure> optionalDataStructure = dataStructureRepository.getBySimpleName(simpleName);
        if (optionalDataStructure.isEmpty()) {
            throw new NotFoundInDBException("data structure with name " + simpleName + " not found");
        }

        DataStructure dataStructure = optionalDataStructure.get();
        return new DataStructureDto()
                .setName(dataStructure.getName())
                .setSimpleName(dataStructure.getSimpleName())
                .setComplexity(dataStructure.getComplexity())
                .setDescription(dataStructure.getDescription())
                .setAdditionalInfo(dataStructure.getAdditionalInfo())
                .setSourceCodeJava(dataStructure.getSourceCodeJava())
                .setSourceCodePython(dataStructure.getSourceCodePython())
                .setSourceCodeCpp(dataStructure.getSourceCodeCpp())
                .setAlgorithms(dataStructure.getAlgorithms());
    }

    public List<DataStructureDto> getDataStructuresByComplexity(Integer complexity) {
        List<DataStructure> dataStructures = dataStructureRepository.getByComplexity(complexity);
        if (dataStructures.isEmpty()) {
            throw new NotFoundInDBException("data structures with complexity " + complexity + " not found");
        }

        return dataStructures.stream()
                .map(dataStructure -> new DataStructureDto()
                        .setName(dataStructure.getName())
                        .setSimpleName(dataStructure.getSimpleName())
                        .setComplexity(dataStructure.getComplexity())
                        .setDescription(dataStructure.getDescription())
                        .setAdditionalInfo(dataStructure.getAdditionalInfo())
                        .setSourceCodeJava(dataStructure.getSourceCodeJava())
                        .setSourceCodePython(dataStructure.getSourceCodePython())
                        .setSourceCodeCpp(dataStructure.getSourceCodeCpp())
                        .setAlgorithms(dataStructure.getAlgorithms()))
                .toList();
    }
}
