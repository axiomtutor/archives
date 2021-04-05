import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TrieMapTest {

    @Test
    public void putAndGetTest() {
        TrieMap<Integer> tm = new TrieMap<Integer>();
        tm.put("abc", 1);
        assertEquals((int) tm.get("abc"), 1);
        tm.put("ab", 2);
        assertEquals((int) tm.get("ab"), 2);
        assertEquals(tm.get("b"), null);
        assertEquals(tm.get("a"), null);
        assertEquals(tm.get("abcd"), null);
        tm.put("abcd", 3);
        assertEquals((int) tm.get("abcd"), 3);
        assertTrue(tm.put("abcd", 4) == 3);
        assertEquals((int) tm.get("abcd"), 4);
        assertEquals(tm.size(), 3);
        assertTrue(tm.containsKey("ab"));
        assertTrue(!(tm.containsKey("abbncwbekjdbwk")));
        assertTrue(tm.containsValue(1));
        assertTrue(tm.containsValue(2));
        assertTrue(tm.containsValue(4));
        assertTrue(!(tm.containsValue(3)));
    }
    
    @Test
    public void removeTest() {
        TrieMap<Integer> tm = new TrieMap<Integer>();
        tm.put("", 1);
        assertTrue(tm.containsKey(""));
        assertEquals((int) tm.remove(""), 1);
        tm.put("abc", 1);
        tm.put("ab", 2);
        tm.put("abcd", 3);
        assertEquals((int) tm.remove("ab"), 2);
        assertEquals(tm.size(), 2);
        assertEquals((int) tm.remove("abcd"), 3);
        assertEquals(tm.size(), 1);
        assertEquals((int) tm.remove("abc"), 1);
        assertEquals(tm.size(), 0);
        assertTrue(!(tm.containsKey("ab")));
        assertTrue(!(tm.containsKey("abc")));
        assertTrue(!(tm.containsKey("abcd")));
        assertEquals(tm.remove("abc"), null);
        tm.put("a", 1);
        tm.put("b", 2);
        tm.put("c", 3);
        assertEquals(tm.remove("d"), null);
        assertEquals((int) tm.remove("a"), 1);
        assertEquals((int) tm.remove("b"), 2);
        assertEquals((int) tm.remove("c"), 3);
        assertEquals(tm.remove("d"), null);
        tm.put("aaaaaa", 1);
        tm.put("abb", 2);
        tm.put("abc", 3);
        assertEquals((int) tm.remove("abc"), 3);
        assertEquals(tm.size(), 2);
        assertEquals((int) tm.get("abb"),  2);
        assertEquals((int) tm.get("aaaaaa"),  1);
        assertEquals(tm.get("abc"), null);
        assertTrue(!(tm.containsValue(3)));
        tm.clear();
        assertTrue(!(tm.containsValue(1)));
        assertTrue(!(tm.containsValue(2)));
        assertEquals(tm.size(), 0);
        assertEquals(tm.get("aaaaaa"), null);
        assertEquals(tm.get("abb"), null);
        assertEquals(tm.get("abc"), null);
    }
    
    @Test 
    public void innerRemoveTest() {
        TrieMap<Integer> tm = new TrieMap<Integer>();
        tm.put("abc", 1);
        assertEquals(tm.remove("ab"), null);
        assertEquals(tm.size(), 1);
    }

}
