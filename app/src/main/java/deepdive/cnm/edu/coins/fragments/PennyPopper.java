package deepdive.cnm.edu.coins.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import de.hdodenhof.circleimageview.CircleImageView;
import deepdive.cnm.edu.coins.R;
import deepdive.cnm.edu.coins.activities.MainActivity;
import deepdive.cnm.edu.coins.model.db.CoinsDB;
import deepdive.cnm.edu.coins.model.entities.Coin;
import deepdive.cnm.edu.coins.model.entities.Play;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * The actual game fragment that is played upon
 */
public class PennyPopper extends Fragment {

  private static final int SIZE = 3;
  private static final int NUM_OF_COINS = SIZE * SIZE;


  /**
   * Creates a variable to keep track of how many coins have been clicked
   */
  int numClicked = 0;
  /**
   * Creates a list of coins that holds all coin objects
   */
  List<Coin> coin = new LinkedList<>();
  /**
   * Holds all the images of each coin
   */
  List<CircleImageView> coins = new ArrayList<>();
  /**
   * Holds all of the values of each coin
   */
  ArrayList<Integer> coinValues = new ArrayList<>();
  /**
   * Holds all of the values of each coin in respective order
   */
  int[] vals = new int[NUM_OF_COINS];
  /**
   * The View.
   */
  View view;
  /**
   * Creates a new play object for placement in the database.
   */
  Play play = new Play();

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    view = inflater.inflate(R.
        layout.fragment_penny_popper, container, false);
    initCoins();
    new MainActivity.UpdateCoins(MainActivity.userCoins,getContext()).execute();

    return view;

  }

  private void initCoins() {

    Random rng = new Random();
    final List<Integer> possible = new ArrayList<>();
    for (int i = 10; i <= 61; i += 10) {
      possible.add(i);
    }

    final int idCoins[] = {R.id.button0, R.id.button1, R.id.button2, R.id.button3,
        R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8};

    final int idCoinValues[] = {R.id.button0_val, R.id.button1_val, R.id.button2_val,
        R.id.button3_val, R.id.button4_val, R.id.button5_val, R.id.button6_val, R.id.button7_val,
        R.id.button8_val};

    for (int i = 0; i < idCoins.length; i++) {
      Coin coin = new Coin();
      CircleImageView coinImage = view.findViewById(idCoins[i]);
      TextView coinValue = view.findViewById(idCoinValues[i]);
      int coinValueInt = possible.get(rng.nextInt(possible.size()));

      coin.setValue(coinValueInt);
      this.coin.add(coin);

      coinValues.add(coinValueInt);
      coinValue.setText(String.valueOf(coinValueInt));
      coins.add(coinImage);
      int finalI = i;
      coins.get(i).setOnClickListener(new OnClickListener() {
        @Override
        public void onClick(View v) {
          coins.get(finalI).setVisibility(View.INVISIBLE);
          coinValue.setVisibility(View.VISIBLE);
          vals[finalI] = coinValueInt;
          ++numClicked;
          checkWin();

        }
      });
    }
  }

  private void checkWin() {
    boolean win = false;
    int valueWon = 0;
    //Checks for horizontal wins ( x==x+1&&x==x+2 == true linear horizontal win.)
    for (int x = 0; x < 7; x += SIZE) {
      if (vals[x] == vals[x + 1] && vals[x] == vals[x + 2] && vals[x] != 0) {
        valueWon = vals[x];
        win = true;
        break;
      }
    }

    //Check for vertical wins ( x==x+3&&x==x+6 == true linear vertical win.)
    for (int x = 0; x < SIZE; x += 1) {
      if (vals[x] == vals[x + 3] && vals[x] == vals[x + 6] && vals[x] != 0) {
        valueWon = vals[x];
        win = true;
        break;
      }
    }

    if (vals[0] == vals[4] && vals[0] == vals[8] && vals[0] != 0) {
      valueWon = vals[0];
      win = true;
    }

    if (vals[2] == vals[4] && vals[2] == vals[6] && vals[2] != 0) {
      valueWon = vals[2];
      win = true;
    }

    if (numClicked == NUM_OF_COINS && !win) {
      getFragmentManager().beginTransaction().replace(R.id.fragment_container, new LossFragment())
          .commit();
      play.setOutcome(false);
      play.setAmountWon(valueWon);
      new InsertPlay(play).execute(coin.toArray(new Coin[0]));

    }
    if (win) {

      WinFragment winFragment = new WinFragment();
      Bundle bundle = new Bundle();
      bundle.putInt("VALUE_WON", valueWon);
      winFragment.setArguments(bundle);
      play.setOutcome(true);
      play.setAmountWon(valueWon);
      new InsertPlay(play).execute(coin.toArray(new Coin[0]));

      getFragmentManager().beginTransaction().replace(R.id.fragment_container, winFragment)
          .commit();

    }
//TODO add loss case
  }


  private class InsertPlay extends AsyncTask<Coin, Void, Void> {

    private Play play;

    /**
     * Instantiates a new Insert play.
     *
     * @param play the play
     */
    public InsertPlay(Play play) {
      this.play = play;
    }

    @Override
    protected Void doInBackground(Coin... coins) {
      long playId = CoinsDB.getInstance(getContext()).getPlayDao().insert(play);
      for (Coin coin : coin) {
        coin.setPlayId(playId);
      }
      CoinsDB.getInstance(getContext()).getCoinDao().insert(coins);
      return null;
    }
  }

}
