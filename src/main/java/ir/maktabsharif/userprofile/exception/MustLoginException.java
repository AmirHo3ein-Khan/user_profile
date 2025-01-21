package ir.maktabsharif.userprofile.exception;

public class MustLoginException extends RuntimeException{
    public MustLoginException(String message) {
        super(message);
    }
}
