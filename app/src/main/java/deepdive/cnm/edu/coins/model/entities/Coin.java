package deepdive.cnm.edu.coins.model.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * The type Coin.
 */
@Entity(
    foreignKeys = {@ForeignKey(
        entity = Play.class,
        parentColumns = "play_id",
        childColumns = "play_id",
        onDelete = ForeignKey.CASCADE
    )},
    indices = @Index(value = "play_id")
)
public class Coin {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "coin_id")
  private long coinId;

  @ColumnInfo(name = "coin_number")
  private long coinNumber;

  @ColumnInfo(name = "play_id")
  private long playId;

  /**
   * Gets play id.
   *
   * @return the play id
   */
  public long getPlayId() {
    return playId;
  }

  /**
   * Sets play id.
   *
   * @param playId the play id
   */
  public void setPlayId(long playId) {
    this.playId = playId;
  }

  private long value;

  /**
   * Gets value.
   *
   * @return the value
   */
  public long getValue() {
    return value;
  }

  /**
   * Sets value.
   *
   * @param value the value
   */
  public void setValue(long value) {
    this.value = value;
  }

  /**
   * Gets coin id.
   *
   * @return the coin id
   */
  public long getCoinId() {
    return coinId;
  }

  /**
   * Sets coin id.
   *
   * @param coinId the coin id
   */
  public void setCoinId(long coinId) {
    this.coinId = coinId;
  }

  /**
   * Gets coin number.
   *
   * @return the coin number
   */
  public long getCoinNumber() {
    return coinNumber;
  }

  /**
   * Sets coin number.
   *
   * @param coinNumber the coin number
   */
  public void setCoinNumber(long coinNumber) {
    this.coinNumber = coinNumber;
  }
}
