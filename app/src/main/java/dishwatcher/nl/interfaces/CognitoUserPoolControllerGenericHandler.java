package dishwatcher.nl.interfaces;

public interface CognitoUserPoolControllerGenericHandler {
    void didSucceed();
    void didFail(Exception exception);
}
