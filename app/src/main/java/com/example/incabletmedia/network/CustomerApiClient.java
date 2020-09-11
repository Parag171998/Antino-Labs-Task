package com.example.incabletmedia.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerApiClient {

	private static final String NEWS_BASE_URL = "https://developers.zomato.com/api/v2.1/";
	private static CustomerApiClient customerApiClient;
	private static Retrofit retrofit;

	private CustomerApiClient() {
		retrofit = new Retrofit.Builder().baseUrl(NEWS_BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
	}

	public static synchronized CustomerApiClient getInstance() {
		if (customerApiClient == null) {
			customerApiClient = new CustomerApiClient();
		}
		return customerApiClient;
	}


	public ApiInterface getApi() {
		return retrofit.create(ApiInterface.class);
	}
}