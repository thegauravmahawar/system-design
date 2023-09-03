package urlshortenerapi.exceptions;

public class InvalidHeaderException extends CustomException {
    public InvalidHeaderException(String message, String code) {
        super(message, code);
    }
}
