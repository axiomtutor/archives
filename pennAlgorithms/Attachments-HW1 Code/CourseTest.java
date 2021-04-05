import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Test;

public class CourseTest {

    @Test(timeout = 1000)
    public void testWillAcceptStudent() {
        // We test that someone on the preference list gets in an empty course, and 
        // someone not on the preference list does not.
        Course cis502 = new Course("CIS 502", 2);
        
        LinkedList<Course> stevenList = new LinkedList<>();
        stevenList.add(cis502);
        Student steven = new Student("Steven", stevenList);

        LinkedList<Student> cis502List = new LinkedList<>();
        cis502List.add(steven);
        cis502.setPreferences(cis502List);
        
        LinkedList<Course> maxList = new LinkedList<>();
        maxList.add(cis502);
        Student max = new Student("Max", maxList);
         
        cis502.setPreferences(cis502List);
        
        assert (cis502.willAcceptStudent(steven));
        assert (!cis502.willAcceptStudent(max));
        
        //Testing empty preference list
        LinkedList<Student> cis5xxList = new LinkedList<>();
        Course cis5xx = new Course("CIS 5xx", 100, cis5xxList); 
        //Code coverage
        cis5xx.setCapacity(50);
 
        assert (!(cis5xx.willAcceptStudent(steven)));
        
        //List willing to kick out third most for second most 
        //and an even lower
        Course cis6xx = new Course("CIS 6xx", 2);
        LinkedList<Course> genList = new LinkedList<>();
        genList.add(cis6xx);
        Student a = new Student("a", genList);
        Student b = new Student("b", genList);
        Student c = new Student("c", genList);
        Student d = new Student("d", genList);
        Student e = new Student("e", genList);
        Student f = new Student("f", genList);
        LinkedList<Student> cis6xxList = new LinkedList<>();
        cis6xxList.add(a);
        cis6xxList.add(b);
        cis6xxList.add(c);
        cis6xxList.add(d);
        cis6xxList.add(e);
        cis6xxList.add(f); 
        cis6xx.setPreferences(cis6xxList);
        //Most preferred
        cis6xx.getAccepted().add(a); 
        //Third most preferred
        cis6xx.getAccepted().add(c);
        
        assert (cis6xx.willAcceptStudent(b));
        assert (!(cis6xx.willAcceptStudent(d)));
        assert (!(cis6xx.willAcceptStudent(e)));
        assert (!(cis6xx.willAcceptStudent(f)));
        
        //Code coverage
        assert (cis6xx.toString().contentEquals(cis6xx.toString()));
        assert (cis6xx.equals(cis6xx));
        assert (!cis6xx.equals(null));
        assert (!cis6xx.equals(cis5xx));
    }
    
    @Test(timeout = 1000)
    public void testAcceptStudent() {
        Course cis502 = new Course("CIS 502", 2);
        
        LinkedList<Course> aList = new LinkedList<>();
        LinkedList<Course> bList = new LinkedList<>();
        LinkedList<Course> cList = new LinkedList<>();
        LinkedList<Course> dList = new LinkedList<>();
        aList.add(cis502);
        bList.add(cis502);
        cList.add(cis502);
        dList.add(cis502);
        Student a = new Student("Student A", aList);
        Student b = new Student("Student B", bList);
        Student c = new Student("Student C", cList);
        Student d = new Student("Student D", dList);
        LinkedList<Student> list = new LinkedList<>();
        list.add(a);
        list.add(b);
        list.add(c);
        list.add(d);
        cis502.setPreferences(list);
        
        assertEquals(cis502.acceptStudent(b), null);
        assertEquals(cis502.acceptStudent(c), null);
        assert (!(cis502.willAcceptStudent(d)));
        assert (cis502.willAcceptStudent(a));
        assertEquals(cis502.acceptStudent(a), c);
    }
}
