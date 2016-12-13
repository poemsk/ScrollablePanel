package ninja.poepoe.library;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Poe Poe on 13/12/16.
 */

public class RowItemAdapter extends RecyclerView.Adapter {
  private PanelAdapter stackAdapter;
  private int row;
  private int width = 0;

  public RowItemAdapter(PanelAdapter stackAdapter, int row, int width) {
    this.stackAdapter = stackAdapter;
    this.row = row;
    this.width = width;
  }

  public RowItemAdapter(PanelAdapter stackAdapter, int row) {
    this.stackAdapter = stackAdapter;
    this.row = row;
  }

  public void setRow(int row) {
    this.row = row;
  }

  @Override public int getItemViewType(int position) {
    return stackAdapter.getItemViewType(row, position + 1);
  }

  @Override public int getItemCount() {
    return stackAdapter.getColumnCount() - 1;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return stackAdapter.onCreateViewHolder(parent, viewType);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    stackAdapter.onBindViewHolder(holder, row, position + 1);
    if (width != 0) {
      holder.itemView.setLayoutParams(
          new RecyclerView.LayoutParams(width, ViewGroup.LayoutParams.WRAP_CONTENT));
    }
  }
}
