package ru.hse.utils;

public class BinarySearchData {

    public static final String BINARY_SEARCH_JAVA = """
            class BinarySearch {
                static int binarySearch(int[] array, int target) {
                    int left = 0;
                    int right = array.length - 1;
            
                    while (left <= right) {
                        int mid = left + (right - left) / 2;
            
                        if (array[mid] == target) {
                            return mid;
                        }
            
                        if (array[mid] < target) {
                            left = mid + 1;
                        } else {
                            right = mid - 1;
                        }
                    }
            
                    return -1;
                }
            }
            """;

    public static final String BINARY_SEARCH_PYTHON = """
            def binary_search(array, target):
                left = 0
                right = len(array) - 1
            
                while left <= right:
                    mid = left + (right - left) // 2
            
                    if array[mid] == target:
                        return mid
            
                    if array[mid] < target:
                        left = mid + 1
                    else:
                        right = mid - 1
            
                return -1
            """;

    public static final String BINARY_SEARCH_CPP = """
            int binarySearch(int array[], int target) {
                int left = 0;
                int right = sizeof(array) / sizeof(array[0]) - 1;
            
                while (left <= right) {
                    int mid = left + (right - left) / 2;
            
                    if (array[mid] == target) {
                        return mid;
                    }
            
                    if (array[mid] < target) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
            
                return -1;
            }
            """;

    public static final String BINARY_SEARCH_DESCRIPTION = """
            Бинарный поиск — алгоритм поиска элемента в отсортированном массиве, который работает за O(log n) времени.
            """;

    public static final String BINARY_SEARCH_ADDITIONAL_INFO = """
            Бинарный поиск — тип поискового алгоритма, который последовательно делит пополам заранее отсортированный массив данных, чтобы обнаружить нужный элемент. 
            Другие его названия — двоичный поиск, метод половинного деления, дихотомия.
            
            Алгоритм бинарного поиска работает за логарифмическое время, что означает, что время его выполнения увеличивается не пропорционально размеру массива, а логарифмически.
            
            Основная последовательность действий алгоритма выглядит так:         
            1. Сортируем массив данных.
            2. Делим его пополам и находим середину.
            3. Сравниваем срединный элемент с заданным искомым элементом.
            4. Если искомое число больше среднего — продолжаем поиск в правой части массива (если он отсортирован по возрастанию): делим ее пополам, повторяя пункт 3. 
            Если же заданное число меньше — алгоритм продолжит поиск в левой части массива, снова возвращаясь к пункту 3.
            
            Реализация бинарного поиска в стандартных библиотеках:
            - Java: Arrays.binarySearch();
            - Python: bisect.bisect_left(), bisect.bisect_right().
            """;
}
