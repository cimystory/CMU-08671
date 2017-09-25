/**
 * Student class.
 * @author Xiaoyu Bai
 */
public class Student {
    /**
     * First name.
     */
    private String firstName;
    /**
     * Last name.
     */
    private String lastName;
    /**
     * Andrew ID.
     */
    private String andrewId;
    /**
     * Phone number.
     */
    private String phoneNumber;
    /**
     * Accecibility.
     */
    private boolean accessible;
    /**
     * Constructor.
     * @param newAndrewId set andrewId.
     */
    public Student(String newAndrewId) {
        this.andrewId = newAndrewId;
        this.firstName = null;
        this.lastName = null;
        this.phoneNumber = null;
        this.accessible = true;
    }
    /**
     * Constructor.
     * @param newAndrewId set adnrewID.
     * @param newFirstName set first name.
     * @param newLastName set last name.
     * @param newPhoneNumber set phone number.
     */
    public Student(String newAndrewId, String newFirstName, String newLastName, String newPhoneNumber) {
        this.andrewId = newAndrewId;
        this.firstName = newFirstName;
        this.lastName = newLastName;
        this.accessible = true;
        setPhoneNumber(newPhoneNumber);
    }
    /**
     * Get Andrew ID.
     * @return andrewId.
     */
    public String getAndrewId() {
        return andrewId;
    }
    /**
     * Get first name.
     * @return first name.
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * Get last name.
     * @return last name.
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * Get phone number.
     * @return phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }
    /**
     * Set first name.
     * @param s set first name.
     */
    public void setFirstName(String s) {
        if (accessible) {
            this.firstName = s;
        }
    }
    /**
     * Set last name.
     * @param s set last name.
     */
    public void setLastName(String s) {
        if (accessible) {
            this.lastName = s;
        }
    }
    /**
     * Set accessibility.
     * @param a value for accessibility.
     */
    public void setAccessible(boolean a) {
        this.accessible = a;
    }
    /**
     * Set phone number.
     * @param s set phone number.
     */
    public void setPhoneNumber(String s) {
        if (this.accessible) {
            if (s.length() == 10) {
                phoneNumber = s.substring(0, 3) + "-" + s.substring(3, 6) + "-" + s.substring(6, 10);
            } else {
                phoneNumber = s;
            }
        }
    }
    /**
     * Print out.
     * @return string.
     */
    @Override
    public String toString() {
        String phoneNumberStr =
                firstName + " " + lastName + " (Andrew ID: " + andrewId + ", Phone Number: " + phoneNumber + ")";
        return phoneNumberStr;
    }
}
