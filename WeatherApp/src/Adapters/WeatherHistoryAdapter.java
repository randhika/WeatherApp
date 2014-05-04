package Adapters;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.weatherapp.R;
import com.example.weatherapp.model.WeatherListItem;

public class WeatherHistoryAdapter extends BaseAdapter {

	Context context;
	public List<WeatherListItem> weatherStatsList;

	public WeatherHistoryAdapter(Context context,
			List<WeatherListItem> weatherStatsList) {
		this.context = context;
		this.weatherStatsList = weatherStatsList;
	}

	@Override
	public int getCount() {
		return this.weatherStatsList.size();
	}

	@Override
	public Object getItem(int position) {
		return this.weatherStatsList.get(position);
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater mInflater = (LayoutInflater) context
					.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
			convertView = mInflater.inflate(R.layout.weather_list_item_layout,
					null);
		}

		// TODO download weatherIcon

		TextView date = (TextView) convertView
				.findViewById(R.id.tv_weather_list_item_date);
		TextView temp = (TextView) convertView
				.findViewById(R.id.tv_weather_list_item_temp);
		TextView stats = (TextView) convertView
				.findViewById(R.id.tv_weather_list_item_other_details);
		// ImageView icon =
		// (ImageView)convertView.findViewById(R.id.iv_weather_list_item_icon);

		// get rid of the time only show the date
		date.setText(weatherStatsList.get(position).getDate());
		temp.setText(weatherStatsList.get(position).getTemp());
		stats.setText(weatherStatsList.get(position).getSummary());

		return convertView;
	}

}
