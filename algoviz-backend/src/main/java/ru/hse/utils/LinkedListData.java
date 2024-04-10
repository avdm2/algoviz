package ru.hse.utils;

public class LinkedListData {

    public static final String CUSTOM_LINKED_LIST_JAVA = """
            public class Node {
                int data;
                Node next;
        
                public Node(int data) {
                    this.data = data;
                }
            }
        
            public class LinkedList {
                Node head;
        
                public void add(int data) {
                    if (head == null) {
                        head = new Node(data);
                        return;
                    }
        
                    Node current = head;
                    while (current.next != null) {
                        current = current.next;
                    }
                    current.next = new Node(data);
                }
       
                public void print() {
                    Node current = head;
                    while (current != null) {
                        System.out.print(current.data + " ");
                        current = current.next;
                    }
                }
            }
        }
        """;

    public static final String CUSTOM_LINKED_LIST_PYTHON = """
            class Node:
                def __init__(self, data):
                    self.data = data
                    self.next = None
        
            class LinkedList:
                def __init__(self):
                    self.head = None
        
                def add(self, data):
                    if self.head is None:
                        self.head = Node(data)
                        return
        
                    current = self.head
                    while current.next is not None:
                        current = current.next
                    current.next = Node(data)
        
                def print(self):
                    current = self.head
                    while current is not None:
                        print(current.data, end=' ')
                        current = current.next
        """;

    public static final String CUSTOM_LINKED_LIST_CPP = """
            struct Node {
                int data;
                Node* next;
        
                Node(int data) : data(data), next(nullptr) {}
            };
        
            class LinkedList {
            public:
                Node* head;
        
                LinkedList() : head(nullptr) {}
        
                void add(int data) {
                    if (head == nullptr) {
                        head = new Node(data);
                        return;
                    }
        
                    Node* current = head;
                    while (current->next != nullptr) {
                        current = current->next;
                    }
                    current->next = new Node(data);
                }
        
                void print() {
                    Node* current = head;
                    while (current != nullptr) {
                        cout << current->data << ' ';
                        current = current->next;
                    }
                }
            };
        """;

    public static final String LINKED_LIST_DESCRIPTION = """
            Связный список — структура данных, в которой элементы хранятся в памяти не последовательно, а в произвольных местах, а каждый элемент хранит указатель на следующий элемент.
            """;

    public static final String LINKED_LIST_ADDITIONAL_INFO = """
            Связный список состоит из узлов, каждый из которых содержит данные и указатель на следующий узел.
            Последний узел указывает на null.
            Связный список не имеет фиксированного размера, и его размер может динамически изменяться.
            
            Реализации связного списка в стандартных библиотеках:
            - Java: LinkedList
            - Python: collections.deque
            - C++: std::list
            """;
}
