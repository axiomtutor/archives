import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import java.util.Iterator;
import org.junit.Test;
import java.util.LinkedList;

public class ProcessWaitlistTest {

    // Writing test cases for makeAssignments is quite tedious, so we're helping
    // you out by providing you with some test cases we will use to grade it.
    // Feel free to add more test cases, but you won't be submitting this file.

    // NOTE: makeAssignments writeup example is testMakeAssignmentsWriteupExample.

    ////////////////////////////////
    // makeAssignments test cases //
    ////////////////////////////////
    
 
    @Test(timeout = 1000)
    public void testMakeAssignmentsOneStudentOneCourse() {
        Course cis502 = new Course("CIS 502", 2);

        LinkedList<Course> stevenList = new LinkedList<>();
        stevenList.add(cis502);
        Student steven = new Student("Steven", stevenList);

        LinkedList<Student> cis502List = new LinkedList<>();
        cis502List.add(steven);
        cis502.setPreferences(cis502List);

        LinkedList<Student> studentsToMatch = new LinkedList<>();
        studentsToMatch.add(steven);
        LinkedList<Course> coursesToMatch = new LinkedList<>();
        coursesToMatch.add(cis502);

        // Run the algorithm.
        ProcessWaitlist.makeAssignments(studentsToMatch, coursesToMatch);

        // Check manually for correct pairings.
        Iterator<Student> cis502Iter = cis502.getAccepted().iterator();
        assertEquals(steven, cis502Iter.next());
        assertFalse(cis502Iter.hasNext());
    }
     
    
    @Test(timeout = 1000)
    public void testMakeAssignmentsNotEnoughCapacityOneCourse() {
        Course cis502 = new Course("CIS 502", 2);

        LinkedList<Course> stevenList = new LinkedList<>();
        stevenList.add(cis502);
        Student steven = new Student("Steven", stevenList);
        
        LinkedList<Course> johnList = new LinkedList<>();
        johnList.add(cis502);
        Student john = new Student("John", johnList);

        LinkedList<Course> tommyList = new LinkedList<>();
        tommyList.add(cis502);
        Student tommy = new Student("Tommy", tommyList);

        LinkedList<Student> cis502List = new LinkedList<>();
        cis502List.add(tommy);
        cis502List.add(john);
        cis502List.add(steven);
        cis502.setPreferences(cis502List);

        LinkedList<Student> studentsToMatch = new LinkedList<>();
        studentsToMatch.add(steven);
        studentsToMatch.add(john);
        studentsToMatch.add(tommy);

        LinkedList<Course> coursesToMatch = new LinkedList<>();
        coursesToMatch.add(cis502);

        // Run the algorithm.
        ProcessWaitlist.makeAssignments(studentsToMatch, coursesToMatch);

        // Check manually for correct pairings.
        Iterator<Student> cis502Iter = cis502.getAccepted().iterator();
        assertEquals(tommy, cis502Iter.next());
        assertEquals(john, cis502Iter.next());
        assertFalse(cis502Iter.hasNext());
    }
 
    

    @Test(timeout = 1000)
    public void testMakeAssignmentsWriteupExample() {
        Course cis502 = new Course("CIS 502", 2);
        Course cis520 = new Course("CIS 520", 2);
        Course cis521 = new Course("CIS 521", 2);

        LinkedList<Course> samList = new LinkedList<>();
        samList.add(cis520);
        Student sam = new Student("Sam", samList);

        LinkedList<Course> briannaList = new LinkedList<>();
        briannaList.add(cis520);
        briannaList.add(cis502);
        Student brianna = new Student("Brianna", briannaList);

        LinkedList<Course> stevenList = new LinkedList<>();
        stevenList.add(cis520);
        stevenList.add(cis521);
        stevenList.add(cis502);
        Student steven = new Student("Steven", stevenList);

        LinkedList<Course> johnList = new LinkedList<>();
        johnList.add(cis502);
        johnList.add(cis520);
        johnList.add(cis521);
        Student john = new Student("John", johnList);

        LinkedList<Course> tommyList = new LinkedList<>();
        tommyList.add(cis520);
        tommyList.add(cis502);
        tommyList.add(cis521);
        Student tommy = new Student("Tommy", tommyList);

        LinkedList<Student> cis502List = new LinkedList<>();
        cis502List.add(brianna);
        cis502List.add(steven);
        cis502.setPreferences(cis502List);

        LinkedList<Student> cis520List = new LinkedList<>();
        cis520List.add(tommy);
        cis520List.add(sam);
        cis520List.add(brianna);
        cis520List.add(john);
        cis520List.add(steven);
        cis520.setPreferences(cis520List);

        LinkedList<Student> cis521List = new LinkedList<>();
        cis521List.add(tommy);
        cis521List.add(sam);
        cis521List.add(steven);
        cis521List.add(john);
        cis521.setPreferences(cis521List);

        LinkedList<Student> studentsToMatch = new LinkedList<>();
        studentsToMatch.add(sam);
        studentsToMatch.add(brianna);
        studentsToMatch.add(steven);
        studentsToMatch.add(john);
        studentsToMatch.add(tommy);

        LinkedList<Course> coursesToMatch = new LinkedList<>();
        coursesToMatch.add(cis502);
        coursesToMatch.add(cis520);
        coursesToMatch.add(cis521);

        // Run the algorithm.
        ProcessWaitlist.makeAssignments(studentsToMatch, coursesToMatch);

        // Check manually for correct pairings.
        Iterator<Student> cis502Iter = cis502.getAccepted().iterator();
        assertEquals(brianna, cis502Iter.next());
        assertFalse(cis502Iter.hasNext());
        
        Iterator<Student> cis520Iter = cis520.getAccepted().iterator();
        assertEquals(tommy, cis520Iter.next());
        assertEquals(sam, cis520Iter.next());
        assertFalse(cis520Iter.hasNext());

        Iterator<Student> cis521Iter = cis521.getAccepted().iterator();
        assertEquals(steven, cis521Iter.next());
        assertEquals(john, cis521Iter.next());
        assertFalse(cis521Iter.hasNext());
    }


