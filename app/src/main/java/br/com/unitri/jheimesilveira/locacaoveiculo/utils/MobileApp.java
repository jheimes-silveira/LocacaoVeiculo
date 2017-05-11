package br.com.unitri.jheimesilveira.locacaoveiculo.utils;

import android.app.Application;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import br.com.unitri.jheimesilveira.locacaoveiculo.R;

public class MobileApp extends Application {

	public static final String VERSION = "1.0";

	private static MobileApp instance;

	public MobileApp() {
		instance = this;
	}

	public static MobileApp getApplication() {
		return instance;
	}

	@Override
	public void onCreate() {
		System.out.println(">>>>>>>>>>>>>>> CRIANDO APLICACAO <<<<<<<<<<<<<<<<<<");
		super.onCreate();
	}

	@Override
	public void onTerminate() {
		super.onTerminate();
		System.out.println("ON TERMINATE");
	}

	public static void setFragmentReplacePage(FragmentManager supportFragmentManager, Fragment fgt) {
		FragmentManager fm = supportFragmentManager;
		FragmentTransaction ft = fm.beginTransaction();
		ft.setCustomAnimations(R.anim.fab_scale_up, R.anim.fab_scale_down);
		ft.replace(R.id.fragment_fgt_main, fgt).commit();
	}

	public static void setFragmentReplacePage(FragmentManager supportFragmentManager, Fragment fgt, int referenceFrameLayout) {
		FragmentManager fm = supportFragmentManager;
		FragmentTransaction ft = fm.beginTransaction();
		ft.setCustomAnimations(R.anim.fab_scale_up, R.anim.fab_scale_down);
		ft.replace(referenceFrameLayout, fgt).commit();
	}

}
