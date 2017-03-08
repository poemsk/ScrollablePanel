package ninja.poepoe.library;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import java.util.HashSet;

/**
 * Created by Poe Poe on 12/12/16.
 */

public class ScrollablePanelView extends RelativeLayout {

  private RecyclerView rvRowHeaders, rvColumnHeaders, rvContent;
  private FrameLayout cornerView;
  private Context mContext;

  public ScrollablePanelView(Context context) {
    super(context);
    this.mContext = context;
    init();
  }

  public ScrollablePanelView(Context context, AttributeSet attrs) {
    super(context, attrs);
    this.mContext = context;
    init();
  }

  public ScrollablePanelView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    this.mContext = context;
    init();
  }

  private void init() {
    createViews();
    generateViewIds();
    addViewsToLayout();
  }

  private void createViews() {
    this.cornerView = new FrameLayout(mContext);
    this.rvRowHeaders = new RecyclerView(mContext);
    this.rvColumnHeaders = new RecyclerView(mContext);
    this.rvContent = new RecyclerView(mContext);
    //column headers, scroll horizontally
    rvColumnHeaders.setLayoutManager(
        new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
    //row headers, scroll vertically
    rvRowHeaders.setLayoutManager(
        new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
    //content grid, scroll vertically
    rvContent.setLayoutManager(
        new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));

    //sync two recyclerviews
    HashSet<RecyclerView> observerList = new HashSet<>();
    observerList.add(rvContent);
    observerList.add(rvRowHeaders);

    SynchronizedScrollListener scrollListener = new SynchronizedScrollListener(observerList);
    rvRowHeaders.addOnScrollListener(scrollListener);
    rvContent.addOnScrollListener(scrollListener);

  }

  private void generateViewIds() {
    this.cornerView.setId(ViewIdGenerator.generateViewId());
    this.rvColumnHeaders.setId(ViewIdGenerator.generateViewId());
    this.rvRowHeaders.setId(ViewIdGenerator.generateViewId());
    this.rvContent.setId(ViewIdGenerator.generateViewId());
  }

  private void addViewsToLayout() {
    LayoutParams columnHeaderRule =
        new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    columnHeaderRule.addRule(RelativeLayout.RIGHT_OF, this.cornerView.getId());

    LayoutParams rowHeaderRule =
        new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    rowHeaderRule.addRule(RelativeLayout.BELOW, this.cornerView.getId());

    LayoutParams contentRule =
        new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    contentRule.addRule(RelativeLayout.RIGHT_OF, this.cornerView.getId());
    contentRule.addRule(RelativeLayout.BELOW, this.rvColumnHeaders.getId());

    this.addView(this.cornerView);
    this.addView(this.rvColumnHeaders, columnHeaderRule);
    this.addView(this.rvRowHeaders, rowHeaderRule);
    this.addView(this.rvContent, contentRule);
  }

  public void setAdapter(PanelAdapter stackAdapter) {
    //clear cornerview
    cornerView.removeAllViews();

    //   init corner view
    RecyclerView.ViewHolder viewHolder =
        stackAdapter.onCreateViewHolder(this, stackAdapter.getItemViewType(0, 0));
    stackAdapter.onBindViewHolder(viewHolder, 0, 0);
    cornerView.addView(viewHolder.itemView);

    //  init column headers
    RowItemAdapter columnHeaderAdapter = new RowItemAdapter(stackAdapter, 0);
    rvColumnHeaders.setAdapter(columnHeaderAdapter);

    //init row headers
    RowHeaderAdapter rowHeaderAdapter = new RowHeaderAdapter(stackAdapter, 0);
    rvRowHeaders.setAdapter(rowHeaderAdapter);

    //  init grid
    GridContentAdapter gridContentAdapter = new GridContentAdapter(stackAdapter, rvColumnHeaders);
    rvContent.setAdapter(gridContentAdapter);
  }
}
