package sbu.cs;

import java.util.List;

public class MatrixMultiplication {

    public static class BlockMultiplier implements Runnable
    {
        List<List<Integer>> tempMatrixProduct;
        public BlockMultiplier() {
            // TODO
        }

        @Override
        public void run() {
            /*
            TODO
                Perform the calculation and store the final values in tempMatrixProduct
            */
        }
    }

    /*
    Matrix A is of the form p x q
    Matrix B is of the form q x r
    both p and r are even numbers
    */
    public static List<List<Integer>> ParallelizeMatMul(List<List<Integer>> matrix_A, List<List<Integer>> matrix_B)
    {
        /*
        TODO
            Parallelize the matrix multiplication by dividing tasks between 4 threads.
            Each thread should calculate one block of the final matrix product. Each block should be a quarter of the final matrix.
            Combine the 4 resulting blocks to create the final matrix product and return it.
         */
        return null;
    }

    public static void main(String[] args) {
        // Test your code here
    }
}
