package mosbach.dhbw.de.smarthome.graphql.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import mosbach.dhbw.de.smarthome.graphql.interceptor.AuthInterceptor;
import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.service.api.UserService;

@Controller
public class UserController {

    
    @Autowired
    private UserService userService;

    @QueryMapping
    public User userInfo() {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        return authenticatedUser;
    }

    @MutationMapping
    public User updateUser(@Argument String firstName, @Argument String lastName, @Argument String email, @Argument String pat, @Argument Boolean isVerified) {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        
        if (firstName != null) {
            authenticatedUser.setFirstName(firstName);
        }
        if (lastName != null) {
            authenticatedUser.setLastName(lastName);
        }
        if (email != null) {
            authenticatedUser.setEmail(email);
        }
        if (pat != null) {
            authenticatedUser.setPat(pat);
        }
        if (isVerified != null) {
            authenticatedUser.setVerified(isVerified);
        }
        userService.updateUser(authenticatedUser);
        return authenticatedUser;
    }
    
}
