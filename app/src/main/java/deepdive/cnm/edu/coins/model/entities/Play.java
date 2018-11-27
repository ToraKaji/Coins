package deepdive.cnm.edu.coins.model.entities;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

/**
 * The type Play.
 */
@Entity(
    indices = @Index(value = "play_id",unique = true))

public class Play {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "play_id")
  private long playId;
  @ColumnInfo(name = "ammount_won")
  private long amountWon;

  /**
   * Gets amount won.
   *
   * @return the amount won
   */
  public long getAmountWon() {
    return amountWon;
  }

  /**
   * Sets amount won.
   *
   * @param amountWon the amount won
   */
  public void setAmountWon(long amountWon) {
    this.amountWon = amountWon;
  }

  private boolean outcome;

  /**
   * Is outcome boolean.
   *
   * @return the boolean
   */
  public boolean isOutcome() {
    return outcome;
  }

  /**
   * Sets outcome.
   *
   * @param outcome the outcome
   */
  public void setOutcome(boolean outcome) {
    this.outcome = outcome;
  }

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
}
