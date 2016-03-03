package com.digital.ayaz.Listener;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public abstract class EndlessRecyclerOnScrollListener extends
  RecyclerView.OnScrollListener {
 public static String TAG = EndlessRecyclerOnScrollListener.class
   .getSimpleName();

 private int previousTotal = 0;
 private boolean loading = true;
 private int visibleThreshold = 5;
 int firstVisibleRow, visibleRowCount, totalRowCount;

 private int current_page = 1;

 private GridLayoutManager mGridLayoutManager;

 public EndlessRecyclerOnScrollListener(
         GridLayoutManager linearLayoutManager) {
  this.mGridLayoutManager = linearLayoutManager;
 }

 @Override
 public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
  super.onScrolled(recyclerView, dx, dy);

  visibleRowCount = recyclerView.getChildCount()/2;
  totalRowCount = mGridLayoutManager.getItemCount()/2;
  firstVisibleRow = mGridLayoutManager.findFirstVisibleItemPosition()/2;

  if (loading) {
   if (totalRowCount > previousTotal) {
    loading = false;
    previousTotal = totalRowCount;
   }
  }
  if (!loading
    && (((firstVisibleRow+visibleRowCount)==totalRowCount))) {
   // End has been reached

   // Do something
   current_page++;

   onLoadMore(current_page);

   loading = true;
  }
 }

 public abstract void onLoadMore(int current_page);
}