    @Test(timeout = 1000)
    public void testMakeAssignmentsManySwaps() {
        Course cis501 = new Course("CIS 501", 1);
        Course cis502 = new Course("CIS 502", 1);
        Course cis519 = new Course("CIS 519", 1);
        Course cis520 = new Course("CIS 520", 1);
        Course cis521 = new Course("CIS 521", 1);

        LinkedList<Course> stevenList = new LinkedList<>();
        stevenList.add(cis501);
        stevenList.add(cis502);
        stevenList.add(cis519);
        stevenList.add(cis520);
        stevenList.add(cis521);
        Student steven = new Student("Steven", stevenList);
        
        LinkedList<Course> johnList = new LinkedList<>();
        johnList.add(cis502);
        johnList.add(cis519);
        johnList.add(cis520);
        johnList.add(cis501);
        johnList.add(cis521);
        Student john = new Student("John", johnList);
        
        LinkedList<Course> briannaList = new LinkedList<>();
        briannaList.add(cis519);
        briannaList.add(cis520);
        briannaList.add(cis501);
        briannaList.add(cis502);
        briannaList.add(cis521);
        Student brianna = new Student("Brianna", briannaList);
        
        LinkedList<Course> tommyList = new LinkedList<>();
        tommyList.add(cis520);
        tommyList.add(cis501);
        tommyList.add(cis502);
        tommyList.add(cis519);
        tommyList.add(cis521);
        Student tommy = new Student("Tommy", tommyList);
        
        LinkedList<Course> samList = new LinkedList<>();
        samList.add(cis501);
        samList.add(cis520);
        samList.add(cis502);
        samList.add(cis519);
        samList.add(cis521);
        Student sam = new Student("Sam", samList);
        
        LinkedList<Student> cis501List = new LinkedList<>();
        cis501List.add(sam);
        cis501List.add(steven);
        cis501List.add(john);
        cis501List.add(brianna);
        cis501List.add(tommy);
        cis501.setPreferences(cis501List);

        LinkedList<Student> cis502List = new LinkedList<>();
        cis502List.add(brianna);
        cis502List.add(john);
        cis502List.add(sam);
        cis502List.add(steven);
        cis502List.add(tommy);
        cis502.setPreferences(cis502List);
        
        LinkedList<Student> cis519List = new LinkedList<>();
        cis519List.add(tommy);
        cis519List.add(brianna);
        cis519List.add(sam);
        cis519List.add(steven);
        cis519List.add(john);
        cis519.setPreferences(cis519List);

        LinkedList<Student> cis520List = new LinkedList<>();
        cis520List.add(steven);
        cis520List.add(tommy);
        cis520List.add(sam);
        cis520List.add(john);
        cis520List.add(brianna);
        cis520.setPreferences(cis520List);

        LinkedList<Student> cis521List = new LinkedList<>();
        cis521List.add(sam);
        cis521List.add(steven);
        cis521List.add(brianna);
        cis521List.add(tommy);
        cis521List.add(john);
        cis521.setPreferences(cis521List);

        LinkedList<Student> studentsToMatch = new LinkedList<>();
        studentsToMatch.add(steven);
        studentsToMatch.add(john);
        studentsToMatch.add(brianna);
        studentsToMatch.add(tommy);
        studentsToMatch.add(sam);

        LinkedList<Course> coursesToMatch = new LinkedList<>();
        coursesToMatch.add(cis501);
        coursesToMatch.add(cis502);
        coursesToMatch.add(cis519);
        coursesToMatch.add(cis520);
        coursesToMatch.add(cis521);

        // Run the algorithm.
        ProcessWaitlist.makeAssignments(studentsToMatch, coursesToMatch);

        // Check manually for correct pairings.
        Iterator<Student> cis501Iter = cis501.getAccepted().iterator();
        assertEquals(sam, cis501Iter.next());
        assertFalse(cis501Iter.hasNext());
        
        Iterator<Student> cis502Iter = cis502.getAccepted().iterator();
        assertEquals(brianna, cis502Iter.next());
        assertFalse(cis502Iter.hasNext());
        
        Iterator<Student> cis519Iter = cis519.getAccepted().iterator();
        assertEquals(tommy, cis519Iter.next());
        assertFalse(cis519Iter.hasNext());
        
        Iterator<Student> cis520Iter = cis520.getAccepted().iterator();
        assertEquals(steven, cis520Iter.next());
        assertFalse(cis520Iter.hasNext());
        
        Iterator<Student> cis521Iter = cis521.getAccepted().iterator();
        assertEquals(john, cis521Iter.next());
        assertFalse(cis521Iter.hasNext());
    }
    
