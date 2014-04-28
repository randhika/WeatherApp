package Fragments;

import Adapters.WeatherForNextDaysAdapter;
import Misc.Constants;
import Model.WeatherListItem;
import Network.GetWeatherStats;
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
		updateWeather();
	}

	public void updateWeather() {
		new GetWeatherStats().execute();
	}

	public static void updateTodaysWeather() {
		WeatherListItem today = WeatherForNextDaysAdapter.weatherStatsList
				.get(0);
		temperatureTV.setText(today.getTemp());
		weatherStateTV.setText(today.getSummary());
		cityAndTimeTV.setText(today.getCity());
		lastUpdateTV.setText(today.getDate());
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		startDetailViewForItem(WeatherForNextDaysAdapter.weatherStatsList
				.get(++position));

	}

	@Override
	public void onClick(View v) {
		startDetailViewForItem(WeatherForNextDaysAdapter.weatherStatsList
				.get(0));
	}

	public void startDetailViewForItem(WeatherListItem item) {
		Intent i = new Intent(getActivity().getApplicationContext(),
				DetailViewActivity.class);
		i.putExtra(Constants.INTENT_EXTRA_DAY_OBJECT, item);
		getActivity().startActivity(i);
	}

}
