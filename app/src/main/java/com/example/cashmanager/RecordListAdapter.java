package com.example.cashmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.Set;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2022/05/22
 */
public class RecordListAdapter extends ArrayAdapter<Record> {

	private Context context;
	private int itemLayout;
	private ArrayList<Record> records;

	public RecordListAdapter(Context context, int itemLayout, ArrayList<Record> records) {
		super(context, itemLayout, records);
		this.context = context;
		this.itemLayout = itemLayout;
		this.records = records;
	}

	@NonNull
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		 String name = getItem(position).getName();
		 String date = getItem(position).getDate();;
		 String balanceChange = getItem(position).getBalanceChange();
		 String balanceResult = getItem(position).getBalanceResult();
		 String description = getItem(position).getDescription();

		LayoutInflater inflater = LayoutInflater.from(context);
		convertView = inflater.inflate(itemLayout, parent, false);

		((TextView) convertView.findViewById(R.id.record_name)).setText(name);
		((TextView) convertView.findViewById(R.id.date)).setText(date);
		((TextView) convertView.findViewById(R.id.value_result)).setText("Баланс: " + balanceResult + "₴");
		((TextView) convertView.findViewById(R.id.value_change)).setText("(" + balanceChange + "₴)");
		((TextView) convertView.findViewById(R.id.description)).setText(description);


		return convertView;
	}
}
