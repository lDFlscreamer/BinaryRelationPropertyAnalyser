/*
 * Copyright (c)  2.2020
 * This file (Main) is part of BinaryRelationPropertyAnalyser.
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 * Written by Screamer  <999screamer999@gmail.com>
 */

package org.kpi.TheoryOfDecision;


import org.kpi.TheoryOfDecision.controler.Worker;
import org.kpi.TheoryOfDecision.entity.Result;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 1, 1, 1, 1, 0, 1},
                {0, 1, 1, 1, 1, 0, 1},
                {0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 0},
                {0, 0, 1, 1, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1},
                {0, 0, 1, 1, 1, 0, 1}
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
