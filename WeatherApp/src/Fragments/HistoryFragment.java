package Fragments;

import java.util.List;

import Adapters.WeatherForNextDaysAdapter;
import Adapters.WeatherHistoryAdapter;
import Misc.Constants;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.weatherapp.DetailViewActivity;
import com.example.weatherapp.R;
import com.example.weatherapp.model.WeatherDatabase;
import com.example.weatherapp.model.WeatherListItem;

public class HistoryFragment extends Fragment implements OnItemClickListener {

	View myView;
	ListView weatherStatsListView;
	WeatherHistoryAdapter adapter;
	WeatherDatabase database;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		myView = inflater.inflate(R.layout.favourites_fragment_layout,
				container, false);
		init();
		return myView;
	}

	public void init() {
		weatherStatsListView = (ListView) myView
				.findViewById(R.id.lv_weather_history);
		database = new WeatherDatabase(getActivity());
		List<WeatherListItem> weatherList = database.getAllWeatherItems();
		adapter = new WeatherHistoryAdapter(getActivity(), weatherList);
		weatherStatsListView.setOnItemClickListener(this);
		weatherStatsListView.setAdapter(adapter);
	}

	public void startDetailViewForItem(int position) {
		WeatherListItem item = WeatherForNextDaysAdapter.weatherStatsList
				.get(position);
		Intent i = new Intent(getActivity().getApplicationContext(),
				DetailViewActivity.class);
		i.putExtra(Constants.INTENT_EXTRA_DAY_OBJECT, item);
		getActivity().startActivity(i);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position,
			long arg3) {
		startDetailViewForItem(position);
	}
}
