import java.util.LinkedList;

/**
 * Class implementing accept/will accept student behavior for a Course object. Only modify the
 * methods which have TODO comments, but be sure to read through the entire file!
 */
public class Course {
    // The course's code (e.g., "CIS 121").
    private String courseCode;
    // The course's preference, in order from most preferred to least preferred.
    private LinkedList<Student> preferences;
    // Capacity of the course, which is guaranteed to be > 0.
    private int capacity;
    // During the execution of the algorithm, this list maintains the temporary acceptances sorted
    // from most preferred, to least preferred. At the end of the execution, this list maintains
    // the final acceptances, sorted in order from most preferred to least preferred.
    private LinkedList<Student> accepted = new LinkedList<>();

    /**
     * Creates a course with the specified code. You may assume name is not null, and capacity is
     * positive.
     * 
     * @param courseCode the course's code (e.g., "CIS 121")
     * @param capacity the course's capacity, which is guaranteed to be > 0.
     * @param preferences the professor's preferences for which students he wants in the course
     */
    public Course(String courseCode, int capacity, LinkedList<Student> preferences) {
        this.courseCode = courseCode;
        this.capacity = capacity;
        this.preferences = preferences;
    }

    /* Additional constructors and setter/getter methods. */

    public Course(String courseCode, int capacity) {
        this.courseCode = courseCode;
        this.capacity = capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setPreferences(LinkedList<Student> preferences) {
        this.preferences = preferences;
    }

    public LinkedList<Student> getAccepted() {
        return accepted;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public LinkedList<Student> getPreferences() {
        return preferences;
    }

    /* NOTE: do not modify anything above this line. */

    /**
     * A course should temporarily accept the student if the student is in its preference list and:
     * 1) the course is not yet at full capacity, or 2) the course is at capacity but the student
     * is preferred to some other student on the accepted list.
     *
     * If the student is already in the accepted list, return false.
     *
     * You may assume the student input is non-null. 
     *
     * @return {@code true} if the course should temporarily accept student,
     *         {@code false} otherwise.
     */
    public boolean willAcceptStudent(Student student) {
        //Check whether student is in course preference list.
        //If he is, then, if the course is not yet full,
        //then return true; if the course is full, 
        //then check where the student ranks on that preference 
        //list compared to the others
        if (accepted.contains(student)) {
            return false;
        }
        if (preferences.contains(student)) {
            if (accepted.size() < capacity) { 
                return true; 
            } else {
                //Check whether the course prefers the last student 
                //in the accepted list over the current student
                Student lastInList = accepted.getLast();
                int indexOfLastInList = preferences.indexOf(lastInList);
                int indexOfCurrentStudent = preferences.indexOf(student);
                return indexOfLastInList > indexOfCurrentStudent;
            }
        }
        return false;
    }

    /**
     * Adds the student to the accepted list, and kicks out and returns the least preferred student
     * if necessary (i.e., course is at capacity), otherwise returns {@code null}.
     *
     * Note:
     * - If the student is already in the accepted list, do nothing and return null.
     * - If the student shouldn't be accepted, then do nothing and return null.
     * 
     * NOTE: you must maintain the invariant that the accepted list is ordered from most preferred
     * to least preferred. This will be the trickiest part -- be sure to only use the methods
     * available in LinkedList.java.
     *
     * You may assume the student input is non-null.
     * 
     * @return student the student who was kicked out, or {@code null} if no student was removed.
     */
    public void insertStudent(Student student) {
        boolean found = false;
        int studentIndex = preferences.indexOf(student);
        for (int i = 0; i < accepted.size(); i++) {
            Student accStudent = accepted.get(i);
            int indexOfAccStudent = preferences.indexOf(accStudent);
            if (indexOfAccStudent > studentIndex) {
                accepted.add(i, student);
                found = true;
                break;
            }
        }
        //if the student is not found throughout the for loop, 
        //then insert him at the end
        if (!found) { 
            accepted.add(student); 
        }
    }
    
    public Student acceptStudent(Student student) {
        if (willAcceptStudent(student)) {
            if (accepted.size() < capacity) {
                insertStudent(student);
            } else {
                insertStudent(student);
                return accepted.removeLast();
            }
        }
        return null;
    }

    /* NOTE: do not modify anything below this line. */

    /** 
     * Provided for convenience of debugging. It's often very helpful to override the toString()
     * method so that when a test case fails, instead of seeing Course@14f98e you see "CIS 121".
     */
    @Override
    public String toString() {
        return courseCode;
    }

    @Override
    public int hashCode() {
        return this.capacity * courseCode.length();
    }

    /**
     * Two courses are equal iff they have the same course code.
     * Don't worry about code coverage for this method.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Course)) {
            return false;
        }
        Course other = (Course) obj;
        if (courseCode == null) {
            if (other.courseCode != null) {
                return false;
            }
        } else if (!courseCode.equals(other.courseCode)) {
            return false;
        }
        return true; 
    }

}
