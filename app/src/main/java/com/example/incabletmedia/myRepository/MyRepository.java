package com.example.incabletmedia.myRepository;

import androidx.lifecycle.MutableLiveData;

import com.example.incabletmedia.model.Restaurant;
import com.example.incabletmedia.network.CustomerApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyRepository {

	private static MyRepository myRepositoryInstance;
	public static MyRepository getInstance()
	{
		if(myRepositoryInstance == null)
			myRepositoryInstance = new MyRepository();

		return myRepositoryInstance;
	}

	public MutableLiveData<Restaurant> getCustomer() {
		final MutableLiveData<Restaurant> restaurantMutableLiveData = new MutableLiveData<>();

		CustomerApiClient.getInstance().getApi().getRestaurant().enqueue(new Callback<Restaurant>() {
			@Override
			public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
				restaurantMutableLiveData.setValue(response.body());
			}

			@Override
			public void onFailure(Call<Restaurant> call, Throwable t) {

			}
		});
		return restaurantMutableLiveData;
	}
}
