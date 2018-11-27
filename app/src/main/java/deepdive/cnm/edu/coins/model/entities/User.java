package deepdive.cnm.edu.coins.model.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import java.util.Date;

/**
 * The type User.
 */
@Entity
public class User {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "user_id")
  private long userId;

  @ColumnInfo(name = "new_user")
  private boolean newUser;

  @ColumnInfo(name = "play_id")
  private long playId;

  @ColumnInfo(name = "user_name")
  private String userName;

  @ColumnInfo(name = "time_played")
  private Date timePlayed;

  /**
   * Gets play id.
   *
   * @return the play id
   */
  public long getPlayId() {
    return playId;
  }

  /**
   * Is new user boolean.
   *
   * @return the boolean
   */
  public boolean isNewUser() {
    return newUser;
  }

  /**
   * Sets new user.
   *
   * @param newUser the new user
   */
  public void setNewUser(boolean newUser) {
    this.newUser = newUser;
  }

  /**
   * Sets play id.
   *
   * @param playId the play id
   */
  public void setPlayId(long playId) {
    this.playId = playId;
  }

  /**
   * Gets time played.
   *
   * @return the time played
   */
  public Date getTimePlayed() {
    return timePlayed;
  }

  /**
   * Sets time played.
   *
   * @param timePlayed the time played
   */
  public void setTimePlayed(Date timePlayed) {
    this.timePlayed = timePlayed;
  }

  private long coins;

  /**
   * Gets coins.
   *
   * @return the coins
   */
  public long getCoins() {
    return coins;
  }

  /**
   * Sets coins.
   *
   * @param coins the coins
   */
  public void setCoins(long coins) {
    this.coins = coins;
  }

  /**
   * Gets user name.
   *
   * @return the user name
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Sets user name.
   *
   * @param userName the user name
   */
  public void setUserName(String userName) {
    this.userName = userName;
  }

  /**
   * Gets user id.
   *
   * @return the user id
   */
  public long getUserId() {
    return userId;
  }

  /**
   * Sets user id.
   *
   * @param userId the user id
   */
  public void setUserId(long userId) {
    this.userId = userId;
  }
}
