package deepdive.cnm.edu.coins.activities;

import android.arch.persistence.room.PrimaryKey;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;
import deepdive.cnm.edu.coins.R;
import deepdive.cnm.edu.coins.SwitchFragment;
import deepdive.cnm.edu.coins.application.CoinsApplication;
import deepdive.cnm.edu.coins.fragments.NewsFragment;
import deepdive.cnm.edu.coins.fragments.PennyPopper;
import deepdive.cnm.edu.coins.fragments.WelcomeFragment;
import deepdive.cnm.edu.coins.model.db.CoinsDB;
import deepdive.cnm.edu.coins.model.entities.User;
import java.nio.InvalidMarkException;
import java.security.PrivateKey;

/**
 * Creates the MainActivities class for use within the application
 */
public class MainActivity extends SwitchFragment
    implements NavigationView.OnNavigationItemSelectedListener {

  /**
   * Sets a global field for the toolbar in MainActivity
   */
  Toolbar toolbar;
  /**
   * Sets a global field for the drawer in MainActivity
   */
  DrawerLayout drawer;
  /**
   * Sets a global field for the drawer in MainActivity.
   */
  ActionBarDrawerToggle toggle;
  /**
   * Sets a global field for the navigation view in MainActivity
   */
  NavigationView navigationView;
  /**
   * Sets a global field for the User's name in MainActivity
   */
  TextView userName;
  /**
   * Sets a global field for the User's email in MainActivity
   */
  TextView userEmail;
  /**
   * Sets a global field for the User's email image in MainActivity
   */
  CircleImageView userEmailImage;
  /**
   * Allows for user of userCoins text outside of the MainActivity
   */
  public static TextView userCoins;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setItemIconTintList(null);
    setSupportActionBar(toolbar);
    toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();
    navigationView.setNavigationItemSelectedListener(this);
    new CheckForNewUser().execute();
    View headerView = navigationView.getHeaderView(0);
    userCoins = headerView.findViewById(R.id.coins);
    new UpdateCoins(userCoins,getApplicationContext()).execute();

    userName = headerView.findViewById(R.id.user_name);
    userName.setText(SignInActivity.account.getDisplayName());
    userEmail = headerView.findViewById(R.id.user_email);
    userEmail.setText(SignInActivity.account.getEmail());
    userEmailImage = headerView.findViewById(R.id.user_email_image);

    Picasso.get().load(SignInActivity.account.getPhotoUrl()).into(userEmailImage);
  }

  @Override
  public void onBackPressed() {
  drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.sign_out) {
      GoogleSignInClient googleSignInClient = CoinsApplication.getInstance().getClient();
      Intent intent = new Intent(this, SignInActivity.class);

      googleSignInClient.signOut()
          .addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
              Toast.makeText(MainActivity.this, "Sign out successful!", Toast.LENGTH_SHORT).show();
              startActivity(intent);
            }
          });
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    switch (item.getItemId()){
      case R.id.penny_popper:
        switchFragment(new PennyPopper(),true,"");
    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }


  /**
   * Creates a class of UpdateCoins which will be displayed on the nav_drawer
   */
  public static class UpdateCoins extends AsyncTask<User,Void,User> {
    private TextView textView = userCoins;
    private Context context;

    /**
     * Constructor for UpdateCoins
     *
     * @param textView the text view
     * @param context the context
     */
    public UpdateCoins(TextView textView, Context context){
      this.textView = textView;
      this.context = context;
    }

    @Override
    protected void onPostExecute(User user) {
      super.onPostExecute(user);
      textView.setText("Coins: " + String.valueOf(user.getCoins()));
    }

    @Override
    protected User doInBackground(User... users) {
      return CoinsDB.getInstance(context).getUserDao().select(1L);
    }
  }
  private class CheckForNewUser extends AsyncTask<Void,Void,User>{

    @Override
    protected void onPostExecute(User user) {
      super.onPostExecute(user);
      if(user.isNewUser()){
          switchFragment(new WelcomeFragment(),false, "");
        }else {
        switchFragment(new NewsFragment(), false, "");
      }
    }

    @Override
    protected User doInBackground(Void... voids) {
      return CoinsDB.getInstance(getApplicationContext()).getUserDao().select(1);
    }
  }
}
