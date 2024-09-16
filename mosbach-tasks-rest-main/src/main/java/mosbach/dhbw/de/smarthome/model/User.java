package mosbach.dhbw.de.smarthome.model;

public class User {
    private static int userIDCounter;
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String passwort;
    private String pat;


    public User(String firstName, String lastName, String email, String passwort) {
        this.userID = User.userIDCounter++;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.passwort = passwort;
        this.pat = "";
    }

    public User(String firstName, String lastName, String email, String passwort, String pat) {
        this(firstName, lastName, email, passwort);
        this.pat = pat;
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

    //Funnctions
    public boolean checkToken(){
        return pat != "";
    }
}
