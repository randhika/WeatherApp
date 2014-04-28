package Network;

import org.json.JSONObject;

import Misc.Constants;
import Misc.UserPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;
import dme.forecastiolib.FIOCurrently;
import dme.forecastiolib.ForecastIO;

public class GetWeatherStats extends AsyncTask<Void, Void, ForecastIO> {

	TextView temperatureTV;
	TextView weatherStateTV;
	TextView cityAndTimeTV;
	TextView lastUpdateTV;
	UserPreferences userPref;

	public GetWeatherStats(TextView temperatureTV, TextView weatherStateTV,
			TextView cityAndTimeTV, TextView lastUpdateTV) {
		this.temperatureTV = temperatureTV;
		this.weatherStateTV = weatherStateTV;
		this.cityAndTimeTV = cityAndTimeTV;
		this.lastUpdateTV = lastUpdateTV;
		userPref = UserPreferences.getUserPreferences();
	}

	@Override
	protected ForecastIO doInBackground(Void... params) {

		ForecastIO fio = new ForecastIO(Constants.APIKEY);
		fio.setUnits(ForecastIO.UNITS_UK);
		fio.setExcludeURL("hourly,minutely");
		fio.getForecast(userPref.getLatitude() + "", userPref.getLongtitude()
				+ "");

		return fio;
	}

	@Override
	protected void onPostExecute(ForecastIO fio) {
		super.onPostExecute(fio);
		FIOCurrently currently = new FIOCurrently(fio);
		temperatureTV.setText(currently.get().temperature()
				+ Constants.celciusExtension);
		weatherStateTV.setText(currently.get().summary());
		cityAndTimeTV.setText(userPref.getCity());
		lastUpdateTV.setText(currently.get().time());
	}

}
