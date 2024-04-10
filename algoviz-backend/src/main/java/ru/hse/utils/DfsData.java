package ru.hse.utils;

public class DfsData {

    public static final String DFS_JAVA = """
            import java.util.ArrayList;
            import java.util.List;
            import java.util.Stack;
            
            public class Dfs {
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
            
                    public void dfs(int start) {
                        boolean[] visited = new boolean[vertices];
                        Stack<Integer> stack = new Stack<>();
                        stack.push(start);
            
                        while (!stack.isEmpty()) {
                            int current = stack.pop();
                            if (!visited[current]) {
                                visited[current] = true;
                                System.out.print(current + " ");
            
                                for (int vertex : adj[current]) {
                                    if (!visited[vertex]) {
                                        stack.push(vertex);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            """;

    public static final String DFS_PYTHON = """
            from collections import defaultdict
            
            class Graph:
                def __init__(self, vertices):
                    self.vertices = vertices
                    self.adj = defaultdict(list)
            
                def add_edge(self, v, w):
                    self.adj[v].append(w)
            
                def dfs(self, start):
                    visited = [False] * self.vertices
                    stack = [start]
            
                    while stack:
                        current = stack.pop()
                        if not visited[current]:
                            visited[current] = True
                            print(current, end=' ')
            
                            for vertex in self.adj[current]:
                                if not visited[vertex]:
                                    stack.append(vertex)
            """;

    public static final String DFS_CPP = """
            #include <iostream>
            #include <vector>
            #include <stack>
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
            
                void dfs(int start) {
                    vector<bool> visited(vertices, false);
                    stack<int> stack;
                    stack.push(start);
            
                    while (!stack.empty()) {
                        int current = stack.top();
                        stack.pop();
            
                        if (!visited[current]) {
                            visited[current] = true;
                            cout << current << " ";
            
                            for (int vertex : adj[current]) {
                                if (!visited[vertex]) {
                                    stack.push(vertex);
                                }
                            }
                        }
                    }
                }
            };
            """;

    public static final String DFS_DESCRIPTION = """
            DFS (Depth-First Search) — алгоритм обхода графа в глубину, который работает за O(V + E) времени, где V — количество вершин, E — количество рёбер.
            """;

    public static final String DFS_ADDITIONAL_INFO = """
            DFS использует стек для хранения вершин, которые нужно посетить. Алгоритм начинает с начальной вершины, помечает её как посещённую и добавляет в стек. 
            Затем алгоритм извлекает вершину из стека и добавляет в стек всех соседей этой вершины, которые ещё не были посещены. 
            Процесс продолжается до тех пор, пока стек не опустеет.
            
            DFS позволяет найти все вершины, достижимые из данной вершины, и определить, есть ли путь между двумя вершинами.
            """;
}
