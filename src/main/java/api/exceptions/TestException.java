package api.exceptions;

public class TestException extends Exception {

    public TestException(String message) {
        super(String.format("Test failed. %s", message));
    }
}