package dishwatcher.nl.interfaces;

public interface CognitoIdentityControllerGenericHandler {
    void didSucceed();
    void didFail(Exception exception);
}
