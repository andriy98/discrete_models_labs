package lab1;

import com.google.common.graph.*;

import java.lang.reflect.Field;
import java.util.*;

public class BoruvkaMST {

    private static MutableValueGraph<Integer, Integer> mst = ValueGraphBuilder.undirected().build();
    private static int totalWeight;

    public BoruvkaMST(MutableValueGraph<Integer, Integer> graph) {

        int verticesNumber = graph.nodes().size();

        VerticesFinder verticesFinder = new VerticesFinder(verticesNumber);

        for (int t = 1; t < verticesNumber && mst.edges().size() < verticesNumber - 1; t = t + t) {
            EndpointPair<Integer>[] closestEdgeArray = new EndpointPair[verticesNumber];
            findClosestEdges(graph, verticesFinder, closestEdgeArray);
            addEdgesToResult(graph, verticesNumber, verticesFinder, closestEdgeArray);
        }
    }

    private void addEdgesToResult(MutableValueGraph<Integer, Integer> graph, int size, VerticesFinder verticesFinder, EndpointPair<Integer>[] closestEdgeArray) {
        for (int i = 0; i < size; i++) {
            EndpointPair<Integer> edge = closestEdgeArray[i];
            if (edge != null) {
                int u = edge.nodeU();
                int v = edge.nodeV();
                int weight = graph.edgeValueOrDefault(u, v, 0);
                // не додаємо якщо вже існує
                if (verticesFinder.find(u) != verticesFinder.find(v)) {
                    mst.putEdgeValue(u, v, weight);
                    totalWeight += weight;
                    verticesFinder.union(u, v);
                }
            }
        }
    }

    private EndpointPair<Integer>[] findClosestEdges(MutableValueGraph<Integer, Integer> graph, VerticesFinder verticesFinder, EndpointPair<Integer>[] closestEdgeArray) {
        for (EndpointPair<Integer> edge : graph.edges()) {
            int u = edge.nodeU();
            int v = edge.nodeV();
            int uParent = verticesFinder.find(u);
            int vParent = verticesFinder.find(v);
            if (uParent == vParent) {
                continue; // у випадку якщо це те саме дерево
            }

            int weight = graph.edgeValueOrDefault(u, v, 0);

            if (closestEdgeArray[uParent] == null) {
                closestEdgeArray[uParent] = edge;
            }
            if (closestEdgeArray[vParent] == null) {
                closestEdgeArray[vParent] = edge;
            }

            int uParentWeight = graph.edgeValueOrDefault(closestEdgeArray[uParent].nodeU(), closestEdgeArray[uParent].nodeV(), 0);
            int vParentWeight = graph.edgeValueOrDefault(closestEdgeArray[vParent].nodeU(), closestEdgeArray[vParent].nodeV(), 0);

            if (weight < uParentWeight) {
                closestEdgeArray[uParent] = edge;
            }
            if (weight < vParentWeight) {
                closestEdgeArray[vParent] = edge;
            }
        }

        return closestEdgeArray;
    }

    public String toString() {
        try {
            return "Вершини графа: " + mst.nodes() +
                    "\nЗагальна вага: " + totalWeight +
                    "\nМінімальне кістякове дерево: " +
                    "\n" + this.buildReport().toString();
        } catch (Exception e) {
            return "";
        }
    }

    private StringBuilder buildReport() throws IllegalAccessException, NoSuchFieldException {
        StringBuilder result = new StringBuilder();

        for (EndpointPair<Integer> edge : mst.edges()) {
            Field f = mst.getClass().getSuperclass().getDeclaredField("nodeConnections");
            f.setAccessible(true);
            Field map = f.get(mst).getClass().getDeclaredField("backingMap");
            map.setAccessible(true);

            for (Object o : ((LinkedHashMap) map.get(f.get(mst))).entrySet()) {
                Map.Entry pair = (Map.Entry) o;
                if (pair.getKey().equals(edge.nodeU())) {
                    Field f1 = pair.getValue().getClass().getDeclaredField("adjacentNodeValues");
                    f1.setAccessible(true);

                    for (Map.Entry<Integer, Integer> entry : ((HashMap<Integer, Integer>) f1.get(pair.getValue())).entrySet()) {
                        if (entry.getKey().equals(edge.nodeV())) {
                            result.append(edge.nodeU() + " -> " + edge.nodeV() + " Вага ребра: " + entry.getValue() + "\n");
                        }
                    }
                }
            }
        }

        return result;
    }
}
