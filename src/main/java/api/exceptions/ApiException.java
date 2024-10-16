package api.exceptions;

public class ApiException extends Exception {

    public ApiException(int httpStatus) {
        super(String.format("Request failed with HTTP Status %d", httpStatus));
    }

    public ApiException(String message) {
        super(message);
    }
}