package com.chengmboy.algorithm.sort;

import java.util.Random;

/**
 * @author cheng_mboy
 */
public class Sort {

    public static void main(String[] args) {
        int[] array = randomArray(10);
        System.out.println(arrayToString(array));
        bubbleSort(array);
        System.out.println(arrayToString(array));
        boolean b = checkSort(array, Order.DESC);
        System.out.println(b);
    }

    private static int[] randomArray(int length) {
        int[] array = new int[length];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100);
        }
        return array;
    }

    private static String arrayToString(int[] array) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i : array) {
            builder.append(i).append(",");
        }
        builder.deleteCharAt(builder.length() - 1);
        builder.append("]");
        return builder.toString();
    }

    private static boolean checkSort(int[] array, Order o) {
        if (o == Order.ASC) {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] > array[i + 1]) {
                    return false;
                }
            }
        } else {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] < array[i + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 冒泡排序
     */
    private static void bubbleSort(int[] array) {
        int len = array.length;
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int j = 0; j < len-1; j++) {
                if (array[j] < array[j + 1]) {
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    flag = true;
                }
            }
            len--;
        }
    }

    /**
     * 选择排序
     */
    private static void chooseSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int k = i;
            int temp = array[i];
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < temp) {
                    temp = array[j];
                    k = j;
                }
            }
            if (k > i) {
                array[k] = array[i];
                array[i] = temp;
            }
        }
    }

    /**
     * 快速排序
     */
    private static void quickSort(int[] array, int start, int end) {
        if (start > end) {
            return;
        }
        int i = start;
        int j = end;
        int key = array[start];
        while (i < j) {
            while (j > i && array[j] >= key) {
                j--;
            }
            if (i < j) {
                array[i] = array[j];
                i++;
            }

            while (i < j && array[i] <= key) {
                i++;
            }
            if (i < j) {
                array[j] = array[i];
                j--;
            }
        }
        array[i] = key;
        quickSort(array, start, i - 1);
        quickSort(array, j + 1, end);
    }

    private enum Order {
        /**
         * 升序
         */
        ASC,
        /**
         * 降序
         */
        DESC
    }
}
