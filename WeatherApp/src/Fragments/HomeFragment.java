package Fragments;

import Adapters.WeatherForNextDaysAdapter;
import Misc.Constants;
import Network.GetWeatherStats;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.weatherapp.DetailViewActivity;
import com.example.weatherapp.R;
import com.example.weatherapp.model.WeatherDatabase;
import com.example.weatherapp.model.WeatherListItem;

public class HomeFragment extends Fragment implements OnItemClickListener,
		OnClickListener {

	View myView;
	static TextView temperatureTV;
	static TextView weatherStateTV;
	static TextView cityAndTimeTV;
	static TextView lastUpdateTV;

	LinearLayout todayWeather;
	ListView weatherForNextDaysLV;
	public static WeatherForNextDaysAdapter weatherDaysAdapter;
	static WeatherDatabase db;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		myView = inflater.inflate(R.layout.home_fragment_layout, container,
				false);
		initViews();
		return myView;
	}

	private void initViews() {
		todayWeather = (LinearLayout) myView
				.findViewById(R.id.ll_today_weather);
		todayWeather.setOnClickListener(this);
		temperatureTV = (TextView) myView.findViewById(R.id.tv_temperature);
		weatherStateTV = (TextView) myView.findViewById(R.id.tv_weather_stat);
		cityAndTimeTV = (TextView) myView.findViewById(R.id.tv_current_city);
		lastUpdateTV = (TextView) myView.findViewById(R.id.tv_last_update);
		weatherForNextDaysLV = (ListView) myView
				.findViewById(R.id.lv_weather_for_next_day);
		weatherDaysAdapter = new WeatherForNextDaysAdapter(getActivity()
				.getApplicationContext());
		weatherForNextDaysLV.setAdapter(weatherDaysAdapter);
		weatherForNextDaysLV.setOnItemClickListener(this);
		db = new WeatherDatabase(getActivity());
		WeatherForNextDaysAdapter.weatherStatsList = db
				.getAllWeatherItemsFromToday();
		HomeFragment.weatherDaysAdapter.notifyDataSetChanged();
		updateWeather();
		updateTodaysWeather();
	}

	public void updateWeather() {
		new GetWeatherStats().execute();
	}

	@SuppressLint("SimpleDateFormat")
	public static void updateTodaysWeather() {
		if (WeatherForNextDaysAdapter.weatherStatsList.size() <= 0)
			return;
		WeatherListItem today = WeatherForNextDaysAdapter.weatherStatsList
				.get(0);

		// textView is the TextView view that should display it

		temperatureTV.setText(today.getTemp());
		weatherStateTV.setText(today.getSummary());
		cityAndTimeTV.setText(today.getCity());
		lastUpdateTV.setText(today.getFullTime());
		db.addWeatherItemsList(WeatherForNextDaysAdapter.weatherStatsList);
	}
	
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		startDetailViewForItem(++position);
	}

	@Override
	public void onClick(View v) {
		startDetailViewForItem(0);
	}

	public void startDetailViewForItem(int position) {
		// If the list isnt loaded on item click try to reload it!
		if (WeatherForNextDaysAdapter.weatherStatsList.size() <= 0) {
			updateWeather();
			return;
		}

		WeatherListItem item = WeatherForNextDaysAdapter.weatherStatsList
				.get(position);
		Intent i = new Intent(getActivity().getApplicationContext(),
				DetailViewActivity.class);
		i.putExtra(Constants.INTENT_EXTRA_DAY_OBJECT, item);
		getActivity().startActivity(i);
	}

}
