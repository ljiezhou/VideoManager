package com.ds.res.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * Create by hanweiwei on 11/07/2018
 */
@SuppressWarnings({"SpellCheckingInspection", "unused"})
public class LoadMoreRecyclerView extends RecyclerView {

    private boolean mIsLoading = false;
    private ILoadMoreListener mLoadMoreListener;
    private boolean mIsTopLoading = false;
    private IScrollTopListener mScorllTopListener;

    public LoadMoreRecyclerView(Context context) {
        super(context);
        init(context);
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadMoreRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private static final String TAG = "LoadMoreRecyclerView";

    private void init(Context context) {
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                if (recyclerView == null || recyclerView.getLayoutManager() == null) {
                    return;
                }

                if (SCROLL_STATE_IDLE == newState) {
                    LayoutManager layoutManager = recyclerView.getLayoutManager();
                    int lastCompletePosition = -1;
                    int firstCompletePosition = -1;
                    if (layoutManager instanceof LinearLayoutManager) {
                        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                        lastCompletePosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                        firstCompletePosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                    } else if (layoutManager instanceof StaggeredGridLayoutManager) {
                        StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) layoutManager;
                        int[] lastPos = manager.findLastCompletelyVisibleItemPositions(new int[manager.getSpanCount()]);
                        int maxVal = Integer.MIN_VALUE;
                        for (int pos : lastPos) {
                            if (pos > maxVal) {
                                maxVal = pos;
                            }
                        }
                        lastCompletePosition = maxVal;

                        int[] firstVisibleItemPositions = ((StaggeredGridLayoutManager) layoutManager).findFirstCompletelyVisibleItemPositions(null);
                        for (int firstVisibleItemPosition : firstVisibleItemPositions) {
                            if (firstVisibleItemPosition == 0) {
                                firstCompletePosition = firstVisibleItemPosition;
                                break;
                            }
                        }
                    }

                    if (lastCompletePosition == layoutManager.getItemCount() - 1 && !mIsLoading) {
                        if (mLoadMoreListener != null) {
                            mIsLoading = true;
                            mLoadMoreListener.onLoadMore();
                        }
                    }

                    if (firstCompletePosition == 0 && !mIsTopLoading) {
                        if (mScorllTopListener != null) {
                            mIsTopLoading = true;
                            mScorllTopListener.onTop();
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            }
        });
    }

    @Override
    public void setLayoutManager(LayoutManager layout) {
        super.setLayoutManager(layout);
        if (layout != null && getAdapter() != null) {
            getAdapter().onAttachedToRecyclerView(this);//手动调用下，否则加载更多异常
        }
    }

    public ILoadMoreListener getLoadMoreListener() {
        return mLoadMoreListener;
    }

    public IScrollTopListener getScrollTopListener() {
        return mScorllTopListener;
    }

    public void setLoadMoreListener(ILoadMoreListener loadMoreListener) {
        mLoadMoreListener = loadMoreListener;
    }

    public void setScorllTopListener(IScrollTopListener scorllTopListener) {
        mScorllTopListener = scorllTopListener;
    }

    public void setLoadingFinish() {
        mIsLoading = false;
    }

    public boolean isLoading() {
        return mIsLoading;
    }


    public void setTopLoadingFinish() {
        mIsTopLoading = false;
    }

    public boolean isTopLoading() {
        return mIsTopLoading;
    }


}
