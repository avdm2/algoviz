package ru.hse.utils;

public class QuicksortData {

    public static final String QUICKSORT_JAVA = """
            class QuickSort {
                static void quickSort(int[] array, int low, int high) {
                    if (low < high) {
                        int pi = partition(array, low, high);
                        quickSort(array, low, pi - 1);
                        quickSort(array, pi + 1, high);
                    }
                }
            
                static int partition(int[] array, int low, int high) {
                    int pivot = array[high];
                    int i = low - 1;
            
                    for (int j = low; j < high; j++) {
                        if (array[j] <= pivot) {
                            i++;
                            int temp = array[i];
                            array[i] = array[j];
                            array[j] = temp;
                        }
                    }
            
                    int temp = array[i + 1];
                    array[i + 1] = array[high];
                    array[high] = temp;
            
                    return i + 1;
                }
            }
            """;

    public static final String QUICKSORT_PYTHON = """
            def partition(array, low, high):
                pivot = array[high]
                i = low - 1
            
                for j in range(low, high):
                    if array[j] <= pivot:
                        i += 1
                        array[i], array[j] = array[j], array[i]
            
                array[i + 1], array[high] = array[high], array[i + 1]
                return i + 1
            
            def quick_sort(array, low, high):
                if low < high:
                    pi = partition(array, low, high)
                    quick_sort(array, low, pi - 1)
                    quick_sort(array, pi + 1, high)
            """;

    public static final String QUICKSORT_CPP = """
            #include <iostream>
            using namespace std;
            
            int partition(int array[], int low, int high) {
                int pivot = array[high];
                int i = (low - 1);
            
                for (int j = low; j < high; j++) {
                    if (array[j] <= pivot) {
                        i++;
                        int temp = array[i];
                        array[i] = array[j];
                        array[j] = temp;
                    }
                }
            
                int temp = array[i + 1];
                array[i + 1] = array[high];
                array[high] = temp;
            
                return (i + 1);
            }
            
            void quickSort(int array[], int low, int high) {
                if (low < high) {
                    int pi = partition(array, low, high);
                    quickSort(array, low, pi - 1);
                    quickSort(array, pi + 1, high);
                }
            }
            """;

    public static final String QUICKSORT_DESCRIPTION = """
            Быстрая сортировка — алгоритм сортировки массива, который работает за O(n log n) времени.
            Основан на принципе разделяй и властвуй.
            """;

    public static final String QUICKSORT_ADDITIONAL_INFO = """
            Алгоритм быстрой сортировки (quicksort) является одним из самых быстрых алгоритмов сортировки.
            Он основан на принципе разделяй и властвуй: массив разбивается на две части относительно опорного элемента,
            после чего каждая из частей сортируется рекурсивно.
            
            Время работы алгоритма в среднем составляет O(n log n), где n — количество элементов в массиве.
            В худшем случае алгоритм работает за O(n^2) времени, что достигается при неудачном выборе опорного элемента.
            
            Реализация быстрой сортировки в стандартных библиотеках:
            - Java: Arrays.sort()
            - Python: list.sort()
            - C++: std::sort()
            """;
}
