package ru.hse.components;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.hse.models.Algorithm;
import ru.hse.models.DataStructure;
import ru.hse.repositories.AlgorithmRepository;
import ru.hse.repositories.DataStructureRepository;

import java.util.Arrays;
import java.util.List;

@Component
public class DatabaseInitializer {

    private final AlgorithmRepository algorithmRepository;
    private final DataStructureRepository dataStructureRepository;

    public DatabaseInitializer(AlgorithmRepository algorithmRepository, DataStructureRepository dataStructureRepository) {
        this.algorithmRepository = algorithmRepository;
        this.dataStructureRepository = dataStructureRepository;
    }

    @EventListener
    @Transactional
    public void appReady(ApplicationReadyEvent event) {
        if (dataStructureRepository.count() == 0) {
            dataStructureRepository.saveAll(initDataStructures());
        }

        if (algorithmRepository.count() == 0) {
            algorithmRepository.saveAll(initAlgorithms());
        }
    }

    private List<Algorithm> initAlgorithms() {
        DataStructure array = dataStructureRepository.getByName("array").orElseThrow();
        DataStructure linkedList = dataStructureRepository.getByName("linked_list").orElseThrow();
        DataStructure graph = dataStructureRepository.getByName("graph").orElseThrow();

        Algorithm binarySearch = new Algorithm().setName("binary_search").setComplexity(1).setDataStructure(array).setDescription(
                "Бинарный поиск — алгоритм поиска элемента в отсортированном массиве, который работает за O(log n) времени."
        );
        Algorithm quickSort = new Algorithm().setName("quicksort").setComplexity(3).setDataStructure(array).setDescription(
                "Быстрая сортировка — алгоритм сортировки массива, который работает за O(n log n) времени." +
                        " Основан на принципе разделяй и властвуй."
        );
        Algorithm dfs = new Algorithm().setName("dfs").setComplexity(3).setDataStructure(graph).setDescription(
                "DFS (Depth-First Search) — алгоритм обхода графа в глубину, который работает за O(V + E) времени, где V — количество вершин, E — количество рёбер."
        );
        Algorithm bfs = new Algorithm().setName("bfs").setComplexity(3).setDataStructure(graph).setDescription(
                "BFS (Breadth-First Search) — алгоритм обхода графа в ширину, который работает за O(V + E) времени, где V — количество вершин, E — количество рёбер."
        );
        Algorithm shortestPath = new Algorithm().setName("dijkstra").setComplexity(4).setDataStructure(graph).setDescription(
                "Алгоритм Дейкстры — алгоритм нахождения кратчайшего пути в графе от одной вершины до всех остальных, который работает за O(V^2) времени, где V — количество вершин."
        );

        return Arrays.asList(binarySearch, quickSort, dfs, bfs, shortestPath);
    }

    private List<DataStructure> initDataStructures() {
        DataStructure array = new DataStructure().setName("array").setComplexity(1).setDescription(
                "Массив — структура данных, в которой элементы хранятся в памяти последовательно и доступ к ним осуществляется по индексу."
        );
        DataStructure linkedList = new DataStructure().setName("linked_list").setComplexity(1).setDescription(
                "Связный список — структура данных, в которой элементы хранятся в памяти не последовательно, а в произвольных местах, а каждый элемент хранит указатель на следующий элемент."
        );
        DataStructure graph = new DataStructure().setName("graph").setComplexity(3).setDescription(
                "Граф — структура данных, в которой элементы хранятся в виде вершин и рёбер, соединяющих вершины между собой."
        );
        DataStructure binaryTree = new DataStructure().setName("binary_tree").setComplexity(2).setDescription(
                "Бинарное дерево — структура данных, в которой каждый элемент хранит значение и указатели на левого и правого потомка."
        );

        return Arrays.asList(array, linkedList, graph, binaryTree);
    }
}
