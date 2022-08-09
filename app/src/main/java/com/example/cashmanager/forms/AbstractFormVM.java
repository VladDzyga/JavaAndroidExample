package com.example.cashmanager.forms;

import android.view.View;
import android.widget.EditText;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import com.google.android.material.textfield.TextInputLayout;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2022/05/21
 */
public class AbstractFormVM extends AndroidViewModel {

	private AbstractForm form;

	public AbstractFormVM(AbstractForm form) {
		super(form.getActivity().getApplication());
		this.form = form;
	}

	public static AbstractFormVM getInstance(AbstractForm form, Class clazz) {
		try {
			Constructor constructor = clazz.getConstructor(AbstractForm.class);
			return (AbstractFormVM) constructor.newInstance(form);
		} catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}
	}

	@BindingAdapter("error")
	public static void setError(TextInputLayout layout, String errorMessage) {
		layout.setError(errorMessage);
	}

	public AbstractForm getForm() {
		return form;
	}
}
