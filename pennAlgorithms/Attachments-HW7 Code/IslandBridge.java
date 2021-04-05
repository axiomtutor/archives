import java.util.HashSet;
import java.util.Set;

final public class IslandBridge {
    private IslandBridge() {}

    /**
     * See question details in the write-up. Input is guaranteed to be valid.
     *
     * @param g the input graph representing the islands as vertices and bridges as directed edges
     * @param x the starting node
     * @return true, if no matter how you navigate through the one-way bridges from node x,
     * there is always a way to drive back to node x, and false otherwise.
     * @implSpec This method should run in worst-case O(n + m) time.
     */
    
    static ResizingDeque<Integer> getFinishTime(Graph g, int x, 
            ResizingDeque<Integer> finishTimes, Set<Integer> visited) {
        // DFS:
        visited.add(x);
        for (int neighbors : g.outNeighbors(x)) {
            if (!(visited.contains(neighbors))) {
                finishTimes = getFinishTime(g, neighbors, finishTimes, visited);
            }
        }
        finishTimes.addFirst(x);
        // Returns finish times in decreasing order (top of stock is highest)
        return finishTimes;
    }
    
    static Set<Integer> scc(Graph g, int x, Set<Integer> sccs, Set<Integer> visited) {
        // DFS:
        visited.add(x);
        for (int neighbors : g.outNeighbors(x)) {
            if (!(visited.contains(neighbors))) {
                sccs = scc(g, neighbors, sccs, visited);
            }
        }
        sccs.add(x);
        return sccs;
    }
    
    public static boolean allNavigable(Graph g, int x) {
        ResizingDeque<Integer> finishTimes = new ResizingDequeImpl<Integer>();
        Set<Integer> visited = new HashSet<Integer>();
        finishTimes = getFinishTime(g, x, finishTimes, visited);
        // Construct transpose graph:
        Graph transpose = new Graph(g.getSize());
        for (int i = 0; i < g.getSize(); i++) {
            Set<Integer> neighbors = g.outNeighbors(i);
            for (int endpoint : neighbors) {
                transpose.addEdge(endpoint, i, 1);
            }
        }
        // DFS on transpose:
        Set<Set<Integer>> sccs = new HashSet<Set<Integer>>();
        visited.clear();
        while (finishTimes.size() != 0) {
            int v = finishTimes.pollFirst();
            if (!(visited.contains(v))) {
                Set<Integer> oneComponent = scc(transpose, v, new HashSet<Integer>(), 
                        visited);
                visited.addAll(oneComponent);
                sccs.add(oneComponent);
            }
        }
        // Check no edge reaches from one SCC to a vertex to another:
        for (Set<Integer> scc : sccs) {
            for (Integer node : scc) {
                for (int neighbors : transpose.outNeighbors(node)) {
                    if (!(scc.contains(neighbors))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}