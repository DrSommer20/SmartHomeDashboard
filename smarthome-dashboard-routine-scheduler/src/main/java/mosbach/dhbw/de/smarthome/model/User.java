package mosbach.dhbw.de.smarthome.model;


public class User {
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String pat;
    private boolean isVerified;

    /**
     * Constructor
     * @param firstName
     * @param lastName
     * @param email
     * @param password
     */
    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.pat = "";
        this.isVerified = false;
    }

    public User(String firstName, String lastName, String email, String password, String pat) {
        this(firstName, lastName, email, password);
        this.pat = pat;
    }

    public User() {
    }

    // Getter and Setter
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPasswordWithoutEncode(String password) {
        this.password = password;
    }

    public String getPat() {
        return pat;
    }

    public void setPat(String pat) {
        this.pat = pat;
    }    

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }

    //Functions
    /**
     * Check if the user is verified
     * 
     * @return true if the user is verified, false otherwise
     */
    public boolean checkToken(){
        return !pat.isEmpty();
    }

    /**
     * Returns a string representation of the User object.
     *
     * @return a string representation of the User object
     */
    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", username='" + firstName + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isVerified=" + isVerified +
                ", pat='" + pat + '\'' +
                '}';
    }
}
