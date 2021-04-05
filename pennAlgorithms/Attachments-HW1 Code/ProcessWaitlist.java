import java.util.Iterator;
import java.util.LinkedList;

public class ProcessWaitlist {

    /**
     * Explanation and algorithm for this method is in the write-up. Make sure to read that in
     * its entirety.
     * 
     * Be sure to make use of the willAcceptStudent(student) and acceptStudent(student) methods you
     * implemented in Course.java.
     * 
     * Note: on execution, the courses LinkedList's accepted list should be populated correctly.
     * This is what we will be testing.
     *
     * Hint: you are allowed to modify the Student objects in the students list. See the comment
     * in Student.java for the getPreferences() method. You may find that doing so helps with
     * book-keeping in the makeAssignments() method. 
     * 
     * @param students list of students to be matched
     * @param courses list of courses to be matched
     *
     * You may assume that the students and courses lists are both non-null and that there 
     * are no null students nor null courses in either of the lists. 
     *
     * Note, students and courses may remain unmatched at the end of the algorithm. You should
     * not throw any exceptions in either case.
     */
    public static void makeAssignments(LinkedList<Student> students, LinkedList<Course> courses) {
        LinkedList<Student> freeStudents = new LinkedList<Student>();
        //Iterate through free students and copy students into it
        Iterator<Student> i = students.iterator();
        while (i.hasNext()) {
            freeStudents.add(i.next()); 
        }
        //While free students is non-empty
        while (!(freeStudents.isEmpty())) {
            //Grab random person from the free students
            Student s = freeStudents.remove();
            //Mutate preference list of student s to keep track of his proposed courses
            //As student makes proposals, also remove those classes from the preference lists
            //so that the first in the preference list is the first that the students has
            //yet to propose to
            if (!(s.getPreferences().isEmpty())) {
                Course firstPreference = s.getPreferences().removeFirst();
                //s wants to propose to firstPreference
                if (firstPreference.willAcceptStudent(s)) {
                    //If no student kicked out, store in variable as null;
                    //If student kicked out, then store in same variable
                    Student kickedOut = firstPreference.acceptStudent(s);
                    if (kickedOut != null) {
                        freeStudents.add(kickedOut);
                    }
                } else {
                    //if the course won't accept s, then put s back into the free student list
                    freeStudents.add(s);
                }
            }
        }
    }

}
