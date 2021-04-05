import java.util.LinkedList;
import java.util.List;

final public class MazeSolver {
    private MazeSolver() {}

    /**
     * Returns a list of coordinates on the shortest path from {@code src} to {@code tgt}
     * by executing a breadth-first search on the graph represented by a 2D-array of size m x n.
     * Please note, you MUST use your ResizingDeque implementation as the BFS queue for this method.
     * <p>
     * Input {@code maze} guaranteed to be a non-empty and valid matrix.
     * Input {@code src} and {@code tgt} are guaranteed to be valid, in-bounds, and not blocked.
     * <p>
     * Do NOT modify this method header.
     *
     * @param maze the input maze, which is a 2D-array of size m x n
     * @param src The starting Coordinate of the path on the matrix
     * @param tgt The target Coordinate of the path on the matrix
     * @return an empty list if there is no path from {@param src} to {@param tgt}, otherwise an
     * ordered list of vertices in the shortest path from {@param src} to {@param tgt},
     * with the first element being {@param src} and the last element being {@param tgt}.
     * @implSpec This method should run in worst-case O(m x n) time.
     */
    
    static List<Coordinate> tgtToSrc(int[][] breadcrumb, Coordinate src, Coordinate tgt) {
        List<Coordinate> path = new LinkedList<Coordinate>();
        path.add(0, tgt);
        while (!path.get(0).equals(src)) {
            Coordinate first = path.get(0);
            int bcFirst = breadcrumb[first.getY()][first.getX()];
            if (bcFirst == 2) {
                path.add(0, new Coordinate(first.getX() + 1, first.getY()));
            } else if (bcFirst == 3) {
                path.add(0, new Coordinate(first.getX(), first.getY() + 1));
            } else if (bcFirst == 4) {
                path.add(0, new Coordinate(first.getX() - 1, first.getY()));
            } else if (bcFirst == 5) {
                path.add(0, new Coordinate(first.getX(), first.getY() - 1));
            }
        }
        return path;
    }
    
    public static List<Coordinate> getShortestPath(int[][] maze, Coordinate src, Coordinate tgt) {
        // 2 means go right, 3 mean go up, 4 means go left, 5 means go down
        int[][] breadcrumb = new int[maze.length][maze[0].length];
        // Populate whole matrix with -1 except the source which is -2
        for (int i = 0; i < breadcrumb.length; i++) {
            for (int j = 0; j < breadcrumb[0].length; j++) {
                breadcrumb[i][j] = -1;
            }
        }
        breadcrumb[src.getY()][src.getX()] = -2;
        // Initialize deque and perform BFS
        ResizingDeque<Coordinate> deque = new ResizingDequeImpl<Coordinate>();
        deque.addLast(src);
        if (src.equals(tgt)) {
            return new LinkedList<Coordinate>();
        }
        while (deque.size() != 0) {
            Coordinate c = deque.pollFirst();
            if (c.getY() < maze.length - 1) {
                int isBlocked = maze[c.getY() + 1][c.getX()];
                if (isBlocked == 0) {
                    Integer bc = breadcrumb[c.getY() + 1][c.getX()];
                    if (bc == -1) {
                        Coordinate open = new Coordinate(c.getX(), c.getY() + 1);
                        // Indicate direction to go back to get to source
                        breadcrumb[c.getY() + 1][c.getX()] = 5;
                        if (open.equals(tgt)) { 
                            return tgtToSrc(breadcrumb, src, tgt);
                        }
                        deque.addLast(open);
                    }
                }
            }
            if (c.getX() < maze[0].length - 1) {
                int isBlocked = maze[c.getY()][c.getX() + 1];
                if (isBlocked == 0) {
                    Integer bc = breadcrumb[c.getY()][c.getX() + 1];
                    if (bc == -1) {
                        Coordinate open = new Coordinate(c.getX() + 1, c.getY());
                        breadcrumb[c.getY()][c.getX() + 1] = 4;
                        if (open.equals(tgt)) {
                            return tgtToSrc(breadcrumb, src, tgt);
                        }
                        deque.addLast(open);
                    }
                }
            }
            if (c.getY() > 0) {
                int isBlocked = maze[c.getY() - 1][c.getX()];
                if (isBlocked == 0) {
                    Integer bc = breadcrumb[c.getY() - 1][c.getX()];
                    if (bc == -1) {
                        Coordinate open = new Coordinate(c.getX(), c.getY() - 1);
                        breadcrumb[c.getY() - 1][c.getX()] = 3;
                        if (open.equals(tgt)) {
                            return tgtToSrc(breadcrumb, src, tgt);
                        }
                        deque.addLast(open);
                    }
                }
            }
            if (c.getX() > 0) {
                int isBlocked = maze[c.getY()][c.getX() - 1];
                if (isBlocked == 0) {
                    Integer bc = breadcrumb[c.getY()][c.getX() - 1];
                    if (bc == -1) {
                        Coordinate open = new Coordinate(c.getX() - 1, c.getY());
                        breadcrumb[c.getY()][c.getX() - 1] = 2;
                        if (open.equals(tgt)) {
                            return tgtToSrc(breadcrumb, src, tgt);
                        }
                        deque.addLast(open);
                    }
                }
            }
        }
        return new LinkedList<Coordinate>();
    }
}