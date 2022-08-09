package com.example.cashmanager.forms;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import com.example.cashmanager.NavigationDispatcher;
import com.example.cashmanager.R;
import com.example.cashmanager.Record;
import com.example.cashmanager.helpers.SharedPreferencesHelper;
import com.example.cashmanager.helpers.ValidationHelpers;
import com.google.android.material.textfield.TextInputLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2022/05/22
 */
public class NewRecordFormVM extends AbstractFormVM {

	private boolean increase = true;
	private String name;
	private String balanceChange;
	private String balanceResult;
	private String description;
	private MutableLiveData<String> nameError = new MutableLiveData<>();
	private MutableLiveData<String> valueError = new MutableLiveData<>();

	public NewRecordFormVM(AbstractForm form) {
		super(form);
		balanceResult = SharedPreferencesHelper.getBalance();
		((EditText) getForm().getView().findViewById(R.id.value_change_edit)).addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				checkBalance();
			}

			@Override
			public void afterTextChanged(Editable editable) {

			}
		});
		((EditText) getForm().getView().findViewById(R.id.name_edit)).addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

			}

			@Override
			public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
				checkName();
			}

			@Override
			public void afterTextChanged(Editable editable) {

			}
		});
	}

	public void onBackClick() {
		NavigationDispatcher.back();
	}

	public void checkBalance() {
		((TextView) getForm().getView().findViewById(R.id.value_result)).setText("Баланс: " + SharedPreferencesHelper.getBalance() + "₴");
		balanceChange = ((EditText) getForm().getView().findViewById(R.id.value_change_edit)).getText().toString();
		if (ValidationHelpers.validateText(balanceChange)) {
			if (ValidationHelpers.validateNumber(balanceChange)) {
				if (increase) {
					balanceResult = String.valueOf(Integer.parseInt(SharedPreferencesHelper.getBalance()) + Integer.parseInt(balanceChange));
				} else {
					balanceResult = String.valueOf(Integer.parseInt(SharedPreferencesHelper.getBalance()) - Integer.parseInt(balanceChange));
				}
				((TextView) getForm().getView().findViewById(R.id.value_result)).setText("Баланс: " + balanceResult + "₴");
				setValueError(null);
			} else {
				setValueError("Введіть число");
			}
		}
	}

	public void checkName() {
		name = ((EditText) getForm().getView().findViewById(R.id.name_edit)).getText().toString();
		if (ValidationHelpers.validateText(name)) {
			setNameError(null);
		}
	}

	public void onAddClick() {
		name = ((EditText) getForm().getView().findViewById(R.id.name_edit)).getText().toString();
		balanceChange = ((EditText) getForm().getView().findViewById(R.id.value_change_edit)).getText().toString();
		if (ValidationHelpers.validateText(name)) {
			if (ValidationHelpers.validateText(balanceChange) && ValidationHelpers.validateNumber(balanceChange)) {
				String timeStamp = new SimpleDateFormat("yyyy.MM.dd HH:mm").format(new Date());
				SharedPreferencesHelper.addRecord(new Record(
						name,
						timeStamp,
						(increase ? "+" : "-") + balanceChange,
						balanceResult,
						((EditText) getForm().getView().findViewById(R.id.description_edit)).getText().toString()));
				SharedPreferencesHelper.setBalance(balanceResult);
				NavigationDispatcher.back();
			} else {
				setValueError("Введіть число");
			}
		} else {
			setNameError("Введіть назву");
			if (!ValidationHelpers.validateText(balanceChange)) {
				setValueError("Введіть число");
			}
		}
	}



	public void onRadioClick() {
		int radioId = ((RadioGroup) getForm().getView().findViewById(R.id.radio_buttons)).getCheckedRadioButtonId();
		if(radioId == R.id.increase) {
			increase = true;
		} else {
			increase = false;
		}
		checkBalance();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBalanceChange() {
		return balanceChange;
	}

	public void setBalanceChange(String balanceChange) {
		this.balanceChange = balanceChange;
	}

	public String getBalanceResult() {
		return "Баланс: " + balanceResult + "₴";
	}

	public void setBalanceResult(String balanceResult) {
		this.balanceResult = balanceResult;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MutableLiveData<String> getNameError() {
		return nameError;
	}

	public void setNameError(String message) {
		nameError.postValue(message);
	}

	public MutableLiveData<String> getValueError() {
		return valueError;
	}

	public void setValueError(String message) {
		valueError.postValue(message);
	}

}
