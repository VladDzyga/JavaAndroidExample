package com.example.cashmanager.forms;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import com.example.cashmanager.NavigationDispatcher;
import com.example.cashmanager.R;
import com.example.cashmanager.RecordListAdapter;
import com.example.cashmanager.databinding.MainFormBinding;
import com.example.cashmanager.helpers.SharedPreferencesHelper;
import com.google.gson.Gson;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2022/05/22
 */
public class MainForm extends AbstractForm<MainFormBinding, MainFormVM> {

	public MainForm() {
		super(R.layout.main_form);
	}

	@Override
	public void onResume() {
		ListView listView = getView().findViewById(R.id.listView);
		listView.setAdapter(new RecordListAdapter(getContext(), R.layout.list_adapter, SharedPreferencesHelper.getAllRecords()));
		listView.setOnItemClickListener((adapterView, view, i, l) -> {
			Gson gson = new Gson();
			String recordString = gson.toJson(SharedPreferencesHelper.getAllRecords().get(i));
			Bundle bundle = new Bundle();
			bundle.putString("record", recordString);
			NavigationDispatcher.next(R.id.detailInfoForm, bundle);
		});
		((TextView) getView().findViewById(R.id.balance)).setText("Баланс: " + SharedPreferencesHelper.getBalance() + "₴");
		super.onResume();
	}
}
