package deepdive.cnm.edu.coins.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import deepdive.cnm.edu.coins.R;
import deepdive.cnm.edu.coins.activities.SignInActivity;
import deepdive.cnm.edu.coins.model.db.CoinsDB;
import deepdive.cnm.edu.coins.model.entities.User;
import java.util.Set;

/**
 * This is the main point at which all new users will pass through to understand the rules of the
 * game.
 */
public class WelcomeFragment extends Fragment {
  private Button letsGo;
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_welcome, container, false);
    TextView welcomeText = view.findViewById(R.id.welcome_text);
    welcomeText.setText("Hello, " + SignInActivity.getUser().getUserName() + "! \n Welcome to the Amazing world of Coins! The awesome penny popping game that everyone has come to love and understand"
        + " Here you will be trying to match three values "
        + "of the coins you pop. If you get three in a row you earn how ever much you see! On the main screen you will see a list of the games you've played and what you've "
        + "won on those games. I implore you to share this with friends and family and spread the love and joy of Coins!");
    letsGo = view.findViewById(R.id.lets_go);
    letsGo.setOnClickListener(v -> {
      new SetNewUser().execute();
     getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewsFragment()).commit();
    });
    return view;
  }
  private class SetNewUser extends AsyncTask<Void,Void, Void>{


    @Override
    protected Void doInBackground(Void... voids) {
      User user = CoinsDB.getInstance(getContext()).getUserDao().select(1L);
      user.setNewUser(false);
      CoinsDB.getInstance(getContext()).getUserDao().update(user);
      return null;
    }
  }
}
