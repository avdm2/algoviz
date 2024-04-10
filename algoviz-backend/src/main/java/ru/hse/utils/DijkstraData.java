package ru.hse.utils;

public class DijkstraData {

    public static final String DIJKSTRA_JAVA = """
            class Dijkstra {
                static final int INF = Integer.MAX_VALUE;
            
                static int minDistance(int[] dist, boolean[] sptSet) {
                    int min = INF, minIndex = -1;
            
                    for (int v = 0; v < dist.length; v++) {
                        if (!sptSet[v] && dist[v] <= min) {
                            min = dist[v];
                            minIndex = v;
                        }
                    }
            
                    return minIndex;
                }
            
                static void dijkstra(int[][] graph, int src) {
                    int V = graph.length;
                    int[] dist = new int[V];
                    boolean[] sptSet = new boolean[V];
            
                    for (int i = 0; i < V; i++) {
                        dist[i] = INF;
                    }
            
                    dist[src] = 0;
            
                    for (int count = 0; count < V - 1; count++) {
                        int u = minDistance(dist, sptSet);
                        sptSet[u] = true;
            
                        for (int v = 0; v < V; v++) {
                            if (!sptSet[v] && graph[u][v] != 0 && dist[u] != INF && dist[u] + graph[u][v] < dist[v]) {
                                dist[v] = dist[u] + graph[u][v];
                            }
                        }
                    }
                }
            }
            """;

    public static final String DIJKSTRA_PYTHON = """
            INF = float('inf')
            
            def min_distance(dist, spt_set):
                min_dist = INF
                min_index = -1
            
                for v in range(len(dist)):
                    if not spt_set[v] and dist[v] <= min_dist:
                        min_dist = dist[v]
                        min_index = v
            
                return min_index
            
            def dijkstra(graph, src):
                V = len(graph)
                dist = [INF] * V
                spt_set = [False] * V
            
                dist[src] = 0
            
                for _ in range(V - 1):
                    u = min_distance(dist, spt_set)
                    spt_set[u] = True
            
                    for v in range(V):
                        if not spt_set[v] and graph[u][v] != 0 and dist[u] != INF and dist[u] + graph[u][v] < dist[v]:
                            dist[v] = dist[u] + graph[u][v]
            """;

    public static final String DIJKSTRA_CPP = """
            #include <iostream>
            #include <climits>
            using namespace std;
            
            #define V 9
            #define INF INT_MAX
            
            int minDistance(int dist[], bool sptSet[]) {
                int min = INF, minIndex = -1;
            
                for (int v = 0; v < V; v++) {
                    if (!sptSet[v] && dist[v] <= min) {
                        min = dist[v];
                        minIndex = v;
                    }
                }
            
                return minIndex;
            }
            
            void dijkstra(int graph[V][V], int src) {
                int dist[V];
                bool sptSet[V];
            
                for (int i = 0; i < V; i++) {
                    dist[i] = INF;
                    sptSet[i] = false;
                }
            
                dist[src] = 0;
            
                for (int count = 0; count < V - 1; count++) {
                    int u = minDistance(dist, sptSet);
                    sptSet[u] = true;
            
                    for (int v = 0; v < V; v++) {
                        if (!sptSet[v] && graph[u][v] && dist[u] != INF && dist[u] + graph[u][v] < dist[v]) {
                            dist[v] = dist[u] + graph[u][v];
                        }
                    }
                }
            }
            """;

    public static final String DIJKSTRA_DESCRIPTION = """
            Алгоритм Дейкстры — алгоритм нахождения кратчайшего пути в графе от одной вершины до всех остальных, который работает за O(V^2) времени, где V — количество вершин.
            """;

    public static final String DIJKSTRA_ADDITIONAL_INFO = """
            Алгоритм Дейкстры использует жадный подход и работает только с графами без рёбер отрицательного веса.
            Алгоритм поддерживает массив dist, в котором хранится длина кратчайшего пути от начальной вершины до всех остальных. 
            Также используется массив sptSet, который хранит информацию о том, посещена ли вершина.
            
            Алгоритм начинает с начальной вершины, помечает её как посещённую и обновляет расстояния до всех смежных вершин. 
            Затем выбирается вершина с минимальным расстоянием до начальной вершины и добавляется в множество посещённых вершин. 
            Процесс продолжается до тех пор, пока все вершины не будут посещены.
            
            Алгоритм Дейкстры позволяет найти кратчайшие пути от одной вершины до всех остальных и определить длину кратчайшего пути между двумя вершинами.
            
            Реализация алгоритма Дейкстры в стандартных библиотеках:
            - Java: java.util.PriorityQueue
            - Python: heapq
            - C++: std::priority_queue
            """;
}
