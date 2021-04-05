import java.util.LinkedList;

/**
 * Class implementing a Student object. DO NOT MODIFY anything in this file.
 * 
 * Read through and understand the behavior and methods exposed by this class.
 */
public class Student {
    private String name;
    // The student's preference, in order from most preferred to least preferred.
    private LinkedList<Course> preferences;

    /**
     * Creates a student with the specified name and preferences. You may assume name and
     * preferences are not null.
     * 
     * @param name the student's name
     * @param preferences the student's course selection preferences
     */
    public Student(String name, LinkedList<Course> preferences) {
        this.name = name;
        this.preferences = preferences;
    }

    /**
     * NOTE: this getter returns a pointer to the preferences field. This means
     * that if you modify the list returned by getPreferences, these modifications
     * will be reflected in the Student object. For example, suppose you have a 
     * Student s with a preference list of 1 -> 2 -> 3. If you call 
     * s.getPreferences().remove(2), then a subsequent call to s.getPreferences() 
     * will return 1 -> 3. 
     *
     * While in general you'd want to avoid this to maintain encapsulation, for this 
     * assignment you may find it useful to take advantage of this. Specifically, 
     * you may want to consider modifying the students' preferences fields as 
     * you are implementing the makeAssignments() method.
     */
    public LinkedList<Course> getPreferences() {
        return preferences;
    }

    public String getName() {
        return name;
    }

    /** 
     * Provided for convenience of debugging. It's often very helpful to override the toString()
     * method so that when a test case fails, instead of seeing Student@14f98e you see "Bob".
     */
    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return name.length() * 31;
    }

    /**
     * Two students are equal iff they have the same name.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Student)) {
            return false;
        }
        Student other = (Student) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

}
