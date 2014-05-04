package Misc;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;

public class UserPreferences {

	private static UserPreferences userPreferences = null;

	Location location;
	Geocoder geoCoder;
	LocationManager locationManager;

	public UserPreferences(Context context) {
		locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		location = locationManager
				.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		geoCoder = new Geocoder(context, Locale.getDefault());

	}

	public static UserPreferences getUserPreferences() {
		return userPreferences;
	}

	public static UserPreferences getInstance(Context context) {
		if (userPreferences == null)
			userPreferences = new UserPreferences(context);
		return userPreferences;
	}

	public double getLongtitude() {
		if (location == null)
			return 0;
		return location.getLongitude();
	}

	public double getLatitude() {
		if (location == null)
			return 0;
		return location.getLatitude();
	}

	public String getCity() {
		try {
			List<Address> adresses = geoCoder.getFromLocation(getLatitude(),
					getLongtitude(), 1);
			if (adresses.size() > 0)
				return adresses.get(0).getLocality();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Skopje";
	}
}
