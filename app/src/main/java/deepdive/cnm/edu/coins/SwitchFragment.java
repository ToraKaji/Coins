package deepdive.cnm.edu.coins;

import android.arch.persistence.room.Update;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import deepdive.cnm.edu.coins.R;
import deepdive.cnm.edu.coins.model.db.CoinsDB;
import deepdive.cnm.edu.coins.model.entities.User;

/**
 * Convenience class for switching between fragments
 */
public class SwitchFragment extends AppCompatActivity{

  /**
   * Switch fragment.
   *
   * @param fragment the fragment
   * @param useStack the use stack
   * @param variant the variant
   */
  public void switchFragment(Fragment fragment, boolean useStack, String variant) {
    FragmentManager manager = getSupportFragmentManager();
    String tag = fragment.getClass().getSimpleName() + ((variant != null) ? variant : "");
    if (manager.findFragmentByTag(tag) != null) {
      manager.popBackStackImmediate(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
    FragmentTransaction transaction = manager.beginTransaction();
    transaction.replace(R.id.fragment_container, fragment, tag);
    if (useStack) {
      transaction.addToBackStack(tag);
    }
    transaction.commit();
  }

}
