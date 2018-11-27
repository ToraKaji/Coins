package deepdive.cnm.edu.coins.model.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import deepdive.cnm.edu.coins.model.entities.User;
import java.util.List;

/**
 * The interface User dao.
 */
@Dao
public interface UserDao {

  /**
   * Select list.
   *
   * @return the list
   */
  @Query("SELECT * FROM User ORDER BY user_id")
  List<User> select();

  /**
   * Select user.
   *
   * @param userId the user id
   * @return the user
   */
  @Query("SELECT * FROM User WHERE user_id=:userId LIMIT 1")
  User select(long userId);

  /**
   * Insert long.
   *
   * @param user the user
   * @return the long
   */
  @Insert(onConflict = OnConflictStrategy.REPLACE)
  long insert(User user);

  /**
   * Update int.
   *
   * @param user the user
   * @return the int
   */
  @Update(onConflict = OnConflictStrategy.REPLACE)
  int update(User user);

}
