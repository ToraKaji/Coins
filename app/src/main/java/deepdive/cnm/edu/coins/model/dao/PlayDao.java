package deepdive.cnm.edu.coins.model.dao;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import deepdive.cnm.edu.coins.model.entities.Play;
import java.util.List;

/**
 * The interface Play dao.
 */
@Dao
public interface PlayDao {

  /**
   * Insert long.
   *
   * @param play the play
   * @return the long
   */
  @Insert(onConflict = OnConflictStrategy.FAIL)
  long insert(Play play);

  /**
   * Query long.
   *
   * @return the long
   */
  @Query("SELECT * FROM Play ORDER BY play_id DESC LIMIT 1")
  long query();

  /**
   * Query 1 list.
   *
   * @return the list
   */
  @Query("SELECT * FROM Play ORDER BY play_id DESC LIMIT 10")
  List<Play> query1();

}
