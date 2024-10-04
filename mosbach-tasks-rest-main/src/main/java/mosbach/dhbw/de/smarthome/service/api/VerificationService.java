package mosbach.dhbw.de.smarthome.service.api;

public interface VerificationService {
    public void sendVerificationEmail(String to, String verificationLink);
}
