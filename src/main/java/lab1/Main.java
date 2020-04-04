package lab1;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MutableValueGraph graph = ValueGraphBuilder.undirected().build();
        Main.initGraph(graph);
        BoruvkaMST boruvkaMST = new BoruvkaMST(graph);
        System.out.println();
        System.out.println();

        System.out.println("Результат:");
        System.out.println();
        System.out.println(boruvkaMST.toString());
    }

    private static MutableValueGraph initGraph(MutableValueGraph graph) {
        try {
            Scanner sc = new Scanner(new File("graph.txt"));
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(" ");
                int[] values = Arrays.stream(line).mapToInt(Integer::parseInt).toArray();

                graph.putEdgeValue(values[0], values[1], values[2]);
            }

            return graph;
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
