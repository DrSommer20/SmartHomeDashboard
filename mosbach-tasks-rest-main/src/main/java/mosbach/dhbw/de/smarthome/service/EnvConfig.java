package mosbach.dhbw.de.smarthome.service;


public class EnvConfig {

    public static String getSmtpHost() {
        return System.getenv("SMTP_HOST");
    }

    public static int getSmtpPort() {
        return Integer.parseInt(System.getenv("SMTP_PORT"));
    }

    public static String getSmtpUsername() {
        return System.getenv("SMTP_USERNAME");
    }

    public static String getSmtpPassword() {
        return System.getenv("SMTP_PASSWORD");
    }

    public static String getJwtSecret() {
        return System.getenv("JWT_SECRET");
    }
}