package sda.academy.travelagency.Service.service;



import sda.academy.travelagency.Dto.RegisterRequest;
import sda.academy.travelagency.Dto.UserResponse;
import sda.academy.travelagency.Entity.User;

import java.util.List;

public interface UserService {
    void saveUser(RegisterRequest userDto);

    void saveAdmin(RegisterRequest userDto);
    UserResponse getUser(Long id);

    User findByEmail(String email);
    List<UserResponse> findAllUsers();

    void deleteUser(Long id);
}