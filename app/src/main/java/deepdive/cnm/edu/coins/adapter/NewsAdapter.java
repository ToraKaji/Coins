package deepdive.cnm.edu.coins.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import deepdive.cnm.edu.coins.R;
import java.util.List;

/**
 * Creates an adapter for the News RecyclerView
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

  private List<String> mData;
  private LayoutInflater mInflater;
  private ItemClickListener mClickListener;

  /**
   * Instantiates a new News adapter.
   *
   * @param context the context
   * @param data the data
   */
// data is passed into the constructor
  public
  NewsAdapter(Context context, List<String> data) {
    this.mInflater = LayoutInflater.from(context);
    this.mData = data;
  }

  // inflates the row layout from xml when needed
  @Override
  public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = mInflater.inflate(R.layout.list_item, parent, false);
    return new ViewHolder(view);
  }

  // binds the data to the TextView in each row
  @Override
  public void onBindViewHolder(ViewHolder holder, int position) {
    String animal = mData.get(position);
    holder.myTextView.setText(animal);
  }

  // total number of rows
  @Override
  public int getItemCount() {
    return mData.size();
  }


  /**
   * Creates a ViewHolder class
   */
// stores and recycles views as they are scrolled off screen
  public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    /**
     * Creates a TextView for generic use
     */
    TextView myTextView;

    /**
     * Instantiates a new View holder.
     *
     * @param itemView the item view
     */
    ViewHolder(View itemView) {
      super(itemView);
      myTextView = itemView.findViewById(R.id.play_id);
      itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
      if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());

    }
  }

  /**
   * Gets item.
   *
   * @param id the id
   * @return the item
   */
// convenience method for getting data at click position
  String getItem(int id) {
    return mData.get(id);
  }

  /**
   * Sets click listener.
   *
   * @param itemClickListener the item click listener
   */
// allows clicks events to be caught
  void setClickListener(ItemClickListener itemClickListener) {
    this.mClickListener = itemClickListener;
  }

  /**
   * The interface Item click listener.
   */
// parent activity will implement this method to respond to click events
  public interface ItemClickListener {

    /**
     * On item click.
     *
     * @param view the view
     * @param position the position
     */
    void onItemClick(View view, int position);
  }
}