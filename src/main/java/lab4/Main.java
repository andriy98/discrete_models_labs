package lab4;

import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        int[][] graph = readGraphFromFile("graph_lab4.txt");;

        // Boolean array to check if a node
        // has been visited or not
        boolean[] v = new boolean[graph.length];

        // Mark 0th node as visited
        v[0] = true;
        int ans = Integer.MAX_VALUE;

        // Find the minimum weight Hamiltonian Cycle
        ans = TravellingSalesMan.tsp(graph, v, 0, graph.length, 1, 0, ans);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Мінімальна довжина маршруту: " + ans);
    }

    public static int[][] readGraphFromFile(String filename) throws Exception {
        int[][] matrix = null;

        BufferedReader buffer = new BufferedReader(new FileReader(filename));

        String line;
        int row = 0;
        int size = 0;

        while ((line = buffer.readLine()) != null) {
            String[] vals = line.trim().split("\\s+");

            // Lazy instantiation.
            if (matrix == null) {
                size = vals.length;
                matrix = new int[size][size];
            }

            for (int col = 0; col < size; col++) {
                matrix[row][col] = Integer.parseInt(vals[col]);
            }

            row++;
        }

        return matrix;
    }
}
