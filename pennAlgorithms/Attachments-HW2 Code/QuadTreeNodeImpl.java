// CIS 121, QuadTree

public class QuadTreeNodeImpl implements QuadTreeNode {
    
    //Store private objects: UL, UR, LL, LR
    QuadTreeNode ul,ur,ll,lr;
    
    //Want null color/size to distinguish between quadrants with 
    //non-homogeneous colors
    Integer color;
    Integer size;
    
    QuadTreeNodeImpl(Integer c, Integer s, QuadTreeNode ul, QuadTreeNode ur, 
            QuadTreeNode ll, QuadTreeNode lr) {
        color = c;
        size = s;
        this.ul = ul;
        this.ur = ur;
        this.ll = ll;
        this.lr = lr;
    }
    /**
     * ! Do not delete this method !
     * Please implement your logic inside this method without modifying the signature
     * of this method, or else your code won't compile.
     * <p/>
     * As always, if you want to create another method, make sure it is not public.
     *
     * @param image image to put into the tree
     * @return the newly build QuadTreeNode instance which stores the compressed image
     * @throws IllegalArgumentException if image is null
     * @throws IllegalArgumentException if image is empty
     * @throws IllegalArgumentException if image.length is not a power of 2
     * @throws IllegalArgumentException if image, the 2d-array, is not a perfect square
     */
    static QuadTreeNodeImpl builder(int[][] image, int i, int j, int size) {
        //If we recurse down to a size 1 block, then construct a size 1 homogeneous block
        if (size == 1) {
            QuadTreeNodeImpl qtn = new QuadTreeNodeImpl(image[j][i], 1, null, null, null, null);
            return qtn;
        }
        //UPPER LEFT RECURSION:
        QuadTreeNode ul0 = builder(image, i, j, size / 2);
        //UPPER RIGHT RECURSION:
        QuadTreeNode ur0 = builder(image, (i + size / 2), j, size / 2);
        //LOWER LEFT RECURSION:
        QuadTreeNode ll0 = builder(image, i, (j + size / 2), size / 2);
        //LOWER RIGHT RECURSION:
        QuadTreeNode lr0 = builder(image, (i + size / 2), (j + size / 2), size / 2);
        
        //Check whether all four are leaves with same color
        //if so, throw them away and replace with single leaf
        //of the same color
        if (ul0.isLeaf() && ur0.isLeaf() && ll0.isLeaf() && lr0.isLeaf()) {
            if (image[j][i] == image[j][i + size / 2] &&
                image[j][i + size / 2] == image[j + size / 2][i] &&
                image[j + size / 2][i] == image[j + size / 2][i + size / 2]) {
                //Make whole quadrant one number and remove separate quadrants (make them null)
                return new QuadTreeNodeImpl(image[j][i], size, null, null, null, null);
            }
        }
        return new QuadTreeNodeImpl(null, size, ul0, ur0, ll0, lr0);
    }

    public static QuadTreeNodeImpl buildFromIntArray(int[][] image) {
        if (image == null || image.length == 0 || image[0].length != image.length) {
            throw new IllegalArgumentException("Bad Image");
        }
        // Check for power of 2
        for (int i = 0; i < image.length - 1; i++) {
            if (image[i].length != image[i + 1].length) {
                throw new IllegalArgumentException("Bad Image");
            }
        }
        //Get values of quadrants
        return builder(image, 0, 0, image.length);
    }

    @Override
    public int getColor(int x, int y) {
        if ((x < 0) || (y < 0) || (x >= size) || (y >= size)) {
            throw new IllegalArgumentException("Out of Bounds"); 
        }
        if (color != null) {
            return color;
        }
        //Check if UL
        if (x < size / 2 && y < size / 2 && x >= 0 && y >= 0) {
            return ul.getColor(x, y);
        }
        //Check if UR
        if (x >= size / 2 && y < size / 2 && x < size && y >= 0) {
            //Column number needs to shrink by size/2
            return ur.getColor(x - size / 2, y);
        }
        //Check if LL
        if (x < size / 2 && y >= size / 2 && x >= 0 && y < size) {
          //Row number needs to shrink by size/2
            return ll.getColor(x, y - size / 2);
        }
        //Check if LR
        if (x >= size / 2 && y >= size / 2 && x < size && y < size) {
          //Both coordinates needs to shrink by size/2
            return lr.getColor(x - size / 2, y - size / 2);
        }
        return -1;
    }

