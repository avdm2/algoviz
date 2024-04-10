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

    private String simpleName;
    private String name;
    private Integer complexity;
    private String description;
    private String additionalInfo;
    private String sourceCodeJava;
    private String sourceCodePython;
    private String sourceCodeCpp;
    private List<Algorithm> algorithms;
}
