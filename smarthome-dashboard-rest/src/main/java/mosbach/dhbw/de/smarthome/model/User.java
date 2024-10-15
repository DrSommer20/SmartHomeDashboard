package mosbach.dhbw.de.smarthome.model;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String pat;
    private boolean isVerified;
    private static PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

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
        this.password = passwordEncoder.encode(password);
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

    public void setPassword(String password) {
        this.password = passwordEncoder.encode(password);
    }

    public String getPassword() {
        return password;
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
     * Check if the password is correct
     * 
     * @param password the password to check
     * @return true if the password is correct, false otherwise
     */
    public boolean checkPassword(String password) {
        return passwordEncoder.matches(password, this.password);
    }
}
