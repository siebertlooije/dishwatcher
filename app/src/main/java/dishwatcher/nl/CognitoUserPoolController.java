package dishwatcher.nl;

import android.content.Context;

import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoDevice;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUser;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserAttributes;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserCodeDeliveryDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserPool;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.CognitoUserSession;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.AuthenticationDetails;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.ChallengeContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.continuations.MultiFactorAuthenticationContinuation;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.AuthenticationHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GenericHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.GetDetailsHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.SignUpHandler;
import com.amazonaws.mobileconnectors.cognitoidentityprovider.handlers.VerificationHandler;
import com.amazonaws.regions.Regions;

import dishwatcher.nl.interfaces.CognitoUserPoolControllerGenericHandler;
import dishwatcher.nl.interfaces.CognitoUserPoolControllerSignupHandler;
import dishwatcher.nl.interfaces.CognitoUserPoolControllerUserDetailsHandler;

public class CognitoUserPoolController {

	//TO DO: Insert your Cognito user pool settings here
    private String userPoolID = "eu-west-1_cVQuVUdSg";

    // within the Cognito user pool.
    private String appClientID = "4pns9kv22ai592vkfa9sqlffq8";
    private String appClientSecret = "1cj5dgg9eavgiqnr6d7daa2lavbbotat8d2ped6meabhkgn1jnib";

    private Regions region = Regions.EU_WEST_1;

    private CognitoUserPool userPool;
    private Context mContext;

    private static CognitoUserPoolController instance = null;
    private CognitoUserPoolController() {}

    public static CognitoUserPoolController getInstance(Context context) {
        if(instance == null) {
            instance = new CognitoUserPoolController();
        }

        instance.setupUserPool(context);
        return instance;
    }

    private void  setupUserPool(Context context) {
        if (userPool == null) {
            mContext = context;
            userPool = new CognitoUserPool(context, userPoolID, appClientID, appClientSecret,region);
            return;
        }

        if (mContext != context) {
            userPool = new CognitoUserPool(context, userPoolID, appClientID, appClientSecret,region);
        }
    }

    public void login(String username,
                      final String password,
                      final CognitoUserPoolControllerGenericHandler completion) {
        CognitoUser user = userPool.getUser(username);
        user.getSessionInBackground(new AuthenticationHandler() {

            @Override
            public void onSuccess(CognitoUserSession userSession, CognitoDevice newDevice) {
                completion.didSucceed();
            }

            @Override
            public void getAuthenticationDetails(AuthenticationContinuation authenticationContinuation, String UserId) {
                // The API needs user sign-in credentials to continue
                AuthenticationDetails authenticationDetails = new AuthenticationDetails(UserId, password, null);
                authenticationContinuation.setAuthenticationDetails(authenticationDetails);
                authenticationContinuation.continueTask();
            }

            @Override
            public void getMFACode(MultiFactorAuthenticationContinuation continuation) {
                // Multi-factor authentication is required; get the verification code from user
            }

            @Override
            public void authenticationChallenge(ChallengeContinuation continuation) {

            }

            @Override
            public void onFailure(Exception exception) {
                completion.didFail(exception);
            }
        });

    }

    public void signup(String username,
                       String password,
                       String emailAddress,
                       final CognitoUserPoolControllerSignupHandler completion) {

        CognitoUserAttributes userAttributes = new CognitoUserAttributes();
        userAttributes.addAttribute("email", emailAddress);

        userPool.signUpInBackground(username, password, userAttributes, null, new SignUpHandler() {
            @Override
            public void onSuccess(CognitoUser user, boolean signUpConfirmationState, CognitoUserCodeDeliveryDetails cognitoUserCodeDeliveryDetails) {
                completion.didSucceed(user, !signUpConfirmationState);
            }

            @Override
            public void onFailure(Exception exception) {
                completion.didFail(exception);
            }
        });
    }

    public void confirmSignup(CognitoUser user, String confirmationCode, final CognitoUserPoolControllerGenericHandler completion) {

        user.confirmSignUpInBackground(confirmationCode, false, new GenericHandler() {
            @Override
            public void onSuccess() {
                completion.didSucceed();
            }

            @Override
            public void onFailure(Exception exception) {
                completion.didFail(exception);
            }
        });
    }

    public void resendConfirmationCode(CognitoUser user, final CognitoUserPoolControllerGenericHandler completion) {
        user.resendConfirmationCodeInBackground(new VerificationHandler() {
            @Override
            public void onSuccess(CognitoUserCodeDeliveryDetails verificationCodeDeliveryMedium) {
                completion.didSucceed();
            }

            @Override
            public void onFailure(Exception exception) {
                completion.didFail(exception);
            }
        });
    }


    public CognitoUser getCurrentUser() {
        return userPool.getCurrentUser();
    }

    public void getUserDetails(CognitoUser user, final CognitoUserPoolControllerUserDetailsHandler completion) {
        user.getDetailsInBackground(new GetDetailsHandler() {
            @Override
            public void onSuccess(CognitoUserDetails cognitoUserDetails) {
                completion.didSucceed(cognitoUserDetails);
            }

            @Override
            public void onFailure(Exception exception) {
                completion.didFail(exception);
            }
        });
    }
}