    @Test(timeout = 1000)
    public void testMakeAssignmentsStudentLeftUnmatched() {
        Course cis520 = new Course("CIS 520", 2);
        Course cis521 = new Course("CIS 521", 2);

        LinkedList<Course> stevenList = new LinkedList<>();
        stevenList.add(cis520);
        stevenList.add(cis521);
        Student steven = new Student("Steven", stevenList);
        
        LinkedList<Course> johnList = new LinkedList<>();
        johnList.add(cis520);
        johnList.add(cis521);
        Student john = new Student("John", johnList);
        
        LinkedList<Course> tommyList = new LinkedList<>();
        tommyList.add(cis520);
        tommyList.add(cis521);
        Student tommy = new Student("Tommy", tommyList);
        
        LinkedList<Student> cis520List = new LinkedList<>();
        cis520List.add(tommy);
        cis520List.add(john);
        cis520List.add(steven);
        cis520.setPreferences(cis520List);

        LinkedList<Student> cis521List = new LinkedList<>();
        cis521List.add(tommy);
        cis521List.add(john);
        cis521.setPreferences(cis521List);

        LinkedList<Student> studentsToMatch = new LinkedList<>();
        studentsToMatch.add(steven);
        studentsToMatch.add(john);
        studentsToMatch.add(tommy);

        LinkedList<Course> coursesToMatch = new LinkedList<>();
        coursesToMatch.add(cis520);
        coursesToMatch.add(cis521);

        // Run the algorithm.
        ProcessWaitlist.makeAssignments(studentsToMatch, coursesToMatch);

        // Check manually for correct pairings.
        // Steven should be left unmatched.
        Iterator<Student> cis520Iter = cis520.getAccepted().iterator();
        assertEquals(tommy, cis520Iter.next());
        assertEquals(john, cis520Iter.next());
        assertFalse(cis520Iter.hasNext());
        
        Iterator<Student> cis521Iter = cis521.getAccepted().iterator();
        assertFalse(cis521Iter.hasNext());
    }
    
    @Test(timeout = 1000)
    public void testMakeAssignmentsCourseLeftUnmatched() {
        Course cis520 = new Course("CIS 520", 3);
        Course cis521 = new Course("CIS 521", 3);

        LinkedList<Course> stevenList = new LinkedList<>();
        stevenList.add(cis520);
        stevenList.add(cis521);
        Student steven = new Student("Steven", stevenList);
        
        LinkedList<Course> johnList = new LinkedList<>();
        johnList.add(cis520);
        johnList.add(cis521);
        Student john = new Student("John", johnList);
        
        LinkedList<Course> tommyList = new LinkedList<>();
        tommyList.add(cis520);
        tommyList.add(cis521);
        Student tommy = new Student("Tommy", tommyList);
        
        LinkedList<Student> cis520List = new LinkedList<>();
        cis520List.add(tommy);
        cis520List.add(john);
        cis520List.add(steven);
        cis520.setPreferences(cis520List);

        LinkedList<Student> cis521List = new LinkedList<>();
        cis521List.add(tommy);
        cis521List.add(john);
        cis521List.add(steven);
        cis521.setPreferences(cis521List);

        LinkedList<Student> studentsToMatch = new LinkedList<>();
        studentsToMatch.add(steven);
        studentsToMatch.add(john);
        studentsToMatch.add(tommy);

        LinkedList<Course> coursesToMatch = new LinkedList<>();
        coursesToMatch.add(cis520);
        coursesToMatch.add(cis521);

        // Run the algorithm.
        ProcessWaitlist.makeAssignments(studentsToMatch, coursesToMatch);

        // Check manually for correct pairings.
        // CIS 521 should be left with no students.
        Iterator<Student> cis520Iter = cis520.getAccepted().iterator();
        assertEquals(tommy, cis520Iter.next());
        assertEquals(john, cis520Iter.next());
        assertEquals(steven, cis520Iter.next());
        assertFalse(cis520Iter.hasNext());
        
        Iterator<Student> cis521Iter = cis521.getAccepted().iterator();
        assertFalse(cis521Iter.hasNext());
    }


}
