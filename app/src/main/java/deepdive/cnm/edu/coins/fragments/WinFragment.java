package deepdive.cnm.edu.coins.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import deepdive.cnm.edu.coins.R;
import deepdive.cnm.edu.coins.activities.MainActivity;
import deepdive.cnm.edu.coins.activities.SignInActivity;
import deepdive.cnm.edu.coins.model.db.CoinsDB;
import deepdive.cnm.edu.coins.model.entities.User;

/**
 * This is the fragment which users go to upon successful matching of three coins in a row.
 */
public class WinFragment extends Fragment {

  /**
   * The Value won.
   */
  int valueWon;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    valueWon = getArguments().getInt("VALUE_WON");
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_win, container, false);

    TextView valueWon = view.findViewById(R.id.value_won);
    valueWon.append(String.valueOf(this.valueWon));

    Button collect = view.findViewById(R.id.collect);
    collect.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, new PennyPopper()).commit();
        new GetUser().execute(1L);
      }
    });
    return view;
  }

  private class InsertCoins extends AsyncTask<User,Void, Integer>{

    @Override
    protected Integer doInBackground(User... users) {
      return CoinsDB.getInstance(getContext()).getUserDao().update(users[0]);
    }
  }
  private class GetUser extends AsyncTask<Long,Void,User>{

    @Override
    protected void onPostExecute(User user) {
      super.onPostExecute(user);
      user.setCoins(user.getCoins()+valueWon);
      new InsertCoins().execute(user);
    }

    @Override
    protected User doInBackground(Long... longs) {
      return CoinsDB.getInstance(getContext()).getUserDao().select(longs[0]);
    }
  }
}
