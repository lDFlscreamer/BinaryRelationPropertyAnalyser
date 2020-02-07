package org.kpi.parallel;


import org.kpi.parallel.controler.Worker;
import org.kpi.parallel.entity.Result;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[][] matrix = {
                {0, 1, 0, 0, 0, 0, 0},
                {0, 1, 0, 1, 0, 1, 0},
                {1, 0, 1, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 1, 0},
                {0, 1, 0, 1, 0, 1, 0},
                {1, 0, 0, 0, 0, 0, 1},
                {0, 0, 1, 0, 0, 0, 0}
        };
        Worker worker = new Worker();
//        for (int i = 0; i < matrix.length; i++) {
//            char[] chars = new char[1];
//            do {
//                System.out.print("->");
//                String s = scanner.nextLine();
//                chars = s.toCharArray();
//            } while (chars.length < matrix[i].length);
//            for (int j = 0; j < matrix[i].length; j++) {
//                matrix[i][j] = Character.getNumericValue(chars[j]);
//            }
//        }
        for (int[] ints : matrix) {
            for (int anInt : ints) {
                System.out.print(" " + anInt);
            }
            System.out.println();
        }
        Result result = worker.analyse(matrix);
        result.printResult();
    }
}
