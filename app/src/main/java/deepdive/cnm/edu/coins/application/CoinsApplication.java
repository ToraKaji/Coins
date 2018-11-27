package deepdive.cnm.edu.coins.application;

import android.app.Application;
import com.facebook.stetho.Stetho;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

/**
 * This sets up GoogleSignIn and allows users to inspect there database with the use of FaceBooks
 * Stetho.
 */
public class CoinsApplication extends Application {

  private static CoinsApplication instance = null;

  private GoogleSignInClient client;
  private static GoogleSignInAccount account;

  @Override
  public void onCreate() {
    super.onCreate();
    instance = this;
    Stetho.initializeWithDefaults(this);
    GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .requestId()
        // Include requestIdToken if we're using Google Sign-in for authenticating on a back-end server.
        .build();
    client = GoogleSignIn.getClient(this, options);
  }

  /**
   * Gets the current instance of the CoinsApplication
   *
   * @return the instance
   */
  public static CoinsApplication getInstance() {
    return instance;
  }

  /**
   * Gets the current client of GoogleSignIn
   *
   * @return the client
   */
  public GoogleSignInClient getClient() {
    return client;
  }

  /**
   * Sets the client of GoogleSignIn
   *
   * @param client the client
   */
  public void setClient(GoogleSignInClient client) {
    this.client = client;
  }

  /**
   * Gets the current account associated with GoogleSignIn
   *
   * @return the account
   */
  public GoogleSignInAccount getAccount() {
    return account;
  }

  /**
   * Sets the Current account associated with GoogleSignIn
   *
   * @param account the account
   */
  public void setAccount(GoogleSignInAccount account) {
    this.account = account;
  }

}
