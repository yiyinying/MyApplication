package com.example.yin.myapplication;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout;
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {

  AtomicInteger atomicInteger = new AtomicInteger(0);

  private final List<String> stringList =
      Arrays.asList("1", "2", "3", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4",
          "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4",
          "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4", "4");
  @BindView(R.id.recycler_view) RecyclerView recyclerView;
  @BindView(R.id.swipe_refresh_layout) SwipeRefreshLayout swipeRefreshLayout;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    MainAdapter adapter = new MainAdapter();
    recyclerView.setAdapter(adapter);
    adapter.addAll(stringList);
    onRefreshes();

    RxRecyclerView.scrollEvents(recyclerView)
        .compose(RxPage.pageTransformer())
        .subscribe(integer -> Log.d("WO", String.valueOf(integer)));
  }

  private void onRefreshes() {
    RxSwipeRefreshLayout.refreshes(swipeRefreshLayout).subscribe(o -> {
      Toast.makeText(this, "dddd", Toast.LENGTH_SHORT).show();
      RxSwipeRefreshLayout.refreshing(swipeRefreshLayout).accept(false);
    });
  }
}
