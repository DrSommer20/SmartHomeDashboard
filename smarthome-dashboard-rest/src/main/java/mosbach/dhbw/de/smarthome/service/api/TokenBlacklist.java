package mosbach.dhbw.de.smarthome.service.api;

public interface TokenBlacklist {
    public void blacklistToken(String token);
    public boolean isTokenBlacklisted(String token);
}
