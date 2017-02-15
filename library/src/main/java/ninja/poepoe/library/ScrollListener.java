package ninja.poepoe.library;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.HashSet;

/**
 * Created by Poe Poe on 13/12/16.
 */

public class ScrollListener extends RecyclerView.OnScrollListener {

  private HashSet<RecyclerView> observerList = new HashSet<>();
  private int state;
  private boolean isVertical;

  public ScrollListener(HashSet<RecyclerView> observerList, boolean isVertical) {
    this.observerList = observerList;
    this.isVertical = isVertical;
  }

  @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);
    if (state == RecyclerView.SCROLL_STATE_IDLE) {
      return;
    }
    LinearLayoutManager currentLayoutManager =
        (LinearLayoutManager) recyclerView.getLayoutManager();
    int firstVisiblePosition = currentLayoutManager.findFirstVisibleItemPosition();
    View v = currentLayoutManager.getChildAt(0);

    if (v != null) {
      int offset;
      if (isVertical) {
        offset = currentLayoutManager.getDecoratedBottom(v);
      } else {
        offset = currentLayoutManager.getDecoratedRight(v);
      }

      for (RecyclerView rv : observerList) {
        if (recyclerView != rv) {
          //scroll to position
          LinearLayoutManager otherLayoutManager = (LinearLayoutManager) rv.getLayoutManager();
          if (otherLayoutManager != null) {
            otherLayoutManager.scrollToPositionWithOffset(firstVisiblePosition + 1, offset);
          }
        }
      }
    }
  }

  @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
    super.onScrollStateChanged(recyclerView, newState);
    state = newState;
  }
}
