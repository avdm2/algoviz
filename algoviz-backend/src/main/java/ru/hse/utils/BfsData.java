package ru.hse.utils;

public class BfsData {

    public static final String BFS_JAVA = """
            import java.util.ArrayList;
            import java.util.LinkedList;
            import java.util.List;
            import java.util.Queue;
            
            public class Bfs {
                public static class Graph {
                    private final int vertices;
                    private final List<Integer>[] adj;
            
                    public Graph(int vertices) {
                        this.vertices = vertices;
                        adj = new ArrayList[vertices];
                        for (int i = 0; i < vertices; i++) {
                            adj[i] = new ArrayList<>();
                        }
                    }
            
                    public void addEdge(int v, int w) {
                        adj[v].add(w);
                    }
            
                    public void bfs(int start) {
                        boolean[] visited = new boolean[vertices];
                        Queue<Integer> queue = new LinkedList<>();
                        queue.add(start);
                        visited[start] = true;
            
                        while (!queue.isEmpty()) {
                            int current = queue.poll();
                            System.out.print(current + " ");
            
                            for (int vertex : adj[current]) {
                                if (!visited[vertex]) {
                                    queue.add(vertex);
                                    visited[vertex] = true;
                                }
                            }
                        }
                    }
                }
            }
            """;

    public static final String BFS_PYTHON = """
            from collections import defaultdict
            from collections import deque
            
            class Graph:
                def __init__(self, vertices):
                    self.vertices = vertices
                    self.adj = defaultdict(list)
            
                def add_edge(self, v, w):
                    self.adj[v].append(w)
            
                def bfs(self, start):
                    visited = [False] * self.vertices
                    queue = deque()
                    queue.append(start)
                    visited[start] = True
            
                    while queue:
                        current = queue.popleft()
                        print(current, end=' ')
            
                        for vertex in self.adj[current]:
                            if not visited[vertex]:
                                queue.append(vertex)
                                visited[vertex] = True
            """;

    public static final String BFS_CPP = """
            #include <iostream>
            #include <vector>
            #include <queue>
            using namespace std;
            
            class Graph {
            private:
                int vertices;
                vector<int> *adj;
            
            public:
                Graph(int vertices) {
                    this->vertices = vertices;
                    adj = new vector<int>[vertices];
                }
            
                void add_edge(int v, int w) {
                    adj[v].push_back(w);
                }
            
                void bfs(int start) {
                    bool *visited = new bool[vertices];
                    for (int i = 0; i < vertices; i++) {
                        visited[i] = false;
                    }
            
                    queue<int> queue;
                    queue.push(start);
                    visited[start] = true;
            
                    while (!queue.empty()) {
                        int current = queue.front();
                        cout << current << " ";
                        queue.pop();
            
                        for (int vertex : adj[current]) {
                            if (!visited[vertex]) {
                                queue.push(vertex);
                                visited[vertex] = true;
                            }
                        }
                    }
                }
            };
            """;

    public static final String BFS_DESCRIPTION = """
            Поиск в ширину (BFS, Breadth-First Search) — алгоритм обхода графа в ширину, который работает за O(V + E) времени, где V — количество вершин, E — количество рёбер.
            """;

    public static final String BFS_ADDITIONAL_INFO = """
            Поиск в ширину — это алгоритм обхода графа, который позволяет найти кратчайший путь от одной вершины до всех остальных.
            Он использует очередь для хранения вершин, которые нужно посетить, и массив visited для отслеживания посещённых вершин.
            
            Поиск в ширину применяется во многих задачах, например:
            - поиск кратчайшего пути в лабиринте;
            - поиск кратчайшего пути в графе метро;
            - поиск кратчайшего пути в графе дорог;
            - поиск кратчайшего пути в графе интернета.
            """;
}
