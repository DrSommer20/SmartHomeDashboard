package mosbach.dhbw.de.smarthome.service;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfig {
    private static Dotenv dotenv = Dotenv.load();

    public static String getSmtpHost() {
        return dotenv.get("SMTP_HOST");
    }

    public static int getSmtpPort() {
        return Integer.parseInt(dotenv.get("SMTP_PORT"));
    }

    public static String getSmtpUsername() {
        return dotenv.get("SMTP_USERNAME");
    }

    public static String getSmtpPassword() {
        return dotenv.get("SMTP_PASSWORD");
    }

    public static String getAppName() {
        return dotenv.get("APP_NAME");
    }

    public static String getJwtSecret() {
        return dotenv.get("JWT_SECRET");
    }
}