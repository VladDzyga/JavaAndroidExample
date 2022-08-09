package com.example.cashmanager.forms;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.library.baseAdapters.BR;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import com.example.cashmanager.R;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2022/05/21
 */
public abstract class AbstractForm<B extends ViewDataBinding, V extends AbstractFormVM> extends Fragment {

	private final int layoutId;
	private B binding;
	private V viewModel;

	public AbstractForm(int layoutId) {
		super();
		this.layoutId = layoutId;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		this.binding = DataBindingUtil.inflate(inflater, this.layoutId, container, false);
		return this.binding.getRoot();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		this.viewModel = new ViewModelProvider(NavHostFragment.findNavController(this).getViewModelStoreOwner(R.id.nav_graph), new ViewModelProvider.Factory() {//NOSONAR нельзя переделать через лямбду (используются generic типы)
			@Override
			public <T extends ViewModel> T create(Class<T> modelClass) {
				return (T) AbstractFormVM.getInstance(AbstractForm.this, getVMClassValue());
			}
		}).get(String.valueOf(hashCode()), getVMClassValue());
		binding.setVariable(BR.vm, viewModel);
		binding.setLifecycleOwner(this);
	}

	private Class<V> getVMClassValue() {
		Class<? extends AbstractForm> aClass = getClass();
		ParameterizedType parameterizedType = (ParameterizedType) aClass.getGenericSuperclass();
		Type type = parameterizedType.getActualTypeArguments()[1];
		return (Class<V>) type;
	}

	public V getViewModel() {
		return viewModel;
	}
}
