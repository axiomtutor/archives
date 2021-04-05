import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Returns a widest path between two vertices in an undirected graph. A widest path between two
 * vertices maximizes the weight of the minimum-weight edge in the path.
 * <p/>
 * There are multiple ways to solve this problem. The following algorithms may be helpful:
 * - Kruskal's algorithm using Union Find, or
 * - Prim's algorithm using Binary Min Heap (Priority Queue)
 * Feel free to use any previous algorithms that you have already implemented.
 */
public final class WidestPath {
    private WidestPath() {}

    /**
     * Computes a widest path from {@param src} to {@param tgt} for an undirected graph. 
     * If there are
     * multiple widest paths, this method may return any one of them.
     * Input {@param g} guaranteed to be undirected.
     * Input {@param src} and {@param tgt} are guaranteed to be valid and in-bounds.
     * <p/>
     * Do NOT modify this method header.
     *
     * @param g   the graph
     * @param src the vertex from which to start the search
     * @param tgt the vertex to find via {@code src}
     * @return an ordered list of vertices on a widest path from {@code src} to {@code tgt}, or an
     * empty list if there is no such path. The first element is {@code src} and the last
     * element is {@code tgt}. If {@code src == tgt}, a list containing just that element is
     * returned.
     * @implSpec This method should run in worst-case runtime is O((m+n)lgn).
     */
    public static List<Integer> getWidestPath(Graph g, int src, int tgt) {
        // Prim:
        BinaryMinHeap<Double, Integer> pq = 
                new BinaryMinHeapImpl<Double, Integer>();
        int[] parents = new int[g.getSize()];
        double[] keys = new double[g.getSize()];
        Set<Integer> visited = new HashSet<Integer>();
        for (int i = 0; i < g.getSize(); i++) {
            pq.add(Double.POSITIVE_INFINITY, i);
            keys[i] = Double.POSITIVE_INFINITY;
        }
        // Makes weights negative
        Graph gNeg = new Graph(g.getSize());
        for (int i = 0; i < g.getSize(); i++) {
            for (int neighbor : g.outNeighbors(i)) {
                int weightNeg = g.getWeight(i, neighbor);
                gNeg.addEdge(i, neighbor, -weightNeg);
            }
        }
        pq.decreaseKey(src, 0.0);
        keys[src] = 0.0;
        while (!(pq.isEmpty())) {
            int u = pq.extractMin().value;
            visited.add(u);
            for (int neighbor : gNeg.outNeighbors(u)) {
                int weight = gNeg.getWeight(u, neighbor);
                if (weight < keys[neighbor]) {
                    if (pq.containsValue(neighbor)) {
                        keys[neighbor] = (double) weight;
                        pq.decreaseKey(neighbor, keys[neighbor]);
                        parents[neighbor] = u;
                    }
                }
            }
        }
        List<Integer> path = new LinkedList<Integer>();
        path.add(0, tgt);
        int vertex = tgt;
        while (vertex != src) {
            // If no path from src to tgt, return empty list:
            if (keys[vertex] == Double.POSITIVE_INFINITY) {
                return new LinkedList<Integer>();
            }
            vertex = parents[vertex];
            path.add(0, vertex);
        }
        return path;
    }
}
