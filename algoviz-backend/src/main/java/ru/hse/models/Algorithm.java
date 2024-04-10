package ru.hse.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Accessors(chain = true)
@Table(name = "algorithms")
public class Algorithm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "simple_name")
    private String simpleName;
    private String name;
    private Integer complexity;

    private String description;

    @Column(name = "additional_info", length = 5000)
    private String additionalInfo;

    @Column(name = "source_code_java", length = 5000)
    private String sourceCodeJava;

    @Column(name = "source_code_python", length = 5000)
    private String sourceCodePython;

    @Column(name = "source_code_cpp", length = 5000)
    private String sourceCodeCpp;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "data_structure_id", nullable = false)
    @JsonBackReference
    private DataStructure dataStructure;
}
