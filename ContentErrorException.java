/*
 * The exception that is thrown if a user contains unnecessary/wrong/null data
 * */

public class ContentErrorException extends Exception {
    public ContentErrorException(String message) {
        super(message);
    }
}
