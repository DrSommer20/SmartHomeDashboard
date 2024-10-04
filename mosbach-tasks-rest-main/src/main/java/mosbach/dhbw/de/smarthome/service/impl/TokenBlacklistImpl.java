package mosbach.dhbw.de.smarthome.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Service;

import mosbach.dhbw.de.smarthome.service.api.TokenBlacklist;

@Service
public class TokenBlacklistImpl implements TokenBlacklist{

    private Set<String> blacklist = new HashSet<>();

    public void blacklistToken(String token) {
        blacklist.add(token);
    }

    public boolean isTokenBlacklisted(String token) {
        return blacklist.contains(token);
    }  
}
