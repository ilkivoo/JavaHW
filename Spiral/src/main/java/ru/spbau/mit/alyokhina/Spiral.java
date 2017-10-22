package ru.spbau.mit.alyokhina;

import java.util.Arrays;
import java.util.Comparator;
/**
 * Класс, который хранит массив. Может его выводить по спирали и сортировать по столбцам
 */
public class Spiral {
    private int[][] matrix;

    /**  Заполняет массив. Для более удобного способа сортирования, транспонируем матрицу*/
    public Spiral(int[][] array) throws Exception{
        if (array == null) {
            throw new NullPointerException(" Array is empty");
        }
        if (array.length % 2 == 0) {
            throw new IllegalArgumentException("Invalid array size");
        }
        matrix = new int[array.length][array.length];
        for (int i =0; i < array.length; i++) {
            for (int j = 0; j <array.length; j++) {
                matrix[i][j] = array[j][i];
            }
        }
    }
    /** сортируем строки транспонированной матрицы*/
    public void sort() {
        Arrays.sort(matrix, Comparator.comparingInt(col -> col[0]));
    }
    /** Вывод матрицы. Обратно транспонируем, чтобы получить нужную матрицу*/
    public void print() {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.print(matrix[j][i]);
                System.out.print(' ');
            }

            System.out.print('\n');
        }
    }
    /** Вывод по спирали. Из центра идем влево, а дальше по часовой стрелке (с учетом транспонированноти)*/
    public void printSpiral() {
        System.out.println(matrix[matrix.length/2][matrix.length/2]);
        int i = matrix.length / 2 - 1;
        int j = matrix.length / 2;
        for (int lengthSpiral  = 2; lengthSpiral < matrix.length; lengthSpiral += 2) {
            for (int k = 0; k < lengthSpiral; k++) {
                System.out.println(matrix[i][j]);
                j--;
            }
            j++;
            i++;
            for (int k = 0; k < lengthSpiral; k++) {
                System.out.println(matrix[i][j]);
                i++;
            }
            i--;
            j++;
            for (int k =0; k < lengthSpiral; k++) {
                System.out.println(matrix[i][j]);
                j++;
            }
            j--;
            i--;
            for(int k = 0; k < lengthSpiral; k++) {
                System.out.println(matrix[i][j]);
                i--;
            }
        }
    }
}