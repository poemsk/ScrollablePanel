package ninja.poepoe.library;

import android.support.v7.widget.RecyclerView;

import java.util.HashSet;

public class SynchronizedScrollListener extends RecyclerView.OnScrollListener {

    private HashSet<RecyclerView> observerList = new HashSet<>();

    public SynchronizedScrollListener(HashSet<RecyclerView> observerList) {
        this.observerList = observerList;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        for (RecyclerView rv : observerList) {
            if (recyclerView != rv) {
                rv.removeOnScrollListener(this);
                rv.scrollBy(dx, dy);
                rv.addOnScrollListener(this);
            }
        }
    }

}
