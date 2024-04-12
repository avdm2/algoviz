package ru.hse.utils;

public class ArrayData {

    public static final String ARRAY_INITIALIZATION_JAVA = """
        int[] array = new int[]{1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(array));
        """;

    public static final String ARRAY_INITIALIZATION_PYTHON = """
        array = [1, 2, 3, 4, 5]
        print(array)
        """;

    public static final String ARRAY_INITIALIZATION_CPP = """
            int myNum[3] = {10, 20, 30};
            for (int i = 0; i < 3; i++) {
                cout << myNum[i] << endl;
            }
            """;

    public static final String ARRAY_DESCRIPTION = """
            Массив — структура данных, в которой элементы хранятся в памяти последовательно и доступ к ним осуществляется по индексу.
            """;

    public static final String ARRAY_ADDITIONAL_INFO = """
            Массивы в различных языках программирования могут быть одномерными, многомерными, динамическими и статическими.
            Они нужны в ситуациях, когда необходимо работать с однотипным набором данных, например последовательностью чисел, объектов или строк.
            
            Массив помогает:
            - хранить несколько однотипных значений внутри одной переменной;
            - структурировать и упорядочивать информацию;
            - легко получать доступ к каждому элементу;
            - быстро применять одинаковые действия ко всем элементам массива;
            - экономить память и поддерживать высокую скорость выполнения действий.
            
            Реализация динамического массива в стандартных библиотеках:
            - Java: ArrayList;
            - Python: list;
            - C++: std::vector.
            """;
}
