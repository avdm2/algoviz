package ru.hse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.hse.models.Algorithm;

import java.util.List;
import java.util.Optional;

public interface AlgorithmRepository extends JpaRepository<Algorithm, Integer> {

    Optional<Algorithm> getBySimpleName(String simpleName);
    List<Algorithm> getByComplexity(Integer complexity);
}
