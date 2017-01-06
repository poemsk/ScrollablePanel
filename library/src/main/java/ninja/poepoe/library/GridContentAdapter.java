package ninja.poepoe.library;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.HashSet;

/**
 * Created by Poe Poe on 13/12/16.
 */

public class GridContentAdapter extends RecyclerView.Adapter<GridContentAdapter.ViewHolder> {

  private PanelAdapter stackAdapter;
  private HashSet<RecyclerView> observerList = new HashSet<>();

  public GridContentAdapter(PanelAdapter stackAdapter, RecyclerView headerRecyclerView) {
    this.stackAdapter = stackAdapter;
    initRecyclerView(headerRecyclerView);
  }

  public void setPanelAdapter(PanelAdapter panelAdapter) {
    this.stackAdapter = panelAdapter;
  }

  @Override public long getItemId(int position) {
    return position;
  }

  @Override public int getItemCount() {
    return stackAdapter.getRowCount() - 1;
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    ViewHolder viewHolder = new ViewHolder(new RecyclerView(parent.getContext()));
    initRecyclerView(viewHolder.recyclerView);
    return viewHolder;
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    if (holder.rowItemAdapter.getItemCount() == 0) {
      holder.rowItemAdapter.setData(stackAdapter, position + 1);
    } else {
      holder.rowItemAdapter.setRow(position + 1);
    }
  }

  public void initRecyclerView(RecyclerView recyclerView) {
    observerList.add(recyclerView);
    recyclerView.addOnScrollListener(new ScrollListener(observerList));
  }

  static class ViewHolder extends RecyclerView.ViewHolder {
    RecyclerView recyclerView;
    RowItemAdapter rowItemAdapter = new RowItemAdapter();

    public ViewHolder(View view) {
      super(view);
      this.recyclerView = (RecyclerView) view;
      this.recyclerView.setLayoutManager(
          new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
      this.recyclerView.setAdapter(rowItemAdapter);
    }
  }
}
