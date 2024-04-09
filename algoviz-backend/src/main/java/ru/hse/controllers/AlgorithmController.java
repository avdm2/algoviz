package ru.hse.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.dto.AlgorithmDto;
import ru.hse.services.AlgorithmService;

import java.util.List;

@RestController
@RequestMapping("/api/algorithms")
public class AlgorithmController {

    private final AlgorithmService algorithmService;

    public AlgorithmController(AlgorithmService algorithmService) {
        this.algorithmService = algorithmService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<AlgorithmDto>> getAll() {
        return ResponseEntity.ok(algorithmService.getAll());
    }

    @GetMapping("/{simpleName}")
    public ResponseEntity<AlgorithmDto> getAlgorithmBySimpleName(@PathVariable String simpleName) {
        return ResponseEntity.ok(algorithmService.getAlgorithmBySimpleName(simpleName));
    }

    @GetMapping("/complexity/{complexity}")
    public ResponseEntity<List<AlgorithmDto>> getAlgorithmsByComplexity(@PathVariable Integer complexity) {
        return ResponseEntity.ok(algorithmService.getAlgorithmsByComplexity(complexity));
    }
}