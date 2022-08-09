package com.example.cashmanager;

import android.app.Activity;
import android.os.Bundle;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

/**
 * @author Vladyslav Dzyhovskyi
 * @company UnitedThinkers
 * @since 2022/05/21
 */
public class NavigationDispatcher {

	private static NavController navController;
	private static int currentLayout;
	private static int lastLayout;

	private NavigationDispatcher() {}

	public static void init(Activity activity) {
		currentLayout = R.id.loginForm;
		navController = Navigation.findNavController(activity, R.id.nav_host_form);
	}

	public static void next(int idRes) {
		lastLayout = currentLayout;
		currentLayout = idRes;
		navController.navigate(idRes);
	}

	public static void next(int idRes, Bundle bundle) {
		lastLayout = currentLayout;
		currentLayout = idRes;
		navController.navigate(idRes, bundle);
	}

	public static void back() {
		currentLayout = lastLayout;
		navController.popBackStack();
	}

	public static int getCurrentLayout() {
		return currentLayout;
	}
}
