package ru.hse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.models.DataStructure;

import java.util.List;
import java.util.Optional;

public interface DataStructureRepository extends JpaRepository<DataStructure, Integer> {

    Optional<DataStructure> getBySimpleName(String simpleName);
    List<DataStructure> getByComplexity(Integer complexity);
}
