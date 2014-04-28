package Fragments;

import Adapters.WeatherForNextDaysAdapter;
import Misc.Constants;
import Network.GetWeatherStats;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.example.weatherapp.DetailViewActivity;
import com.example.weatherapp.R;

public class HomeFragment extends Fragment implements OnItemClickListener {

	View myView;
	TextView temperatureTV;
	TextView weatherStateTV;
	TextView cityAndTimeTV;
	TextView lastUpdateTV;

	ListView weatherForNextDaysLV;
	public static WeatherForNextDaysAdapter weatherDaysAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		myView = inflater.inflate(R.layout.home_fragment_layout, container,
				false);
		initViews();
		return myView;
	}

	private void initViews() {
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
		updateWeather();
	}

	public void updateWeather() {
		new GetWeatherStats(temperatureTV, weatherStateTV, cityAndTimeTV,
				lastUpdateTV).execute();
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		Intent i = new Intent(getActivity().getApplicationContext(),
				DetailViewActivity.class);
		i.putExtra(Constants.INTENT_EXTRA_DAY_OBJECT,
				WeatherForNextDaysAdapter.weatherStatsList.get(position));
		getActivity().startActivity(i);
	}

}
