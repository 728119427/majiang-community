package co.mawen.majiangcommunity.exception;

public class CustomizeException extends RuntimeException {
    public CustomizeException() {
    }

    public CustomizeException(String message) {
        super(message);
    }

    public CustomizeException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomizeException(Throwable cause) {
        super(cause);
    }
}
