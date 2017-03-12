package com.example.yin.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yin on 17-3-12.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

  private List<String> stringList = new ArrayList<>();

  public void addAll(List<String> stringList){
    this.stringList.addAll(stringList);
    notifyDataSetChanged();
  }

  @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, null);
    return new ViewHolder(view);
  }

  @Override public void onBindViewHolder(ViewHolder holder, int position) {
    String str = stringList.get(position);
    holder.onBind(str);
  }

  @Override public int getItemCount() {
    return stringList.size();

  }

  public class ViewHolder extends RecyclerView.ViewHolder {

    @BindView(android.R.id.text1) TextView textView;

    public ViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }

    public void onBind(String str){
      textView.setText(str);
    }
  }
}
