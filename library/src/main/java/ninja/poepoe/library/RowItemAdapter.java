package ninja.poepoe.library;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * Created by Poe Poe on 13/12/16.
 */

public class RowItemAdapter extends RecyclerView.Adapter {
  private PanelAdapter panelAdapter;
  private int row;

  public RowItemAdapter() {
  }

  public RowItemAdapter(PanelAdapter stackAdapter, int row) {
    this.panelAdapter = stackAdapter;
    this.row = row;
  }

  public void setData(PanelAdapter stackAdapter, int row) {
    this.panelAdapter = stackAdapter;
    this.row = row;
    notifyDataSetChanged();
  }

  public void setRow(int row) {
    this.row = row;
    notifyDataSetChanged();
  }

  @Override public int getItemViewType(int position) {
    if (panelAdapter == null) return -1;
    return panelAdapter.getItemViewType(row, position + 1);
  }

  @Override public int getItemCount() {
    if (panelAdapter == null) return 0;
    return panelAdapter.getColumnCount() - 1;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return panelAdapter.onCreateViewHolder(parent, viewType);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    panelAdapter.onBindViewHolder(holder, row, position + 1);
  }
}
