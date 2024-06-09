package sbu.cs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatrixMultiplication {

    public static class BlockMultiplier implements Runnable {
        List <List <Integer>> tempMatrixProduct;
        List <List <Integer>> matrixA;
        List <List <Integer>> matrixB;
        int startingRow;
        int endingRow;
        int startingCol;
        int endingCol;

        public BlockMultiplier (List <List <Integer>> tempMatrixProduct, List <List <Integer>> matrixA,
                                List <List <Integer>> matrixB, int startingRow, int endingRow,
                                int startingCol, int endingCol) {
            this.tempMatrixProduct = tempMatrixProduct;
            this.matrixA = matrixA;
            this.matrixB = matrixB;
            this.startingRow = startingRow;
            this.endingRow = endingRow;
            this.startingCol = startingCol;
            this.endingCol = endingCol;
        }


        @Override
        public void run() {
            /*
                Perform the calculation and store the final values in tempMatrixProduct
            */
            for (int i = startingRow; i < endingRow; i++) {
                for (int j = startingCol; j < endingCol; j++) {
                    int sum = 0;
                    for (int k = 0; k < matrixA.get(0).size(); k++) {
                        sum += matrixA.get(i).get(k) * matrixB.get(k).get(j);
                    }
                    tempMatrixProduct.get(i).set(j, sum);
                }
            }
        }
    }

    /*
    Matrix A is of the form p x q
    Matrix B is of the form q x r
    both p and r are even numbers
    */
    public static List <List <Integer>> ParallelizeMatMul (List <List <Integer>> matrixA, List <List <Integer>> matrixB) {
        /*
            Parallelize the matrix multiplication by dividing tasks between 4 threads.
            Each thread should calculate one block of the final matrix product. Each block should be a quarter of the final matrix.
            Combine the 4 resulting blocks to create the final matrix product and return it.
         */
        int row = matrixA.size();
        int col = matrixB.get(0).size();
        List <List <Integer>> matrixC = new ArrayList<>();
        for (int i = 0; i < row; i++) {
            matrixC.add(new ArrayList<>(Collections.nCopies(col, 0)));
        }

        BlockMultiplier blockMultiplier1 = new BlockMultiplier(matrixC , matrixA , matrixB , 0 , row / 2 , 0 , col / 2);
        BlockMultiplier blockMultiplier2 = new BlockMultiplier(matrixC , matrixA , matrixB , 0 , row / 2 , col / 2 , col);
        BlockMultiplier blockMultiplier3 = new BlockMultiplier(matrixC , matrixA , matrixB , row / 2 , row , 0 , col / 2);
        BlockMultiplier blockMultiplier4 = new BlockMultiplier(matrixC , matrixA , matrixB , row / 2 , row , col / 2 , col);

        Thread thread1 = new Thread(blockMultiplier1);
        Thread thread2 = new Thread(blockMultiplier2);
        Thread thread3 = new Thread(blockMultiplier3);
        Thread thread4 = new Thread(blockMultiplier4);

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        return matrixC;
    }

    public static void main(String[] args) {
        // Test your code here
    }
}
