package dishwatcher.nl.interfaces;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;

public interface CognitoUserPoolControllerSignupHandler {
    void didSucceed(CognitoUser user, boolean userMustConfirmEmailAddress);
    void didFail(Exception exception);
}
