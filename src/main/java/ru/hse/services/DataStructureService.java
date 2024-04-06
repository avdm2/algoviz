package ru.hse.services;

import org.springframework.stereotype.Service;
import ru.hse.dto.DataStructureDto;
import ru.hse.exceptions.NotFoundInDBException;
import ru.hse.models.DataStructure;
import ru.hse.repositories.DataStructureRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DataStructureService {

    private final DataStructureRepository dataStructureRepository;

    public DataStructureService(DataStructureRepository dataStructureRepository) {
        this.dataStructureRepository = dataStructureRepository;
    }

    public DataStructureDto getDataStructureByName(String name) {
        Optional<DataStructure> optionalDataStructure = dataStructureRepository.getByName(name);
        if (optionalDataStructure.isEmpty()) {
            throw new NotFoundInDBException("data structure with name " + name + " not found");
        }

        DataStructure dataStructure = optionalDataStructure.get();
        return new DataStructureDto()
                .setName(dataStructure.getName())
                .setComplexity(dataStructure.getComplexity())
                .setDescription(dataStructure.getDescription())
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
                        .setComplexity(dataStructure.getComplexity())
                        .setDescription(dataStructure.getDescription())
                        .setAlgorithms(dataStructure.getAlgorithms()))
                .toList();
    }
}
