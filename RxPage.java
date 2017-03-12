package com.example.yin.myapplication;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import com.jakewharton.rxbinding2.support.v7.widget.RecyclerViewScrollEvent;
import io.reactivex.ObservableTransformer;
import java.util.Arrays;

import static android.support.v7.widget.RecyclerView.LayoutManager;

/**
 * Created by yin on 17-3-12.
 */
public class RxPage {

  private static int limint=5;//每一页加载多少项
  private static int visibleThreshold=5;//为总共多少项减去已经加载多少项
  private static int lastVisibleItem=0;//已经加载多少项
  private static LayoutManager layoutManager;

  private RxPage() {
  }

  public static ObservableTransformer<RecyclerViewScrollEvent, Integer> pageTransformer(){
    return upstream -> upstream.map(event -> lastVisibleItem(event.view().getLayoutManager()))
        .map(RxPage::onPage)
        .filter(integer -> integer>0)
        .distinctUntilChanged();
  }

  private static int onPage(int item){
    if (item + visibleThreshold >= layoutManager.getItemCount()) {
      return (int) Math.ceil(layoutManager.getItemCount() / limint) + 1;
    }
    return -1;
  }

  //已经加载了多少项
  private static Integer lastVisibleItem(LayoutManager layoutManager) {
    RxPage.layoutManager = layoutManager;
    if (layoutManager instanceof LinearLayoutManager) {
      lastVisibleItem= ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
    }
    if (layoutManager instanceof GridLayoutManager) {
      lastVisibleItem= ((GridLayoutManager) layoutManager).findLastVisibleItemPosition();
    }
    if (layoutManager instanceof StaggeredGridLayoutManager) {
      StaggeredGridLayoutManager manager = (StaggeredGridLayoutManager) layoutManager;
      int[] ints = manager.findLastVisibleItemPositions(null);
      Arrays.sort(ints);
      lastVisibleItem=ints[ints.length - 1];
    }
    return lastVisibleItem;
  }
}
