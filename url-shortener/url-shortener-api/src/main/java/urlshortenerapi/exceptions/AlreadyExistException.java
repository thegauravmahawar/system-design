package urlshortenerapi.exceptions;

public class AlreadyExistException extends CustomException {
    public AlreadyExistException(String message, String code) {
        super(message, code);
    }
}
