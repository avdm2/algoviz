package ru.hse.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.hse.models.Algorithm;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class DataStructureDto {

    private String name;
    private Integer complexity;
    private String description;
    private List<Algorithm> algorithms;
}
