package ir.maktabsharif.userprofile.exception;

public class UsernameOrPasswordIncorrectException extends RuntimeException{
    public UsernameOrPasswordIncorrectException(String message) {
        super(message);
    }
}
