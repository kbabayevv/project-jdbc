package exception;

public class PostgresException extends RuntimeException {
    public PostgresException(String message) {
        super(message);
    }
}
