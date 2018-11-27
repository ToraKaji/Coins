package deepdive.cnm.edu.coins.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import deepdive.cnm.edu.coins.R;
import deepdive.cnm.edu.coins.application.CoinsApplication;
import deepdive.cnm.edu.coins.model.db.CoinsDB;
import deepdive.cnm.edu.coins.model.entities.User;
import java.util.Date;
import java.util.List;

/**
 * Creates a class SignInActivity
 */
public class SignInActivity extends Activity {

  private static User user;

  /**
   * Get the current instance of the user associated with the application
   *
   * @return the user
   */
  public static User getUser() {
    return user;
  }

  private static final int REQUEST_CODE = 1010;

  /**
   * Creates a sign in button field for use in establishing a user
   */
  SignInButton signIn;

  /**
   * Creates a GoogleSignInAccount for use outside of the class
   */
  public static GoogleSignInAccount account;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_sign_in);
    signIn = findViewById(R.id.sign_in_button);
    signIn.setOnClickListener((view) -> signin());
    user = new User();
  }

  @Override
  protected void onStart() {
    super.onStart();
    account = GoogleSignIn.getLastSignedInAccount(this);
    if (account != null) {
      CoinsApplication.getInstance().setAccount(account);
      switchToMain();
    }
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (requestCode == REQUEST_CODE) {
      try {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        account = task.getResult(ApiException.class);
        CoinsApplication.getInstance().setAccount(account);
      } catch (ApiException e) {
        //e.printStackTrace();
        Toast.makeText(this, "Error signing in.", Toast.LENGTH_LONG).show();
      }
      switchToMain();
    }
  }


  private void signin() {
    Intent intent = CoinsApplication.getInstance().getClient().getSignInIntent();
    startActivityForResult(intent, REQUEST_CODE);
  }

  private void switchToMain() {
    Intent intent = new Intent(this, MainActivity.class);
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);

    new AddUser().execute(user);
    startActivity(intent);
}

private class AddUser extends AsyncTask<User,Void, Long>{

  @Override
  protected void onPostExecute(Long aLong) {

  }

  @Override
  protected Long doInBackground(User... users) {
    if(CoinsDB.getInstance(getApplicationContext()).getUserDao().select(1)==null){
      user.setUserName(CoinsApplication.getInstance().getAccount().getDisplayName());
      user.setCoins(10000);
      user.setNewUser(true);
      Date date = new Date();
      user.setTimePlayed(date);
      return CoinsDB.getInstance(getApplicationContext()).getUserDao().insert(user);
    }
    else{
      return null;
    }
  }
}

}
