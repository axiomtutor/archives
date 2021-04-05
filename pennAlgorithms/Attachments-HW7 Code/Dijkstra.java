import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Provides access to Dijkstra's algorithm for a weighted graph.
 */
final public class Dijkstra {
    private Dijkstra() {}

    /**
     * Computes the shortest path between two nodes in a weighted graph.
     * Input graph is guaranteed to be valid and have no negative-weighted edges.
     *
     * @param g   the weighted graph to compute the shortest path on
     * @param src the source node
     * @param tgt the target node
     * @return an empty list if there is no path from {@param src} to {@param tgt}, otherwise an
     * ordered list of vertices in the shortest path from {@param src} to {@param tgt},
     * with the first element being {@param src} and the last element being {@param tgt}.
     */
    public static List<Integer> getShortestPath(Graph g, int src, int tgt) {
        BinaryMinHeap<Double, Integer> bmh = 
                new BinaryMinHeapImpl<Double, Integer>();
        int[] parents = new int[g.getSize()];
        double[] distances = new double[g.getSize()];
        Set<Integer> visited = new HashSet<Integer>();
        for (int i = 0; i < g.getSize(); i++) {
            bmh.add(Double.POSITIVE_INFINITY, i);
            distances[i] = Double.POSITIVE_INFINITY;
        }
        bmh.decreaseKey(src, 0.0);
        distances[src] = 0.0;
        while (!(bmh.isEmpty())) {
            int u = bmh.extractMin().value;
            visited.add(u);
            for (int neighbor : g.outNeighbors(u)) {
                int weight = g.getWeight(u, neighbor);
                // Relaxation:
                if (distances[neighbor] > distances[u] + weight) {
                    distances[neighbor] = distances[u] + weight;
                    bmh.decreaseKey(neighbor, distances[neighbor]);
                    parents[neighbor] = u;
                }
            }
        }
        List<Integer> path = new LinkedList<Integer>();
        path.add(0, tgt);
        int vertex = tgt;
        while (vertex != src) {
            // If no path from src to tgt, return empty list:
            if (distances[vertex] == Double.POSITIVE_INFINITY) {
                return new LinkedList<Integer>();
            }
            vertex = parents[vertex];
            path.add(0, vertex);
        }
        return path;
    }
}
