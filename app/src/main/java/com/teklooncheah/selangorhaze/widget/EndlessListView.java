package com.teklooncheah.selangorhaze.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.teklooncheah.selangorhaze.R;

/**
 * Created by tekloon on 11/14/15.
 */
public class EndlessListView extends ListView implements AbsListView.OnScrollListener {


    private static final String TAG = "LoadMoreListView";
    private SwipeRefreshLayout refreshLayout;

    /**
     * Listener that will receive notifications every time the list scrolls.
     */
    private OnScrollListener mOnScrollListener;

    private View mProgressBarLoadMore;

    private boolean hasMorePages;

    // Listener to process load more items when user reaches the end of the list
    private OnLoadMoreListener mOnLoadMoreListener;
    // To know if the list is loading more items
    private boolean mIsLoadingMore = true;
    private int mCurrentScrollState;

    public EndlessListView(Context context) {
        super(context);
        init(context);
    }

    public EndlessListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public EndlessListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {

        // footer
        View footer = LayoutInflater.from(context).inflate(R.layout.load_more_footer, this, false);
        mProgressBarLoadMore = footer.findViewById(R.id.load_more_progressBar);
        addFooterView(footer, null, false);
        this.hasMorePages = true;
        super.setOnScrollListener(this);
        TypedValue ta = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.selectableItemBackground, ta, true);
        this.setSelector(ta.resourceId);
    }

    @Override
    public void setAdapter(ListAdapter adapter) {
        super.setAdapter(adapter);
        if (mCustomLoadingView != null)
            setEmptyView(mCustomLoadingView);
    }

    private void displayCustomLoading() {
        mCustomLoadingView.setVisibility(View.VISIBLE);
    }

    private void hideCustomLoadingView() {
        mCustomLoadingView.setVisibility(View.GONE);
    }

    /**
     * Set the listener that will receive notifications every time the list
     * scrolls.
     *
     * @param l The scroll listener.
     */
    @Override
    public void setOnScrollListener(OnScrollListener l) {
        mOnScrollListener = l;
    }

    /**
     * Register a callback to be invoked when this list reaches the end (last
     * item be visible)
     *
     * @param onLoadMoreListener The callback to run.
     */
    public void setOnLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
        mOnLoadMoreListener = onLoadMoreListener;
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {

//        if (firstVisibleItem == 0) {
//            if (getRefreshLayout() != null)
//                getRefreshLayout().setEnabled(true);
//        } else {
//            if (getRefreshLayout() != null)
//            getRefreshLayout().setEnabled(false);
//        }

        if (mOnScrollListener != null) {
            mOnScrollListener.onScroll(view, firstVisibleItem,
                    visibleItemCount, totalItemCount);
        }

        if (mOnLoadMoreListener != null) {
            if (visibleItemCount == totalItemCount) {
                mProgressBarLoadMore.setVisibility(View.GONE);
                return;
            }

//            boolean loadMore = firstVisibleItem + visibleItemCount >= (totalItemCount - 1); //included footer count?
//            if (hasMorePages && !mIsLoadingMore && loadMore) {
//                mProgressBarLoadMore.setVisibility(View.VISIBLE);
//                mIsLoadingMore = true;
//                if (getRefreshLayout() != null)
//                    getRefreshLayout().setEnabled(false);
//                onLoadMore();
//            }

            boolean loadMore = firstVisibleItem + visibleItemCount >= (totalItemCount - 1); //included footer count?
            if (getRefreshLayout() != null) {
                if (hasMorePages && !mIsLoadingMore && loadMore && !getRefreshLayout().isRefreshing()) {
                    mProgressBarLoadMore.setVisibility(View.VISIBLE);
                    mIsLoadingMore = true;
                    getRefreshLayout().setEnabled(false);
                    onLoadMore();
                }
            } else {
                if (hasMorePages && !mIsLoadingMore && loadMore) {
                    mProgressBarLoadMore.setVisibility(View.VISIBLE);
                    mIsLoadingMore = true;
                    if (getRefreshLayout() != null)
                        getRefreshLayout().setEnabled(false);
                    onLoadMore();
                }
            }

            if (hasMorePages && !mIsLoadingMore && loadMore) {
                mProgressBarLoadMore.setVisibility(View.VISIBLE);
                mIsLoadingMore = true;
                if (getRefreshLayout() != null)
                    getRefreshLayout().setEnabled(false);
                onLoadMore();
            }


        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        mCurrentScrollState = scrollState;

        if (mOnScrollListener != null) {
            mOnScrollListener.onScrollStateChanged(view, scrollState);
        }

    }

    public void onLoadMore() {
        if (mOnLoadMoreListener != null) {
            mOnLoadMoreListener.onLoadMore();
        }
    }

    public void hasMorePages(boolean morePage) {
        this.hasMorePages = morePage;
    }

    public void onLoadMoreComplete() {
        mIsLoadingMore = false;
        if (getRefreshLayout() != null)
            getRefreshLayout().setEnabled(true);
        mProgressBarLoadMore.setVisibility(View.GONE);
        if (mCustomEmptyView != null)
            setEmptyView(mCustomEmptyView);
    }

    public boolean isLoadingMore() {
        return mIsLoadingMore;
    }

    /**
     * Interface definition for a callback to be invoked when list reaches the
     * last item (the user load more items in the list)
     */
    public interface OnLoadMoreListener {
        /**
         * Called when the list reaches the last item (the last item is visible
         * to the user)
         */
        public void onLoadMore();
    }

    public void showLoadingProgress() {
        mProgressBarLoadMore.setVisibility(View.VISIBLE);
    }

    private View mCustomLoadingView;
    private View mCustomEmptyView;

    public void setCustomLoadingView(View view) {
        mCustomLoadingView = view;
    }

    public void setCustomEmptyView(View mCustomEmptyView) {
        this.mCustomEmptyView = mCustomEmptyView;
    }

    public SwipeRefreshLayout getRefreshLayout() {
        return refreshLayout;
    }

    public void setRefreshLayout(SwipeRefreshLayout refreshLayout) {
        this.refreshLayout = refreshLayout;
    }

    public void setProgressBarLoadMoreVisible(int visible){
        this.mProgressBarLoadMore.setVisibility(visible);
    }
}
