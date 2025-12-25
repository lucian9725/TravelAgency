package sda.academy.travelagency.Utils;

import sda.academy.travelagency.Dto.UserResponse;
import sda.academy.travelagency.Entity.Role;
import sda.academy.travelagency.Entity.User;

import java.util.stream.Collectors;

public class Convertor {

    public static UserResponse convertEntityToResponse(User user){
        UserResponse userResponse = new UserResponse();
        String[] name = user.getName().split(" ");
        userResponse.setFirstName(name[0]);
        userResponse.setLastName(name[1]);
        userResponse.setEmail(user.getEmail());
        userResponse.setRoles(user.getRoles().stream().map(Role::getName).collect(Collectors.toList()));
        return userResponse;
    }
}