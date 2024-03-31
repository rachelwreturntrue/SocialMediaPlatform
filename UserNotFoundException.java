/*
* The exception that is thrown if a user is not found
* */

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String message) {
        super(message);
    }
}
