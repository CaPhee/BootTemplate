package com.example;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

public class TestGoogleApi {
	public static void main(String[] args) throws Exception {
		GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyAB2LvImfNA4Z4iZ_Rib15YsLYJR5AOqO8");
		GeocodingResult[] results = GeocodingApi.geocode(context, "1600 Amphitheatre Parkway Mountain View, CA 94043")
				.await();
		System.out.println(results[0].geometry.location.lat);
		testTextSearchGoogleAPI();
	}
	private static void testTextSearchGoogleAPI(){
		RestTemplate restTemplate = new RestTemplate();
		String jsonString=restTemplate.getForObject("https://maps.googleapis.com/maps/api/place/textsearch/json?query=bus+station&location=16.065599,108.184212&radius=10000&key=AIzaSyC2qVKwmfiNY6DNyIIFOqZ4HTJYJ_E1pz4", String.class);
		JSONObject jsonObject= new JSONObject(jsonString);
		JSONArray jsonArray= jsonObject.getJSONArray("results");
		for(int i=0;i<jsonArray.length();i++){
			System.out.println(jsonArray.getJSONObject(i).get("name"));
		}
	}

}

