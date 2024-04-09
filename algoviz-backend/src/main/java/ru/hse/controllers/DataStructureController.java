package ru.hse.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.hse.dto.DataStructureDto;
import ru.hse.services.DataStructureService;

import java.util.List;

@RestController
@RequestMapping("/api/data-structures")
public class DataStructureController {

    private final DataStructureService dataStructureService;

    public DataStructureController(DataStructureService dataStructureService) {
        this.dataStructureService = dataStructureService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<DataStructureDto>> getAll() {
        return ResponseEntity.ok(dataStructureService.getAll());
    }

    @GetMapping("/{simpleName}")
    public ResponseEntity<DataStructureDto> getDataStructureBySimpleName(@PathVariable String simpleName) {
        return ResponseEntity.ok(dataStructureService.getDataStructureBySimpleName(simpleName));
    }

    @GetMapping("/complexity/{complexity}")
    public ResponseEntity<List<DataStructureDto>> getDataStructuresByComplexity(@PathVariable Integer complexity) {
        return ResponseEntity.ok(dataStructureService.getDataStructuresByComplexity(complexity));
    }
}
