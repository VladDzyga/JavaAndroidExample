package com.example.cashmanager.forms;

import android.widget.EditText;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.MutableLiveData;
import com.example.cashmanager.NavigationDispatcher;
import com.example.cashmanager.R;
import com.example.cashmanager.helpers.SecurityHelpers;
import com.example.cashmanager.helpers.SharedPreferencesHelper;
import com.example.cashmanager.helpers.ValidationHelpers;
import com.google.android.material.textfield.TextInputLayout;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2022/05/22
 */
public class FirstStartFormVM extends AbstractFormVM {



	private String newPassword;
	private String confirmPassword;
	private MutableLiveData<String> passwordError = new MutableLiveData<>();
	private MutableLiveData<String> confirmError = new MutableLiveData<>();


	public FirstStartFormVM(AbstractForm form) {
		super(form);
	}

	public void onClick() {
		newPassword = ((EditText) getForm().getView().findViewById(R.id.new_password)).getText().toString();
		confirmPassword = ((EditText) getForm().getView().findViewById(R.id.confirm_password)).getText().toString();
		if (ValidationHelpers.validateText(newPassword)) {
			setPasswordError(null);
			if (ValidationHelpers.validateText(confirmPassword)) {
				setConfirmError(null);
				if (newPassword.equals(confirmPassword)) {
					try {
						SharedPreferencesHelper.setPassword(SecurityHelpers.encrypt(newPassword));
					} catch (Exception e) {
						e.printStackTrace();
					}
					NavigationDispatcher.next(R.id.mainForm);
				} else {
					setConfirmError("Пароль не вірний");
				}
			} else {
				setConfirmError("Введіть пароль повторно");
			}
		} else {
			setPasswordError("Введіть пароль");
			if (ValidationHelpers.validateText(confirmPassword)) {
				setConfirmError(null);
			} else {
				setConfirmError("Введіть пароль повторно");
			}
		}

	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirm_password) {
		this.confirmPassword = confirm_password;
	}

	public MutableLiveData<String> getPasswordError() {
		return passwordError;
	}

	public void setPasswordError(String message) {
		passwordError.postValue(message);
	}

	public MutableLiveData<String> getConfirmError() {
		return confirmError;
	}

	public void setConfirmError(String message) {
		confirmError.postValue(message);
	}

}
