package lab2;

public class Main {
    public static void main(String[] args) {
        PostmanProblem postmanProblem = new PostmanProblem(5); // create a graph of four vertices
        postmanProblem.initGraph();

        System.out.println();
        System.out.println();
        System.out.println();
        postmanProblem.solve();
        postmanProblem.printCPT(0);
        System.out.println();
        System.out.println("Загальна вартість = " + postmanProblem.cost());
    }
}
