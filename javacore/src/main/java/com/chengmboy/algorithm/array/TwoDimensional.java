package com.chengmboy.algorithm.array;

import java.util.Random;

/**
 * @author cheng_mboy
 */
public class TwoDimensional {

    /**
     * 填充一个二维数组，每一行从左到右递增排序，
     * 每一列从上到下递增排序。
     *
     * @param a   需要填充的二维数组
     * @param max 二维数组最大值
     */
    static int[][] fill(int[][] a, int max) {
        Random random = new Random();
        int bv = max - (a.length + a[0].length) + 2;
        if (bv < 0) {
            throw new IllegalArgumentException("最大值设置太小");
        }
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                int v;
                do {
                    int bound = bv + i + j + 1;
                    if (bound == 0) {
                        v = 0;
                    } else {
                        v = random.nextInt(bound);
                    }
                    if (i == 0 && j == 0) {
                        break;
                    }
                    if (i - 1 >= 0 && j - 1 >= 0) {
                        if (v > a[i - 1][j] && v > a[i][j - 1]) {
                            break;
                        }
                    } else {
                        if (i - 1 >= 0) {
                            if (v > a[i - 1][j]) {
                                break;
                            }
                        }
                        if (j - 1 >= 0) {
                            if (v > a[i][j - 1]) {
                                break;
                            }
                        }
                    }
                } while (true);
                a[i][j] = v;
            }
        }
        return a;
    }

    /**
     * 一个二维数组，每一行从左到右递增排序，
     * 每一列从上到下递增排序。判断数组中是含有该整数。
     *
     * @param a 提供的查找的数组。
     * @param n 需要查找的整数n
     * @return 存在该整数返回true，否则false
     */
    static boolean findInPartiallySortedMatrix(int[][] a, int n) {
        int k = 0;
        for (int i = a.length - 1; i >= 0; i--) {
            for (int j = k; j < a[i].length; j++) {
                if (n == a[i][j]) {
                    return true;
                }
                if (n < a[i][j]) {
                    k = j;
                    break;
                }
            }
        }
        return false;
    }

    /**
     * 遍历二维数组
     */
    static void traverse(int[][] a) {
        for (int[] b : a) {
            for (int c : b) {
                System.out.printf("%-5s", c);
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        int[][] a = new int[3][4];
        int max = 10;
        fill(a, max);
        traverse(a);
        int n = new Random().nextInt(max + 1);
        System.out.println(findInPartiallySortedMatrix(a, n) ? String.format("%-3s存在", n) : String.format("%-3s不存在", n));
    }
}
