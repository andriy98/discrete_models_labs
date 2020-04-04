package lab3;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        MaxFlow maxFlow = new MaxFlow(6, 0, 5);
        Main.init(maxFlow);
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println("Максимально можливий потік: " + maxFlow.findSolution());
    }

    private static MaxFlow init(MaxFlow maxFlowObject) {
        try {
            Scanner sc = new Scanner(new File("graph_lab3.txt"));
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(" ");
                int[] values = Arrays.stream(line).mapToInt(Integer::parseInt).toArray();

                maxFlowObject.addArc(values[0], values[1], values[2]);
            }

            return maxFlowObject;
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
