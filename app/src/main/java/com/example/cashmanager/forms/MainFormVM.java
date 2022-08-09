package com.example.cashmanager.forms;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.cashmanager.NavigationDispatcher;
import com.example.cashmanager.R;
import com.example.cashmanager.RecordListAdapter;
import com.example.cashmanager.helpers.SharedPreferencesHelper;
import com.google.gson.Gson;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2022/05/22
 */
public class MainFormVM extends AbstractFormVM {

	public MainFormVM(AbstractForm form) {
		super(form);
	}

	public void onClick() { }

	public void onAddClick() {
		NavigationDispatcher.next(R.id.newRecordForm);
	}
}
