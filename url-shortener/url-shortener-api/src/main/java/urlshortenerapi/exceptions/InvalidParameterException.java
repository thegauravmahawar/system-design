package urlshortenerapi.exceptions;

public class InvalidParameterException extends CustomException {
    public InvalidParameterException(String message, String code) {
        super(message, code);
    }
}
