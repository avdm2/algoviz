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

    @PostMapping
    public ResponseEntity<DataStructureDto> create(@RequestBody DataStructureDto dto) {
        return ResponseEntity.ok(dataStructureService.create(dto));
    }

    @PutMapping("/{simpleName}")
    public ResponseEntity<DataStructureDto> change(@PathVariable String simpleName, @RequestBody DataStructureDto dto) {
        return ResponseEntity.ok(dataStructureService.change(simpleName, dto));
    }

    @GetMapping("/{simpleName}")
    public ResponseEntity<DataStructureDto> getDataStructureBySimpleName(@PathVariable String simpleName) {
        return ResponseEntity.ok(dataStructureService.getDataStructureBySimpleName(simpleName));
    }

    @GetMapping("/complexity/{complexity}")
    public ResponseEntity<List<DataStructureDto>> getDataStructuresByComplexity(@PathVariable Integer complexity) {
        return ResponseEntity.ok(dataStructureService.getDataStructuresByComplexity(complexity));
    }

    @DeleteMapping("/{simpleName}")
    public ResponseEntity<String> deleteBySimpleName(@PathVariable String simpleName) {
        dataStructureService.deleteBySimpleName(simpleName);
        return ResponseEntity.ok("data structure with name " + simpleName + " was deleted");
    }
}
