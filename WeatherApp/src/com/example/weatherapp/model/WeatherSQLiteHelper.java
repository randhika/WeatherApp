package com.example.weatherapp.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class WeatherSQLiteHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "weatherdatabse.db";
	public static final int DATABASE_VERSION = 1;

	public static final String TABLE_WEATHER = "weatherTable";
	// Table columns
	public static final String WEATHER_ID = "_id";
	public static final String WEATHER_DATE = "date";
	public static final String WEATHER_TEMP = "temperature";
	public static final String WEATHER_IconName = "iconName";
	public static final String WEATHER_SUMMARY = "summary";
	public static final String WEATHER_minMaxTemp = "minMaxTemperature";
	public static final String WEATHER_Sunrise = "SunriseTime";
	public static final String WEATHER_Sunset = "SunsetTime";
	public static final String WEATHER_Moon = "Moonphase";
	public static final String WEATHER_Wind = "WindSpeed";
	public static final String WEATHER_Cloud = "CloudCover";
	public static final String WEATHER_HUMINDITY = "Humindity";
	public static final String WEATHER_Visibility = "Visibility";
	public static final String WEATHER_City = "City";

	public static String DATABSE_CREATE_QUERY;
	static {
		DATABSE_CREATE_QUERY = createWeatherTable();
	}

	public WeatherSQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABSE_CREATE_QUERY);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXIST " + TABLE_WEATHER);
		onCreate(db);
	}

	private static String createWeatherTable() {
		StringBuilder sb = new StringBuilder();
		sb.append("create table ");
		sb.append(TABLE_WEATHER);
		sb.append(" (");
		sb.append(WEATHER_ID);
		sb.append(" integer primary key, ");
		sb.append(WEATHER_DATE);
		sb.append(" TEXT, ");
		sb.append(WEATHER_TEMP);
		sb.append(" TEXT, ");
		sb.append(WEATHER_IconName);
		sb.append(" TEXT,");
		sb.append(WEATHER_SUMMARY);
		sb.append(" TEXT,");
		sb.append(WEATHER_minMaxTemp);
		sb.append(" TEXT,");
		sb.append(WEATHER_Sunrise);
		sb.append(" TEXT,");
		sb.append(WEATHER_Sunset);
		sb.append(" TEXT,");
		sb.append(WEATHER_Moon);
		sb.append(" TEXT,");
		sb.append(WEATHER_Wind);
		sb.append(" TEXT,");
		sb.append(WEATHER_Cloud);
		sb.append(" TEXT,");
		sb.append(WEATHER_HUMINDITY);
		sb.append(" TEXT,");
		sb.append(WEATHER_Visibility);
		sb.append(" TEXT,");
		sb.append(WEATHER_City);
		sb.append(" TEXT");
		sb.append(");");
		return sb.toString();
	}

}
