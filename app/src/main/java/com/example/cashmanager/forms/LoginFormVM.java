package com.example.cashmanager.forms;

import android.widget.EditText;
import androidx.lifecycle.MutableLiveData;
import com.example.cashmanager.NavigationDispatcher;
import com.example.cashmanager.R;
import com.example.cashmanager.helpers.SharedPreferencesHelper;
import com.example.cashmanager.helpers.ValidationHelpers;
import com.google.android.material.textfield.TextInputEditText;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2022/05/22
 */
public class LoginFormVM extends AbstractFormVM {

	private String password;
	private MutableLiveData<String> passwordError = new MutableLiveData<>();

	public LoginFormVM(AbstractForm form) {
		super(form);
	}

	public void onClick() {
		password = ((EditText) getForm().getView().findViewById(R.id.password)).getText().toString();
		if (ValidationHelpers.validateText(password)) {
			try {
				if (SharedPreferencesHelper.checkPassword(password)) {
					NavigationDispatcher.next(R.id.mainForm);
				} else {
					setPasswordError("Пароль не вірний");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			setPasswordError("Введіть пароль");
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public MutableLiveData<String> getPasswordError() {
		return passwordError;
	}

	public void setPasswordError(String message) {
		passwordError.postValue(message);
	}
}
