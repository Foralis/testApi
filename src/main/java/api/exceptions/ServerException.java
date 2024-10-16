package api.exceptions;

public class ServerException extends Exception {

    public ServerException(String status) {
        super(String.format("Request failed with server Status %s", status));
    }
}