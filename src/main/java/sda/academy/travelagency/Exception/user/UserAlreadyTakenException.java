package sda.academy.travelagency.Exception.user;

public class UserAlreadyTakenException extends RuntimeException{
    public UserAlreadyTakenException(String message)
    {
        super(message);
    }
}