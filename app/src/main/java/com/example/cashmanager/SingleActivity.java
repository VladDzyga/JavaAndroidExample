package com.example.cashmanager;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.example.cashmanager.helpers.SharedPreferencesHelper;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2022/05/21
 */
public class SingleActivity extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.single_activity);
		NavigationDispatcher.init(this);
		SharedPreferencesHelper.setContext(this);
		if (!SharedPreferencesHelper.isPasswordSet()) {
			NavigationDispatcher.next(R.id.firstStartForm);
		}
	}

	@Override
	public void onBackPressed() {
		if (NavigationDispatcher.getCurrentLayout() == R.id.loginForm
				|| NavigationDispatcher.getCurrentLayout() == R.id.firstStartForm
				|| NavigationDispatcher.getCurrentLayout() == R.id.mainForm) {
			finish();
		} else {
			super.onBackPressed();
		}
	}
}
