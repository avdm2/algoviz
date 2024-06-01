package ru.hse.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
        System.out.println("req");
        return ResponseEntity.ok(algorithmService.getAll());
    }

    @PostMapping
    public ResponseEntity<AlgorithmDto> create(@RequestBody AlgorithmDto dto) {
        return ResponseEntity.ok(algorithmService.create(dto));
    }

    @PutMapping("/{simpleName}")
    public ResponseEntity<AlgorithmDto> change(@PathVariable String simpleName, @RequestBody AlgorithmDto dto) {
        return ResponseEntity.ok(algorithmService.change(simpleName, dto));
    }

    @GetMapping("/{simpleName}")
    public ResponseEntity<AlgorithmDto> getAlgorithmBySimpleName(@PathVariable String simpleName) {
        return ResponseEntity.ok(algorithmService.getAlgorithmBySimpleName(simpleName));
    }

    @GetMapping("/complexity/{complexity}")
    public ResponseEntity<List<AlgorithmDto>> getAlgorithmsByComplexity(@PathVariable Integer complexity) {
        return ResponseEntity.ok(algorithmService.getAlgorithmsByComplexity(complexity));
    }

    @DeleteMapping("/{simpleName}")
    public ResponseEntity<String> deleteBySimpleName(@PathVariable String simpleName) {
        algorithmService.deleteBySimpleName(simpleName);
        return ResponseEntity.ok("algorithm with name " + simpleName + " was deleted");
    }
}
