package com.example.cashmanager.helpers;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.cashmanager.Record;
import com.google.gson.Gson;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2022/05/21
 */
public class SharedPreferencesHelper {

	private static Context context;
	private static Set<String> records;

	private SharedPreferencesHelper() {}

	public static void setContext(Context context) {
		SharedPreferencesHelper.context = context;
	}

	public static ArrayList<Record> getAllRecords() {
		ArrayList<Record> records = new ArrayList<>();
		Set<String> recordsString = getRecordsSet();
		for (String recordString : recordsString) {
			Gson gson = new Gson();
			Record record = gson.fromJson(recordString, Record.class);
			records.add(record);
		}
		return sort(records);
	}

	public static ArrayList<Record> sort(ArrayList<Record> records) {
		Record buf;
		boolean isSorted = false;
		while(!isSorted) {
			isSorted = true;
			for (int i = 0; i < records.size()-1; i++) {
				try {
					long date1 = new SimpleDateFormat("yyyy.MM.dd HH:mm").parse(records.get(i).getDate()).getTime();
					long date2 = new SimpleDateFormat("yyyy.MM.dd HH:mm").parse(records.get(i+1).getDate()).getTime();
					if(date1 < date2){
						isSorted = false;
						buf = records.get(i);
						records.set(i, records.get(i+1));
						records.set(i+1, buf);
					}
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
		return records;
	}

	public static Set<String> getRecordsSet() {
		return context.getSharedPreferences("Global", MODE_PRIVATE).getStringSet("Records", new HashSet<>());
	}

	public static void addRecord(Record record) {
		Set<String> recordsString = getRecordsSet();
		SharedPreferences sharedPreferences = context.getSharedPreferences("Global", MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		Gson gson = new Gson();
		String recordString = gson.toJson(record);
		recordsString.add(recordString);
		editor.putStringSet("Records", recordsString);
		editor.apply();
	}

	public static String getBalance() {
		return context.getSharedPreferences("Global", MODE_PRIVATE).getString("Balance", "0");
	}

	public static void setBalance(String value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences("Global", MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString("Balance", value);
		editor.apply();
	}

	public static void setPassword(String value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences("Global", MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPreferences.edit();
		editor.putString("Password", value);
		editor.apply();
	}

	public static boolean checkPassword(String value) throws Exception {
		return SecurityHelpers.decrypt(context.getSharedPreferences("Global", MODE_PRIVATE).getString("Password", "")).equals(value);
	}

	public static boolean isPasswordSet() {
		return !context.getSharedPreferences("Global", MODE_PRIVATE).getString("Password", "").isEmpty();
	}

	public static String getPassword() {
		return context.getSharedPreferences("Global", MODE_PRIVATE).getString("Password", "");
	}
}
