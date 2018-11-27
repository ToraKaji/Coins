package deepdive.cnm.edu.coins.model.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import deepdive.cnm.edu.coins.model.entities.Coin;
import java.util.List;

/**
 * The interface Coin dao.
 */
@Dao
public interface CoinDao {

  /**
   * Insert list.
   *
   * @param coin the coin
   * @return the list
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insert(List<Coin> coin);

  /**
   * Insert list.
   *
   * @param coin the coin
   * @return the list
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  List<Long> insert(Coin... coin);
}
