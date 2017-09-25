/**
 * @author Xiaoyu Bai
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/**
 * Directory to manipulate Student.
 */
public class Directory {
    /**
     * Map from andrewId to Student.
     */
    private Map<String, Student> idMap;
    /**
     * Map from first name to Student List.
     */
    private Map<String, List<Student>> firstNameMap;
    /**
     * Map from last name to Student List.
     */
    private Map<String, List<Student>> lastNameMap;
    /**
     * Constructor.
     */
    public Directory() {
        idMap = new HashMap<String, Student>();
        firstNameMap = new HashMap<String, List<Student>>();
        lastNameMap = new HashMap<String, List<Student>>();
    }
    /**
     * Add new student.
     * @param s Student object to add.
     */
    public void addStudent(Student s) {
        if (s == null) {
            throw new IllegalArgumentException("Cannot add null.");
        } else {
            System.out.println("add: ID(" + s.getAndrewId() + ")");
        }
        if (idMap.containsKey(s.getAndrewId())) {
            throw new IllegalArgumentException("Student already exist.");
        } else {
            // idMap.
            idMap.put(s.getAndrewId(), s);
            // firstNameMap.
            if (firstNameMap.containsKey(s.getFirstName())) {
                firstNameMap.get(s.getFirstName()).add(s);
            } else {
                List<Student> temp1 = new ArrayList<Student>();
                temp1.add(s);
                firstNameMap.put(s.getFirstName(), temp1);
            }
            // lastNameMap.
            if (lastNameMap.containsKey(s.getLastName())) {
                lastNameMap.get(s.getLastName()).add(s);
            } else {
                List<Student> temp2 = new ArrayList<Student>();
                temp2.add(s);
                lastNameMap.put(s.getLastName(), temp2);
            }
        }
        s.setAccessible(false);
    }
    /**
     * Remove an existing student.
     * @param andrewId andrew Id of target Student.
     */
    public void deleteStudent(String andrewId) {
        if (andrewId == null) {
            throw new IllegalArgumentException("Cannot delete null.");
        } else {
            System.out.println("delete: ID(" + andrewId + ")");
        }
        if (!idMap.containsKey(andrewId)) {
            throw new IllegalArgumentException("Student does not exist.");
        } else {
            // Target student to delete.
            Student s = idMap.get(andrewId);
            // firstNameMap.
            if (firstNameMap.get(s.getFirstName()).size() == 1) {
                firstNameMap.remove(s.getFirstName());
            } else {
                for (int i = 0; i < firstNameMap.get(s.getFirstName()).size(); i++) {
                    if (andrewId.equals(firstNameMap.get(s.getFirstName()).get(i).getAndrewId())) {
                        firstNameMap.get(s.getFirstName()).remove(i);
                    }
                }
            }
            // lastNameMap.
            if (lastNameMap.get(s.getLastName()).size() == 1) {
                lastNameMap.remove(s.getLastName());
            } else {
                for (int i = 0; i < lastNameMap.get(s.getLastName()).size(); i++) {
                    if (andrewId.equals(lastNameMap.get(s.getLastName()).get(i).getAndrewId())) {
                        lastNameMap.get(s.getLastName()).remove(i);
                    }
                }
            }
            // idMap.
            idMap.remove(andrewId);
        }
    }
    /**
     * Search student by andrew Id.
     * @param andrewId to use as key.
     * @return result Student who matches andrewId.
     */
    public Student searchByAndrewId(String andrewId) {
        if (andrewId == null) {
            throw new IllegalArgumentException("Search value cannot be null.");
        } else {
            System.out.println("search by andrew id: " + andrewId);
        }
        if (!idMap.containsKey(andrewId)) {
            return null;
        } else {
            Student s = idMap.get(andrewId);
            Student result =
                    new Student(s.getAndrewId(), s.getFirstName(), s.getLastName(), s.getPhoneNumber());
            return result;
        }
    }
    /**
     * Search student by first name.
     * @param firstName to use as key.
     * @return result Student List with this first name.
     */
    public List<Student> searchByFirstName(String firstName) {
        if (firstName == null) {
            throw new IllegalArgumentException("Search value cannot be null.");
        } else {
            System.out.println("search by first name: " + firstName);
        }
        List<Student> result = new ArrayList<Student>(0);
        if (firstNameMap.containsKey(firstName)) {
            result = firstNameMap.get(firstName);
        }
        return result;
    }
    /**
     * Search student by last name.
     * @param lastName to use as key.
     * @return result Student List with this last name.
     */
    public List<Student> searchByLastName(String lastName) {
        if (lastName == null) {
            throw new IllegalArgumentException("Search value cannot be null.");
        } else {
            System.out.println("search by last name: " + lastName);
        }
        List<Student> result = new ArrayList<Student>(0);
        if (lastNameMap.containsKey(lastName)) {
            result = lastNameMap.get(lastName);
        }
        return result;
    }
    /**
     * Size of directory.
     * @return size of directory.
     */
    public int size() {
        return idMap.size();
    }
}
