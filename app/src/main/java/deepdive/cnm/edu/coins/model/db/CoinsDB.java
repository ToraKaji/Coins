package deepdive.cnm.edu.coins.model.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverter;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import deepdive.cnm.edu.coins.model.dao.PlayDao;
import deepdive.cnm.edu.coins.model.dao.CoinDao;
import deepdive.cnm.edu.coins.model.dao.UserDao;
import deepdive.cnm.edu.coins.model.db.CoinsDB.Converters;
import deepdive.cnm.edu.coins.model.entities.Coin;
import deepdive.cnm.edu.coins.model.entities.Play;
import deepdive.cnm.edu.coins.model.entities.User;
import java.util.Date;

/**
 * The type Coins db.
 */
@Database(
    entities = {Coin.class, Play.class, User.class},
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters.class)
public abstract class CoinsDB extends RoomDatabase {

  private static final String DB_NAME = "CoinsDB";

  private static CoinsDB instance = null;

  /**
   * Sets the instance of CoinsDB to null
   */
  public synchronized static void forgetInstance() {
    instance = null;
  }

  /**
   * Gets the instance of CoinsDB
   *
   * @param context the context
   * @return the instance
   */
  public synchronized static CoinsDB getInstance(Context context) {
    if (instance == null) {
      instance = Room.databaseBuilder(context.getApplicationContext(), CoinsDB.class, DB_NAME)
          .build();
    }
    return instance;
  }

  /**
   * Gets play dao.
   *
   * @return the play dao
   */
  public abstract PlayDao getPlayDao();

  /**
   * Gets user dao.
   *
   * @return the user dao
   */
  public abstract UserDao getUserDao();

  /**
   * Gets coin dao.
   *
   * @return the coin dao
   */
  public abstract CoinDao getCoinDao();

  /**
   * The type Converters.
   */
  public static class Converters {

    /**
     * Date from long date.
     *
     * @param time the time
     * @return the date
     */
    @TypeConverter
    public static Date dateFromLong(Long time) {
      return (time != null) ? new Date(time) : null;
    }

    /**
     * Long from date long.
     *
     * @param date the date
     * @return the long
     */
    @TypeConverter
    public static Long longFromDate(Date date) {
      return (date != null) ? date.getTime() : null;
    }
  }
}