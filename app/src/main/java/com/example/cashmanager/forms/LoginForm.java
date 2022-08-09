package com.example.cashmanager.forms;

import com.example.cashmanager.NavigationDispatcher;
import com.example.cashmanager.R;
import com.example.cashmanager.databinding.LoginFormBinding;
import com.example.cashmanager.helpers.SharedPreferencesHelper;
import com.example.cashmanager.helpers.ValidationHelpers;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2022/05/22
 */
public class LoginForm extends AbstractForm<LoginFormBinding, LoginFormVM> {

	public LoginForm() {
		super(R.layout.login_form);
	}

	public void checkPassword(String password) {
		if (ValidationHelpers.validateText(password)) {
			try {
				if (SharedPreferencesHelper.checkPassword(password)) {
					NavigationDispatcher.next(R.id.mainForm);
				} else {
					getViewModel().setPasswordError("Пароль не вірний");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			getViewModel().setPasswordError("Введіть пароль");
		}
	}

}
