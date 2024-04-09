package ru.hse.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class AlgorithmDto {

    private String simpleName;
    private String name;
    private Integer complexity;
    private String description;
}