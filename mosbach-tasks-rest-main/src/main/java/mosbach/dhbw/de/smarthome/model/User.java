package mosbach.dhbw.de.smarthome.model;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String passwort;

    
    private static PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public User(String firstName, String lastName, String email, String passwort) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwort = passwordEncoder.encode(passwort);
    }

    public boolean checkPasswort(String pw){
        return passwordEncoder.matches(pw, this.passwort);
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
    public String getPasswort() {
        return passwort;
    }
    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    
}
