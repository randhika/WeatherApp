package com.example.weatherapp;

import Misc.Constants;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

public class WeatherServiceBroadcastReciver extends BroadcastReceiver {
	private Context context;
	private NotificationCompat.Builder nBuilder;
	private NotificationManager mNotificationManager;

	@Override
	public void onReceive(Context context, Intent intent) {
		this.context = context;
		Bundle bundle = intent.getExtras();
		if (bundle != null) {
			int resultCode = bundle.getInt(WeatherService.RESULT);
			if (resultCode == Activity.RESULT_OK) {
				String temp = bundle
						.getString(Constants.SERVICE_INTENT_WEATEHR_TEMP_EXTRA);
				Toast.makeText(context, "SERVICE UPDATED", Toast.LENGTH_SHORT)
						.show();
				updateNotification(temp);
			} else {
				Toast.makeText(context, "Service update failed",
						Toast.LENGTH_SHORT).show();
			}
		}
	}

	private void updateNotification(String temp) {
		mNotificationManager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		nBuilder = new NotificationCompat.Builder(context)
				.setSmallIcon(R.drawable.ic_launcher)
				.setContentTitle(
						context.getResources().getText(R.string.app_name))
				.setContentText(temp);
		mNotificationManager.notify(Constants.notificationID, nBuilder.build());
	}

}
