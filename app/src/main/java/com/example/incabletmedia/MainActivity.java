package com.example.incabletmedia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.incabletmedia.adapter.RestaurantAdapter;
import com.example.incabletmedia.model.Collection;
import com.example.incabletmedia.model.Restaurant;
import com.example.incabletmedia.viewModel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

	MainViewModel mainViewModel;
	@BindView(R.id.recycler_view)
	RecyclerView recyclerView;
	List<Collection> collectionList;
	RestaurantAdapter restaurantAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);

		initRecyclerView();
		initViewModel();
	}

	private void initRecyclerView() {
		collectionList = new ArrayList<>();
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		restaurantAdapter = new RestaurantAdapter(this, collectionList);
		recyclerView.setAdapter(restaurantAdapter);
	}

	private void initViewModel() {
		mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
		mainViewModel.init();
		mainViewModel.getRestaurant().observe(this, new Observer<Restaurant>() {
			@Override
			public void onChanged(Restaurant restaurant) {
				if(restaurant != null){
					collectionList.addAll(restaurant.getCollections());
					restaurantAdapter.notifyDataSetChanged();
				}
			}
		});

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu, menu);

		MenuItem searchItem = menu.findItem(R.id.action_search);

		SearchManager searchManager = (SearchManager) MainActivity.this.getSystemService(Context.SEARCH_SERVICE);

		SearchView searchView = null;
		if (searchItem != null) {
			searchView = (SearchView) searchItem.getActionView();
		}
		if (searchView != null) {
			searchView.setSearchableInfo(searchManager.getSearchableInfo(MainActivity.this.getComponentName()));
		}

		searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			@Override
			public boolean onQueryTextSubmit(String query) {
				return false;
			}

			@Override
			public boolean onQueryTextChange(String newText) {
				filter(newText.trim().toLowerCase());
				return false;
			}
		});
		return super.onCreateOptionsMenu(menu);
	}

	private void filter(String text){
		List<Collection> temp = new ArrayList();
		for(Collection d: collectionList){
			if(d.getCollection().getTitle().toLowerCase().contains(text)){
				temp.add(d);
			}
		}
		//update recyclerview
		restaurantAdapter.updateList(temp);
	}

}