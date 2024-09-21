package mosbach.dhbw.de.smarthome.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class TokenBlacklist {

    private Set<String> blacklist = new HashSet<>();

    public void blacklistToken(String token) {
        blacklist.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklist.contains(token);
    }  
}
