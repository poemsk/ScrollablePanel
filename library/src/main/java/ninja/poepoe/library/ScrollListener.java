package ninja.poepoe.library;

import android.support.v7.widget.RecyclerView;
import java.util.HashSet;

/**
 * Created by Poe Poe on 13/12/16.
 */

public class ScrollListener extends RecyclerView.OnScrollListener {

  private HashSet<RecyclerView> observerList = new HashSet<>();
  private int state;

  public ScrollListener(HashSet<RecyclerView> observerList) {
    this.observerList = observerList;
  }

  @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    super.onScrolled(recyclerView, dx, dy);
    if (state == RecyclerView.SCROLL_STATE_IDLE) {
      return;
    }
    for (RecyclerView rv : observerList) {
      if (recyclerView != rv) {
        rv.scrollBy(dx, dy);
      }
    }
  }

  @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
    super.onScrollStateChanged(recyclerView, newState);
    state = newState;
  }
}
