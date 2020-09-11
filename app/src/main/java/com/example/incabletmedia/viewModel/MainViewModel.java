package com.example.incabletmedia.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.incabletmedia.model.Restaurant;
import com.example.incabletmedia.myRepository.MyRepository;

public class MainViewModel extends ViewModel {
	private MutableLiveData<Restaurant> restaurantMutableLiveData = null;
	private MyRepository myRepository;

	public void init()
	{
		if(restaurantMutableLiveData != null)
			return;

		myRepository = MyRepository.getInstance();
		restaurantMutableLiveData = myRepository.getCustomer();
	}

	public LiveData<Restaurant> getRestaurant()
	{
		return this.restaurantMutableLiveData;
	}
}
