package lab5;

public class Isomorphic {
    private int[][] firstGraph;
    private int[][] secondGraph;
    private int[] degForFirst;
    private int[] degForSecond;
    private int vertex;
    private int flag;
    
    
    
    Isomorphic(int[][] firstGraph, int[][] secondGraph, int vertex, int[] degForFirst, int[] degForSecond, int flag){
        this.firstGraph = firstGraph;
        this.secondGraph = secondGraph;
        this.degForFirst = degForFirst;
        this.degForSecond = degForSecond;
        this.flag = flag;
        this.vertex = vertex;
    }
    
    
    public void getResult() {
        int degreeA;
        int degreeB;
        if (flag == vertex) {
            degreeA = checkdegree(firstGraph, vertex, degForFirst);
            degreeB = checkdegree(secondGraph, vertex, degForSecond);
            if (degreeA == degreeB) {
                sort(degForFirst, vertex);
                sort(degForSecond, vertex);
                flag = isIsomorphic(degForFirst, degForSecond, vertex);
                if (flag == 1)
                    System.out.println("Обидва графи не ізоморфні !");
                else if (flag == 0)
                    System.out.println("Обидва графи ізоморфні !");
            } else {
                System.out.println("Обидва графи не ізоморфні !");
            }
        } else {
            System.out.println("Обидва графи ізоморфні !");
        }
    }

    static int checkdegree(int A[][], int vertex, int degA[]) {
        int i = 0, j, degreeA, k = 0;
        for (; i < vertex; i++, k++) {
            j = 0;
            degreeA = 0;
            for (; j < vertex; j++) {
                if (A[i][j] == 1)
                    degreeA++;
            }
            degA[k] = degreeA;
        }
        degreeA = 0;
        for (k = 0; k < vertex; k++) {
            degreeA = (degreeA + degA[k]);
        }
        return (degreeA);
    }

    static void sort(int degA[], int vertex) {
        int i = 0, j, temp = 0;
        for (; i < vertex; i++) {
            j = 0;
            for (; j < vertex - i - 1; j++) {
                if (degA[j] > degA[j + 1]) {
                    temp = degA[j];
                    degA[j] = degA[j + 1];
                    degA[j + 1] = temp;
                }
            }
        }
        return;
    }

    static int isIsomorphic(int degA[], int degB[], int vertex) {
        int flag = 0, i = 0, j = 0;
        for (; i < vertex; i++, j++) {
            if (degA[i] != degB[j]) {
                flag = 1;
                break;
            }
        }
        return flag;
    }
}