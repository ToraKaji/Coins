package deepdive.cnm.edu.coins.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import deepdive.cnm.edu.coins.R;
import deepdive.cnm.edu.coins.adapter.NewsAdapter;
import deepdive.cnm.edu.coins.application.CoinsApplication;
import deepdive.cnm.edu.coins.model.db.CoinsDB;
import deepdive.cnm.edu.coins.model.entities.Play;
import java.util.ArrayList;
import java.util.List;


/**
 * Creates a class in which the plays the user has done is displayed. Also known as the "Main Screen"
 */
public class NewsFragment extends Fragment {


  /**
   * The Adapter.
   */
  RecyclerView.Adapter adapter;
  /**
   * The Recycler view.
   */
  RecyclerView recyclerView;
  /**
   * The Layout manager.
   */
  RecyclerView.LayoutManager layoutManager;
  /**
   * Holds all of the play instances to be displayed on the recyclerview
   */
  List<String> info = new ArrayList<>();


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_news, container, false);
    Toast.makeText(getContext(), "Welcome back, " + CoinsApplication.getInstance().getAccount().getDisplayName() + "!", Toast.LENGTH_SHORT).show();

    recyclerView = view.findViewById(R.id.recycler_news);

      // use this setting to improve performance if you know that changes
      // in content do not change the layout size of the RecyclerView
      recyclerView.setHasFixedSize(true);

      // use a linear layout manager
      layoutManager = new LinearLayoutManager(getContext());
      recyclerView.setLayoutManager(layoutManager);
      new CheckPlays().execute();
      // specify an adapter (see also next example)



    return view;
  }

private class CheckPlays extends AsyncTask<Void,Void,List<Play>>{

  @Override
  protected void onPostExecute(List<Play> plays) {

    super.onPostExecute(plays);
    clear();
    for(Play play : plays){
      String playInfo = ("Play Number: " + play.getPlayId() + "\nPlay Outcome: " + (play.isOutcome() ? "win" : "loss") + "\n" +"Amount Won: " + play.getAmountWon() + "\n");
      info.add(playInfo);
    }
    adapter = new NewsAdapter(getContext(),info);
    recyclerView.setAdapter(adapter);

    adapter.notifyDataSetChanged();
  }

  @Override
  protected List<Play> doInBackground(Void... voids) {
    return CoinsDB.getInstance(getContext()).getPlayDao().query1();
  }
}

  /**
   * Clears the recyclerview of all children to allow space for more plays
   */
  public void clear() {
    final int size = info.size();
    if (size > 0) {
      for (int i = 0; i < size; i++) {
        info.remove(0);
      }

      adapter.notifyItemRangeRemoved(0, size);
    }
  }
}
