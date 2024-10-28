package mosbach.dhbw.de.smarthome.graphql.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import mosbach.dhbw.de.smarthome.graphql.interceptor.AuthInterceptor;
import mosbach.dhbw.de.smarthome.model.Action;
import mosbach.dhbw.de.smarthome.model.ActionInput;
import mosbach.dhbw.de.smarthome.model.Routine;
import mosbach.dhbw.de.smarthome.model.User;
import mosbach.dhbw.de.smarthome.service.api.AuthService;

import mosbach.dhbw.de.smarthome.service.impl.RoutineClientService;

@Controller
public class RoutineController {

    @Autowired
    private RoutineClientService routineService;

    @Autowired
    private AuthService authService;

    @QueryMapping
    public Routine routineById(@Argument int id) {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        try {
            return routineService.getRoutineById(authService.generateToken(authenticatedUser), id, authenticatedUser.getId());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @QueryMapping
    public List<Routine> allRoutines() {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        try {
        return routineService.getAllRoutines(authService.generateToken(authenticatedUser), authenticatedUser.getId());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @MutationMapping
    public boolean createRoutine(@Argument String name, @Argument List<ActionInput> actions, @Argument String triggerTime) {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        Routine routine = new Routine();
        routine.setName(name);
        List<Action> newActions = new ArrayList<>();
        for(ActionInput actionInput : actions){
            Action action = new Action();
            action.setDeviceID(actionInput.getDeviceID());
            action.setSetTo(actionInput.getSetTo());
            newActions.add(action);
        }
        routine.setActions(newActions);
        routine.setTriggerTime(triggerTime);
        try {
            return routineService.createRoutine(authService.generateToken(authenticatedUser), routine);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @MutationMapping
    public Routine updateRoutine(@Argument int id, @Argument String name, @Argument List<ActionInput> actions, @Argument String triggerTime, @Argument Boolean state) {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        Routine routine = null;
        try {
            routine = routineService.getRoutineById(authService.generateToken(authenticatedUser),id, authenticatedUser.getId());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (routine == null) {
            throw new RuntimeException("Routine not found");
        }
        if (name != null) {
            routine.setName(name);
        }
        if (actions != null) {
            List<Action> newActions = new ArrayList<>();
            for(ActionInput actionInput : actions){
                Action action = new Action();
                action.setDeviceID(actionInput.getDeviceID());
                action.setSetTo(actionInput.getSetTo());
                newActions.add(action);
            }
            routine.setActions(newActions);
        }
        if (triggerTime != null) {
            routine.setTriggerTime(triggerTime);
        }
        try {
            routineService.updateRoutine(authService.generateToken(authenticatedUser), id, routine);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return routine;
    }

    @MutationMapping
    public Boolean switchRoutine(@Argument int id, @Argument Boolean state) {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        try {
            return routineService.switchRoutine(authService.generateToken(authenticatedUser), id, state ? "on" : "off");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @MutationMapping
    public Boolean deleteRoutine(@Argument int id) {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        try {
            System.out.println(authService.generateToken(authenticatedUser) + " ID: " + id);
            return routineService.deleteRoutine(authService.generateToken(authenticatedUser), id);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}