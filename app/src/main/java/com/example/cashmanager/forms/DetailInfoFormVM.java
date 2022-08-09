package com.example.cashmanager.forms;

import android.widget.TextView;
import com.example.cashmanager.NavigationDispatcher;
import com.example.cashmanager.R;
import com.example.cashmanager.Record;
import com.google.gson.Gson;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2022/05/22
 */
public class DetailInfoFormVM extends AbstractFormVM {

	public DetailInfoFormVM(AbstractForm form) {
		super(form);
		String recordString = getForm().getArguments().getString("record");
		Gson gson = new Gson();
		Record record = gson.fromJson(recordString, Record.class);
		((TextView) getForm().getView().findViewById(R.id.name)).setText("Назва: " + record.getName());
		((TextView) getForm().getView().findViewById(R.id.date)).setText("Дата: " +record.getDate());
		((TextView) getForm().getView().findViewById(R.id.value_change)).setText(record.getBalanceChange().contains("+") ? "Прибуток: " + record.getBalanceChange() + "₴" : "Витрати: " + record.getBalanceChange() + "₴");
		((TextView) getForm().getView().findViewById(R.id.value_result)).setText("Баланс: " + record.getBalanceResult() + "₴");
		((TextView) getForm().getView().findViewById(R.id.description)).setText("Опис: " +record.getDescription());
	}

	public void onBackClick() {
		NavigationDispatcher.back();
	}
}
