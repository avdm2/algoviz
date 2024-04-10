package ru.hse.utils;

public class BinaryTreeData {

    public static final String BINARY_TREE_JAVA = """
            public class BinaryTree {
                private static class Node {
                    int key;
                    Node left, right;
        
                    public Node(int item) {
                        key = item;
                        left = right = null;
                    }
                }
        
                Node root;
        
                public BinaryTree() {
                    root = null;
                }
        
                private Node insertRec(Node root, int key) {
                    if (root == null) {
                        root = new Node(key);
                        return root;
                    }
        
                    if (key < root.key) {
                        root.left = insertRec(root.left, key);
                    } else if (key > root.key) {
                        root.right = insertRec(root.right, key);
                    }
        
                    return root;
                }
        
                public void insert(int key) {
                    root = insertRec(root, key);
                }
        
                private void inorderRec(Node root) {
                    if (root != null) {
                        inorderRec(root.left);
                        System.out.print(root.key + " ");
                        inorderRec(root.right);
                    }
                }
        
                public void inorder() {
                    inorderRec(root);
                }
            }
        """;

    public static final String BINARY_TREE_PYTHON = """
            class Node:
                def __init__(self, key):
                    self.key = key
                    self.left = None
                    self.right = None
            
            class BinaryTree:
                def __init__(self):
                    self.root = None
            
                def insert_rec(self, root, key):
                    if root is None:
                        return Node(key)
            
                    if key < root.key:
                        root.left = self.insert_rec(root.left, key)
                    elif key > root.key:
                        root.right = self.insert_rec(root.right, key)
            
                    return root
            
                def insert(self, key):
                    self.root = self.insert_rec(self.root, key)
            
                def inorder_rec(self, root):
                    if root is not None:
                        self.inorder_rec(root.left)
                        print(root.key, end=' ')
                        self.inorder_rec(root.right)
            
                def inorder(self):
                    self.inorder_rec(self.root)
            """;

    public static final String BINARY_TREE_CPP = """
            #include <iostream>
            
            using namespace std;
            
            class Node {
            public:
                int key;
                Node* left;
                Node* right;
            
                Node(int item) {
                    key = item;
                    left = right = nullptr;
                }
            };
            
            class BinaryTree {
            private:
                Node* root;
            
                Node* insert_rec(Node* root, int key) {
                    if (root == nullptr) {
                        return new Node(key);
                    }
            
                    if (key < root->key) {
                        root->left = insert_rec(root->left, key);
                    } else if (key > root->key) {
                        root->right = insert_rec(root->right, key);
                    }
            
                    return root;
                }
            
                void inorder_rec(Node* root) {
                    if (root != nullptr) {
                        inorder_rec(root->left);
                        cout << root->key << ' ';
                        inorder_rec(root->right);
                    }
                }
            
            public:
                BinaryTree() {
                    root = nullptr;
                }
            
                void insert(int key) {
                    root = insert_rec(root, key);
                }
            
                void inorder() {
                    inorder_rec(root);
                }
            };
            """;

    public static final String BINARY_TREE_DESCRIPTION = """
            Бинарное дерево — это иерархическая структура данных, в которой каждый узел имеет не более двух потомков.
            """;

    public static final String BINARY_TREE_ADDITIONAL_INFO = """
            Бинарное дерево — это одна из самых распространённых структур данных в информатике.
            Узел, который не имеет потомков, называется листом. В бинарном дереве нет узлов, у которых больше двух потомков.
            
            Бинарное дерево может быть пустым, то есть не содержать ни одного узла.
            Если бинарное дерево не пустое, то оно состоит из корня и двух поддеревьев, которые являются бинарными деревьями.
            
            Бинарное дерево может быть:
            - пустым;
            - строгим (у каждого узла либо два потомка, либо ни одного);
            - полным (у каждого узла либо два потомка, либо ни одного, причём все листья на одном уровне);
            - сбалансированным (разница высот левого и правого поддеревьев каждого узла не превышает 1).
            
            Бинарное дерево используется для решения различных задач, например:
            - поиска элемента;
            - вставки элемента;
            - удаления элемента;
            - обхода дерева.
            
            Реализация бинарного дерева в стандартных библиотеках:
            - Java: TreeSet и TreeMap;
            - C++: set и map.
            """;
}
