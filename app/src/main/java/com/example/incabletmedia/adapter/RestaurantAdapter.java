package com.example.incabletmedia.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.incabletmedia.R;
import com.example.incabletmedia.model.Collection;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantAdapter.ViewHolder> {

	LayoutInflater layoutInflater;
	List<Collection> lists;

	public RestaurantAdapter(Context context, List<Collection> lists) {
		this.layoutInflater = layoutInflater.from(context);
		this.lists = lists;
	}

	public void updateList(List<Collection> list){
		lists = list;
		notifyDataSetChanged();
	}

	@NonNull
	@Override
	public RestaurantAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View view = layoutInflater.inflate(R.layout.custom_news_layout, parent, false);
		return new ViewHolder(view);
	}

	@SuppressLint("SetTextI18n")
    @Override
	public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

		Glide.with(layoutInflater.getContext()).load(lists.get(position).getCollection().getImageUrl()).into(holder.customerImg);
		holder.name.setText("Name: " + lists.get(position).getCollection().getTitle());
		holder.status.setText("Description: " + lists.get(position).getCollection().getDescription());
	}

	@Override
	public int getItemCount() {
		return lists.size();
	}

	public static class ViewHolder extends RecyclerView.ViewHolder {
		@BindView(R.id.customer_profile)
		ImageView customerImg;
		@BindView(R.id.txt_name)
		TextView name;
		@BindView(R.id.txt_status)
		TextView status;

		public ViewHolder(@NonNull final View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);

		}
	}
}