    @Override
    public void setColor(int x, int y, int c) {
        if (x < 0 || y < 0 || x >= size || y >= size) {
            throw new IllegalArgumentException();
        }
        if (isLeaf()) {
            if (c == color) {
                return;
            } else {
                if (getDimension() == 1) {
                    color = c;
                } else {
                    //UPPER LEFT
                    if (x < size / 2 && y < size / 2) {
                        // Construct new node by removing its color and 
                        // giving it four children (the other quadrants)
                        ul = new QuadTreeNodeImpl(color, size / 2, null, null, null, null);
                        ur = new QuadTreeNodeImpl(color, size / 2, null, null, null, null);
                        ll = new QuadTreeNodeImpl(color, size / 2, null, null, null, null);
                        lr = new QuadTreeNodeImpl(color, size / 2, null, null, null, null);
                        ul.setColor(x, y, c);
                    }
                    //UPPER RIGHT
                    if (x >= size / 2 && y < size / 2) {
                        ul = new QuadTreeNodeImpl(color, size / 2, null, null, null, null);
                        ur = new QuadTreeNodeImpl(color, size / 2, null, null, null, null);
                        ll = new QuadTreeNodeImpl(color, size / 2, null, null, null, null);
                        lr = new QuadTreeNodeImpl(color, size / 2, null, null, null, null);
                        ur.setColor(x - size / 2, y, c);
                    }
                    //LOWER LEFT
                    if (x < size / 2 && y >= size / 2) {
                        ul = new QuadTreeNodeImpl(color, size / 2, null, null, null, null);
                        ur = new QuadTreeNodeImpl(color, size / 2, null, null, null, null);
                        ll = new QuadTreeNodeImpl(color, size / 2, null, null, null, null);
                        lr = new QuadTreeNodeImpl(color, size / 2, null, null, null, null);
                        ll.setColor(x, y - size / 2, c);
                    }
                    //LOWER RIGHT
                    if (x >= size / 2 && y >= size / 2) {
                        ul = new QuadTreeNodeImpl(color, size / 2, null, null, null, null);
                        ur = new QuadTreeNodeImpl(color, size / 2, null, null, null, null);
                        ll = new QuadTreeNodeImpl(color, size / 2, null, null, null, null);
                        lr = new QuadTreeNodeImpl(color, size / 2, null, null, null, null);
                        lr.setColor(x - size / 2, y - size / 2, c);
                    }
                    // Set color to null because once you make a leaf
                    // a new color, then the quadrant it occupies will
                    // no longer be a leaf
                    color = null;
                }
            }
        } else {
            //UPPER LEFT
            if (x < size / 2 && y < size / 2) {
                ul.setColor(x, y, c);
            }
            //UPPER RIGHT
            if (x >= size / 2 && y < size / 2) {
                ur.setColor(x - size / 2, y, c);
            }
            //LOWER LEFT
            if (x < size / 2 && y >= size / 2) {
                ll.setColor(x, y - size / 2, c);
            }
            //LOWER RIGHT
            if (x >= size / 2 && y >= size / 2) {
                lr.setColor(x - size / 2, y - size / 2, c);
            }
            //After setting, check all the colors that result
            //and if they are all leaves/leaves of same color, merge them
            if (ul.isLeaf() && ur.isLeaf() && ll.isLeaf() && lr.isLeaf()) {
                if (ul.getColor(0, 0) == ur.getColor(0, 0) &&
                    ur.getColor(0, 0) == ll.getColor(0, 0) &&
                    ll.getColor(0, 0) == lr.getColor(0, 0)) {
                    color = c;
                    ul = null;
                    ur = null;
                    ll = null;
                    lr = null;
                }
            }
        }
    }

    @Override
    public QuadTreeNode getQuadrant(QuadName quadrant) {
        if (quadrant == QuadTreeNode.QuadName.TOP_LEFT) {
            return ul;
        }
        if (quadrant == QuadTreeNode.QuadName.TOP_RIGHT) {
            return ur;
        }
        if (quadrant == QuadTreeNode.QuadName.BOTTOM_LEFT) {
            return ll;
        }
        if (quadrant == QuadTreeNode.QuadName.BOTTOM_RIGHT) {
            return lr;
        }
        return null;
    }

    @Override
    public int getDimension() {
        return size;
    }

    @Override
    public int getSize() {
        //Number of nodes in graph
        if (ul == null) {
            return 1;
        }
        return 1 + ul.getSize() + ur.getSize() + ll.getSize() + lr.getSize();
    }

    @Override
    public boolean isLeaf() {
        return (color != null);
    }

    void inject(QuadTreeNode node, int[][] matrix, int x, int y) {
        int s = node.getDimension();
        if (node.isLeaf()) {
            int c = getColor(x, y);
            for (int i = 0; i < s; i++) {
                for (int j = 0; j < s; j++) {
                    // Fill matrix with leaf color in region where leaf corresponds
                    matrix[y + i][x + j] = c;
                }
            }
        } else {
            inject(node.getQuadrant(QuadTreeNode.QuadName.TOP_LEFT), matrix, x, y);
            inject(node.getQuadrant(QuadTreeNode.QuadName.TOP_RIGHT), matrix, x + s / 2, y);
            inject(node.getQuadrant(QuadTreeNode.QuadName.BOTTOM_LEFT), matrix, x, y + s / 2);
            inject(node.getQuadrant(QuadTreeNode.QuadName.BOTTOM_RIGHT), matrix, x + s / 2, y 
                    + s / 2);
        }
    }
    
    @Override
    public int[][] decompress() {
        int[][] newArray = new int[size][size];
        if (isLeaf()) {
            for (int i = 0; i < newArray.length; i++) {
                for (int j = 0; j < newArray.length; j++) {
                    newArray[i][j] = color;
                }
            }
        } else {
            // Inject each quadrant into the big newArray matrix
            inject(ul, newArray, 0, 0);
            inject(ur, newArray, size / 2, 0);
            inject(ll, newArray, 0, size / 2);
            inject(lr, newArray, size / 2, size / 2);
        }
        return newArray;
    }

    @Override
    public double getCompressionRatio() {
        return (1.0 * getSize() / (size * size));
    }
}
