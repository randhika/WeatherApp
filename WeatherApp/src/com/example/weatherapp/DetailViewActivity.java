package com.example.weatherapp;

import com.example.weatherapp.R.id;

import Misc.Constants;
import Misc.UserPreferences;
import Model.WeatherListItem;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailViewActivity extends Activity {

	TextView averageTemp;
	TextView minMaxTemp;
	TextView date;
	TextView sunriseTime;
	TextView sunsetTime;
	TextView summary;
	TextView town;

	WeatherListItem currentItem;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detail_view_activity_layout);

		currentItem = (WeatherListItem) getIntent().getSerializableExtra(
				Constants.INTENT_EXTRA_DAY_OBJECT);
		initViews();
	}

	public void initViews() {
		averageTemp = (TextView) findViewById(R.id.tv_detail_view_avg_temp);
		averageTemp.setText(currentItem.getTemp());

		minMaxTemp = (TextView) findViewById(R.id.tv_detail_view_min_max_temp);
		minMaxTemp.setText(currentItem.getMinMaxTemp());

		date = (TextView) findViewById(R.id.tv_detail_view_date);
		date.setText(currentItem.getDate());

		sunriseTime = (TextView) findViewById(R.id.tv_detail_view_sunrise_time);
		sunriseTime.setText(currentItem.getSunriseTime());

		sunsetTime = (TextView) findViewById(R.id.tv_detail_view_sunset_time);
		sunsetTime.setText(currentItem.getSunsetTime());

		summary = (TextView) findViewById(R.id.tv_detail_view_summary);
		summary.setText(currentItem.getSummary());

		town = (TextView) findViewById(id.tv_detail_view_location);
		town.setText(UserPreferences.getUserPreferences().getCity());

	}

}
