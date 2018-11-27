package deepdive.cnm.edu.coins.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import deepdive.cnm.edu.coins.R;
import deepdive.cnm.edu.coins.activities.MainActivity;
import deepdive.cnm.edu.coins.model.db.CoinsDB;
import deepdive.cnm.edu.coins.model.entities.User;


/**
 * Creates a LossFragment for when the user expends all chances to match three coins
 */
public class LossFragment extends Fragment {
private long valueLost;
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    valueLost = 18;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_loss, container, false);
    Button collect = view.findViewById(R.id.try_again);
    collect.setOnClickListener(v -> {
      new GetUser().execute(1L);
      getFragmentManager().beginTransaction().replace(R.id.fragment_container, new PennyPopper()).commit();
    });
    return view;
  }

  private class RemoveCoins extends AsyncTask<User,Void, Integer> {

    @Override
    protected Integer doInBackground(User... users) {
      return CoinsDB.getInstance(getContext()).getUserDao().update(users[0]);
    }
  }
  private class GetUser extends AsyncTask<Long,Void,User>{

    @Override
    protected void onPostExecute(User user) {
      super.onPostExecute(user);
      user.setCoins(user.getCoins()-valueLost);
      new LossFragment.RemoveCoins().execute(user);
    }

    @Override
    protected User doInBackground(Long... longs) {
      return CoinsDB.getInstance(getContext()).getUserDao().select(longs[0]);
    }
  }

}
