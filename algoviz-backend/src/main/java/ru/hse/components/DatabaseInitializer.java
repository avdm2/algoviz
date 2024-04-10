package ru.hse.components;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.hse.models.Algorithm;
import ru.hse.models.DataStructure;
import ru.hse.repositories.AlgorithmRepository;
import ru.hse.repositories.DataStructureRepository;
import ru.hse.utils.ArrayData;
import ru.hse.utils.BfsData;
import ru.hse.utils.BinarySearchData;
import ru.hse.utils.BinaryTreeData;
import ru.hse.utils.DfsData;
import ru.hse.utils.DijkstraData;
import ru.hse.utils.GraphData;
import ru.hse.utils.LinkedListData;
import ru.hse.utils.QuicksortData;

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
        DataStructure array = dataStructureRepository.getBySimpleName("array").orElseThrow();
        DataStructure linkedList = dataStructureRepository.getBySimpleName("linked_list").orElseThrow();
        DataStructure graph = dataStructureRepository.getBySimpleName("graph").orElseThrow();

        Algorithm binarySearch = new Algorithm().setSimpleName("binary_search").setName("Бинарный поиск")
                .setComplexity(1).setDataStructure(array).setDescription(BinarySearchData.BINARY_SEARCH_DESCRIPTION)
                .setAdditionalInfo(BinarySearchData.BINARY_SEARCH_ADDITIONAL_INFO).setSourceCodeJava(BinarySearchData.BINARY_SEARCH_JAVA)
                .setSourceCodePython(BinarySearchData.BINARY_SEARCH_PYTHON).setSourceCodeCpp(BinarySearchData.BINARY_SEARCH_CPP);

        Algorithm quickSort = new Algorithm().setSimpleName("quicksort").setName("Быстрая сортировка")
                .setComplexity(3).setDataStructure(array).setDescription(QuicksortData.QUICKSORT_DESCRIPTION)
                .setAdditionalInfo(QuicksortData.QUICKSORT_ADDITIONAL_INFO).setSourceCodeJava(QuicksortData.QUICKSORT_JAVA)
                .setSourceCodePython(QuicksortData.QUICKSORT_PYTHON).setSourceCodeCpp(QuicksortData.QUICKSORT_CPP);

        Algorithm dfs = new Algorithm().setSimpleName("dfs").setName("Поиск в глубину")
                .setComplexity(3).setDataStructure(graph).setDescription( DfsData.DFS_DESCRIPTION)
                .setAdditionalInfo(DfsData.DFS_ADDITIONAL_INFO).setSourceCodeJava(DfsData.DFS_JAVA)
                .setSourceCodePython(DfsData.DFS_PYTHON).setSourceCodeCpp(DfsData.DFS_CPP);

        Algorithm bfs = new Algorithm().setSimpleName("bfs").setName("Поиск в ширину")
                .setComplexity(3).setDataStructure(graph).setDescription(BfsData.BFS_DESCRIPTION)
                .setAdditionalInfo(BfsData.BFS_ADDITIONAL_INFO).setSourceCodeJava(BfsData.BFS_JAVA)
                .setSourceCodePython(BfsData.BFS_PYTHON).setSourceCodeCpp(BfsData.BFS_CPP);

        Algorithm shortestPath = new Algorithm().setSimpleName("dijkstra").setName("Алгоритм Дейкстры")
                .setComplexity(4).setDataStructure(graph).setDescription(DijkstraData.DIJKSTRA_DESCRIPTION)
                .setAdditionalInfo(DijkstraData.DIJKSTRA_ADDITIONAL_INFO).setSourceCodeJava(DijkstraData.DIJKSTRA_JAVA)
                .setSourceCodePython(DijkstraData.DIJKSTRA_PYTHON).setSourceCodeCpp(DijkstraData.DIJKSTRA_CPP);

        return Arrays.asList(binarySearch, quickSort, dfs, bfs, shortestPath);
    }

    private List<DataStructure> initDataStructures() {
        DataStructure array = new DataStructure().setSimpleName("array").setName("Массив")
                .setComplexity(1).setDescription(ArrayData.ARRAY_DESCRIPTION)
                .setAdditionalInfo(ArrayData.ARRAY_ADDITIONAL_INFO).setSourceCodeJava(ArrayData.ARRAY_INITIALIZATION_JAVA)
                .setSourceCodePython(ArrayData.ARRAY_INITIALIZATION_PYTHON).setSourceCodeCpp(ArrayData.ARRAY_INITIALIZATION_CPP);

        DataStructure linkedList = new DataStructure().setSimpleName("linked_list").setName("Связный список")
                .setComplexity(1).setDescription(LinkedListData.LINKED_LIST_DESCRIPTION)
                .setAdditionalInfo(LinkedListData.LINKED_LIST_ADDITIONAL_INFO).setSourceCodeJava(LinkedListData.CUSTOM_LINKED_LIST_JAVA)
                .setSourceCodePython(LinkedListData.CUSTOM_LINKED_LIST_PYTHON).setSourceCodeCpp(LinkedListData.CUSTOM_LINKED_LIST_CPP);

        DataStructure graph = new DataStructure().setSimpleName("graph").setName("Граф")
                .setComplexity(3).setDescription(GraphData.GRAPH_DESCRIPTION)
                .setAdditionalInfo(GraphData.GRAPH_ADDITIONAL_INFO).setSourceCodeJava(GraphData.GRAPH_JAVA)
                .setSourceCodePython(GraphData.GRAPH_PYTHON).setSourceCodeCpp(GraphData.GRAPH_CPP);

        DataStructure binaryTree = new DataStructure().setSimpleName("binary_tree").setName("Бинарное дерево")
                .setComplexity(2).setDescription(BinaryTreeData.BINARY_TREE_DESCRIPTION)
                .setAdditionalInfo(BinaryTreeData.BINARY_TREE_ADDITIONAL_INFO).setSourceCodeJava(BinaryTreeData.BINARY_TREE_JAVA)
                .setSourceCodePython(BinaryTreeData.BINARY_TREE_PYTHON).setSourceCodeCpp(BinaryTreeData.BINARY_TREE_CPP);

        return Arrays.asList(array, linkedList, graph, binaryTree);
    }
}
