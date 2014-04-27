package Fragments;

import Network.GetWeatherStats;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.weatherapp.R;

public class HomeFragment extends Fragment implements OnClickListener {

	View myView;
	Button getWeatherBtn;

	TextView temperatureTV;
	TextView weatherStateTV;
	TextView cityAndTimeTV;
	TextView lastUpdateTV;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		myView = inflater.inflate(R.layout.home_fragment_layout, container,
				false);
		initViews();
		return myView;
	}

	private void initViews() {
		getWeatherBtn = (Button) myView.findViewById(R.id.btn_get_weather);

		temperatureTV = (TextView) myView.findViewById(R.id.tv_temperature);
		weatherStateTV = (TextView) myView.findViewById(R.id.tv_weather_stat);
		cityAndTimeTV = (TextView) myView.findViewById(R.id.tv_current_city);
		lastUpdateTV = (TextView) myView.findViewById(R.id.tv_last_update);

		getWeatherBtn.setOnClickListener(this);
		getWeather();

	}

	@Override
	public void onClick(View v) {
		int id = v.getId();

		switch (id) {
		case R.id.btn_get_weather:
			break;
		default:
			break;
		}
	}

	public void getWeather() {
		new GetWeatherStats(temperatureTV, weatherStateTV, cityAndTimeTV,
				lastUpdateTV).execute();
	}

}
