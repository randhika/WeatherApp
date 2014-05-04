package Fragments;

import java.util.Calendar;

import Misc.Constants;
import Misc.UserPreferences;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.example.weatherapp.R;
import com.example.weatherapp.WeatherService;

public class SettingsFragment extends Fragment implements
		OnCheckedChangeListener {

	View myView;
	CheckBox changeNotificationState;
	Calendar cal;
	Intent intent;
	PendingIntent pintent;
	AlarmManager alarm;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		myView = inflater.inflate(R.layout.settings_frgament_layout, container,
				false);
		init();
		return myView;
	}

	public void init() {
		cal = Calendar.getInstance();

		intent = new Intent(getActivity(), WeatherService.class);
		pintent = PendingIntent.getService(getActivity(), 0, intent, 0);
		alarm = (AlarmManager) getActivity().getSystemService(
				Context.ALARM_SERVICE);
		changeNotificationState = (CheckBox) myView
				.findViewById(R.id.cb_shouldRunService);
		changeNotificationState.setChecked(UserPreferences.getUserPreferences()
				.notificationOn());
		changeNotificationState.setOnCheckedChangeListener(this);
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		UserPreferences.getUserPreferences().changeNotificationState();
		if (isChecked)
			startServiceRepeating();
		else
			stopServiceRepeating();
	}

	private void startServiceRepeating() {

		// Start every 30 seconds
		alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
				Constants.SERVICE_UPDATE_TIME, pintent);
	}

	private void stopServiceRepeating() {
		alarm.cancel(pintent);
	}
}
