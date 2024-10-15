package mosbach.dhbw.de.smarthome.service.api;

public interface VerificationService {

    /**
     * Sends a verification email
     * 
     * @param to the email address
     * @param verificationLink the verification link
     */
    public void sendVerificationEmail(String to, String verificationLink);
}
