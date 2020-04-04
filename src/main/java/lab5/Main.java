package lab5;

public class Main {
    public static void main(String[] args) throws Exception {
        int A[][] = lab4.Main.readGraphFromFile("graph_lab5_1.txt");
        int B[][] = lab4.Main.readGraphFromFile("graph_lab5_2.txt");
        int vertex, degA[], degB[], flag;
        degA = new int[A.length];
        degB = new int[B.length];
        flag = B.length;
        vertex = A.length;
        System.out.println();
        System.out.println("Граф номер 1: ");
        displayGraph(A, flag);
        System.out.println();
        System.out.println("Граф номер 2: ");
        displayGraph(B, vertex);
        System.out.println();
        System.out.println("Результат: ");
        new Isomorphic(A, B, vertex, degA, degB, flag).getResult();
    }

    static void displayGraph(int A[][], int vertex) {
        int i = 0, j;
        for (; i < vertex; i++) {

            for (j = 0; j < vertex; j++) {
                System.out.print(" " + A[i][j]);
            }
            System.out.println();
        }
        return;
    }
}
