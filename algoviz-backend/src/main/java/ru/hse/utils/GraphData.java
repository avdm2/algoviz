package ru.hse.utils;

public class GraphData {

    public static final String GRAPH_JAVA = """
        import java.util.ArrayList;
        import java.util.List;
    
        public class Graph {
            private final int vertices;
            private final List<List<Integer>> adj;
    
            public Graph(int vertices) {
                this.vertices = vertices;
                adj = new ArrayList<>(vertices);
                for (int i = 0; i < vertices; i++) {
                    adj.add(new ArrayList<>());
                }
            }
    
            public void addEdge(int v, int w) {
                adj.get(v).add(w);
            }
    
            public void print() {
                for (int i = 0; i < vertices; i++) {
                    System.out.print(i + ": ");
                    for (int j : adj.get(i)) {
                        System.out.print(j + " ");
                    }
                    System.out.println();
                }
            }
        }
        """;

    public static final String GRAPH_PYTHON = """
            class Graph:
                def __init__(self, vertices):
                    self.vertices = vertices
                    self.adj = [[] for _ in range(vertices)]
            
                def add_edge(self, v, w):
                    self.adj[v].append(w)
            
                def print(self):
                    for i in range(self.vertices):
                        print(i, end=': ')
                        for j in self.adj[i]:
                            print(j, end=' ')
                        print()
            """;

    public static final String GRAPH_CPP = """
            #include <iostream>
            #include <vector>
            
            using namespace std;
            
            class Graph {
            private:
                int vertices;
                vector<vector<int>> adj;
            
            public:
                Graph(int vertices) {
                    this->vertices = vertices;
                    adj.resize(vertices);
                }
            
                void add_edge(int v, int w) {
                    adj[v].push_back(w);
                }
            
                void print() {
                    for (int i = 0; i < vertices; i++) {
                        cout << i << ": ";
                        for (int j : adj[i]) {
                            cout << j << " ";
                        }
                        cout << endl;
                    }
                }
            };
            """;

    public static final String GRAPH_DESCRIPTION = """
            Граф — структура данных, в которой элементы хранятся в виде вершин и рёбер, соединяющих вершины между собой.
            """;

    public static final String GRAPH_ADDITIONAL_INFO = """
            Графы могут быть:
            - ориентированными и неориентированными. В ориентированном графе рёбра имеют направление, в неориентированном — нет.
            - взвешенными и невзвешенными. В взвешенном графе каждому ребру присвоено значение (вес).
            - связными и несвязными. Связный граф — граф, в котором есть путь от любой вершины к любой другой вершине.
            - ациклическими и циклическими. Ациклический граф — граф, в котором нет циклов.
            """;
}
