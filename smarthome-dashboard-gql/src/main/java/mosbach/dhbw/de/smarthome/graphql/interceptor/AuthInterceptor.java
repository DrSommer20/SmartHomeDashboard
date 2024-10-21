package mosbach.dhbw.de.smarthome.graphql.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import mosbach.dhbw.de.smarthome.service.api.AuthService;
import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.service.api.UserService;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    private static final ThreadLocal<User> authenticatedUser = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");  
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            String email = authService.extractEmail(token);
            User user = userService.getUserByEmail(email);
            authenticatedUser.set(user);
        }
        return true;
    }

    public static User getAuthenticatedUser() {
        return authenticatedUser.get();
    }

    public static void clear() {
        authenticatedUser.remove();
    }
}