package ninja.poepoe.library;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

/**
 * Created by Poe Poe on 13/12/16.
 */

public class RowHeaderAdapter extends RecyclerView.Adapter {
  private PanelAdapter stackAdapter;
  private int width = 0;

  public RowHeaderAdapter(PanelAdapter stackAdapter, int width) {
    this.stackAdapter = stackAdapter;
    this.width = width;
  }

  @Override public int getItemViewType(int position) {
    return stackAdapter.getItemViewType(position + 1, 0);
  }

  @Override public int getItemCount() {
    return stackAdapter.getRowCount() - 1;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return stackAdapter.onCreateViewHolder(parent, viewType);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    try {
      stackAdapter.onBindViewHolder(holder, position + 1, 0);
      if (width != 0) {
        holder.itemView.setLayoutParams(
            new RecyclerView.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));
      }
    } catch (IndexOutOfBoundsException e) {
      Log.d("RowHeaderAdapter", "index " + position);
    }
  }
}
