package mosbach.dhbw.de.smarthome.graphql.controller;

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
import mosbach.dhbw.de.smarthome.service.api.RoutineService;

@Controller
public class RoutineController {

    @Autowired
    private RoutineService routineService;

    @QueryMapping
    public Routine routineById(@Argument String id) {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        return routineService.getRoutineByID(id, authenticatedUser);
    }

    @QueryMapping
    public List<Routine> allRoutines() {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        return routineService.getRoutines(authenticatedUser);
    }

    @MutationMapping
    public Routine createRoutine(@Argument String name, @Argument List<ActionInput> actions, @Argument String triggerTime) {
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
        routineService.addRoutine(authenticatedUser, routine);
        return routine;
    }

    @MutationMapping
    public Routine updateRoutine(@Argument String id, @Argument String name, @Argument List<ActionInput> actions, @Argument String triggerTime, @Argument Boolean state) {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        Routine routine = routineService.getRoutineByID(id, authenticatedUser);
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
        if (state != null) {
            routine.setState(state);
        }
        routineService.updateRoutine(routine, authenticatedUser.getId());
        return routine;
    }

    @MutationMapping
    public Boolean switchRoutine(@Argument String id, @Argument Boolean state) {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        return routineService.switchRoutine(id, state, authenticatedUser);
    }

    @MutationMapping
    public Boolean deleteRoutine(@Argument String id) {
        User authenticatedUser = AuthInterceptor.getAuthenticatedUser();
        if (authenticatedUser == null) {
            throw new RuntimeException("User not authenticated");
        }
        return routineService.deleteRoutine(id, authenticatedUser);
    }
}