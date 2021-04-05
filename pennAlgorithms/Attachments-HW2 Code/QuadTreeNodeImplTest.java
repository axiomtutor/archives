import static org.junit.Assert.*;

import org.junit.Test;

public class QuadTreeNodeImplTest {

    int[][] im1 = {{1, 2},
                   {3, 4}};
    
    int[][] im2 = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16},
    };
    
    int[][] im3 = {{100}};
    
    int[][] im4 = {
            {50, 50, 3, 4},
            {50, 50, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16},
    };
    
    int[][] im4WithMod = {
            {100, 50, 3, 4},
            {50, 50, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 16},
    };
    
    int[][] im5 = {
            {-1, -1, -1, -1},
            {-1, -1, -1, -1},
            {-1, -1, -1, -1},
            {-1, -1, -1, -1},
    };
    
    int[][] im5Sol = {
            {0,0,0,0},
            {0, 1, 2, 3},
            {0, 2, 4, 6},
            {0, 3, 6, 9},
    };
    
    @Test
    public void testSimpleOrigin() {
        QuadTreeNode map1 = QuadTreeNodeImpl.buildFromIntArray(im1);
        QuadTreeNode map2 = QuadTreeNodeImpl.buildFromIntArray(im3);
        assert (map1.getColor(0, 0) == 1);
        assert (map1.getColor(0, 1) == 3);
        assert (map1.getColor(1, 0) == 2);
        assert (map1.getColor(1, 1) == 4);
        assert (map2.getColor(0, 0) == 100);
    }

    @Test
    public void testBiggerOrigin() {
        QuadTreeNode map3 = QuadTreeNodeImpl.buildFromIntArray(im2);
        assert (map3.getColor(0, 0) == 1);
        /*assert (map3.getColor(0, 1) == 5);
        assert (map3.getColor(2, 3) == 15);
        assert (map3.getColor(3, 0) == 4);*/
    }

    @Test
    public void testHomogeneousRegion() {
        QuadTreeNode map4 = QuadTreeNodeImpl.buildFromIntArray(im4);
        assert (map4.getColor(1, 1) == 50);
    }
    
    @Test
    public void testGetSize() {
        QuadTreeNode map4 = QuadTreeNodeImpl.buildFromIntArray(im4);
        assert (map4.getSize() == 17);
    }
    
    @Test
    public void testGetQuadrant() {
        QuadTreeNode map4 = QuadTreeNodeImpl.buildFromIntArray(im4);
        assert (map4.getQuadrant(QuadTreeNode.QuadName.TOP_LEFT).getSize() == 1);
        //Node + 4 Leaves = 5
        assert (map4.getQuadrant(QuadTreeNode.QuadName.TOP_RIGHT).getSize() == 5);
    }
    
    @Test
    public void testGetDimension() {
        QuadTreeNode map4 = QuadTreeNodeImpl.buildFromIntArray(im4);
        assert (map4.getDimension() == 4);
        assert (map4.getQuadrant(QuadTreeNode.QuadName.TOP_RIGHT).getDimension() == 2);
    }
    
    @Test
    public void testCompressionRatio() {
        QuadTreeNode map1 = QuadTreeNodeImpl.buildFromIntArray(im1);
        assert (map1.getCompressionRatio() == 1.25);
    }
    
    @Test
    public void testSetColor() {
        QuadTreeNode map1 = QuadTreeNodeImpl.buildFromIntArray(im1);
        map1.setColor(0, 0, 100);
        map1.setColor(1, 0, 100);
        map1.setColor(0, 1, 100);
        assert (map1.getSize() == 5);
        map1.setColor(1, 1, 100);
        assert (map1.getSize() == 1);
        
        //Set Color, then decompress
        QuadTreeNode map4 = QuadTreeNodeImpl.buildFromIntArray(im4);
        map4.setColor(0, 0, 100);
        assertArrayEquals(im4WithMod, map4.decompress());
        
        // Call set color and every coordinate (for code coverage)
        QuadTreeNode map5 = QuadTreeNodeImpl.buildFromIntArray(im5);
        for (int i = im5.length - 1; i >= 0; i--) {
            for (int j = im5.length - 1; j >= 0; j--) {
                map5.setColor(i, j, i * j);
            } 
        }
        assertArrayEquals(im5Sol, map5.decompress());
        
        // More code coverage tests
        map5 = QuadTreeNodeImpl.buildFromIntArray(im5);
        map5.setColor(0, 3, 0);
        map5.setColor(0, 3, -1);
        assertArrayEquals(im5, map5.decompress());
        map5.setColor(3, 0, 0);
        map5.setColor(3, 0, -1);
        assertArrayEquals(im5, map5.decompress());
    }
    
    @Test
    public void testDecompress() {
        QuadTreeNode map4 = QuadTreeNodeImpl.buildFromIntArray(im4);
        QuadTreeNode map3 = QuadTreeNodeImpl.buildFromIntArray(im3);
        assertArrayEquals(im4, map4.decompress());
        assertArrayEquals(im3, map3.decompress());
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void getColorNegativeCoordSmall() {
        QuadTreeNode map4 = QuadTreeNodeImpl.buildFromIntArray(im4);
        map4.getColor(-1, 0); 
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void getColorNegativeCoordLarge() {
        QuadTreeNode map4 = QuadTreeNodeImpl.buildFromIntArray(im4);
        map4.getColor(-10, 999999);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void getColorNegativeCoordLeaf() {
        QuadTreeNode map5 = QuadTreeNodeImpl.buildFromIntArray(im5);
        map5.getColor(-1, 0); 
    }

}
