package Fragments;

import java.util.Calendar;

import Misc.Constants;
import Misc.UserPreferences;
import android.app.AlarmManager;
import android.app.NotificationManager;
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

import com.example.weatherapp.MainActivity;
import com.example.weatherapp.R;
import com.example.weatherapp.WeatherService;

public class SettingsFragment extends Fragment implements
		OnCheckedChangeListener {

	View myView;
	CheckBox changeNotificationState;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		myView = inflater.inflate(R.layout.settings_frgament_layout, container,
				false);
		init();
		return myView;
	}

	public void init() {

		changeNotificationState = (CheckBox) myView
				.findViewById(R.id.cb_shouldRunService);
		changeNotificationState.setChecked(UserPreferences.getUserPreferences()
				.notificationOn());
		changeNotificationState.setOnCheckedChangeListener(this);
		changeNotificationState();
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

		UserPreferences.getUserPreferences().changeNotificationState();
		changeNotificationState();
	}

	private void changeNotificationState() {

		MainActivity activity = (MainActivity) getActivity();
		if (changeNotificationState.isChecked()) {
			activity.startServiceRepeating();
		} else {
			activity.stopServiceRepeating();
			removeNotifications();
		}
	}

	private void removeNotifications() {
		NotificationManager nm = (NotificationManager) getActivity()
				.getSystemService(Context.NOTIFICATION_SERVICE);
		nm.cancel(Constants.notificationID);
	}

}
