package com.darmstadt.diamonds_android.global.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import com.darmstadt.diamonds_android.R;

public class FragUtil {

    public static void popBackStack(final FragmentManager fragmentManager) {
        fragmentManager.popBackStack();//        transaction.setCustomAnimations(FragmentTransaction.TRANSIT_FRAGMENT_OPEN, FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
    }

    public static void replaceFragment(final FragmentManager fragmentManager, final Fragment fragment, final String tag, final int enterAnimation, final int exitAnimation) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(enterAnimation, exitAnimation);
        transaction.replace(R.id.fragment_container, fragment, tag).addToBackStack(tag);
        transaction.commit();
    }

    public static void replaceFragment(final FragmentManager fragmentManager, final Fragment fragment, final String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment, tag).addToBackStack(tag);
        transaction.commit();
    }

    public static void replaceFragmentSharedElementTransition(final FragmentManager fragmentManager, final Fragment fragment, final String tag, final View sharedElement, final String transitionName) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.addSharedElement(sharedElement, transitionName);
//        transaction.setCustomAnimations(enterAnimation, exitAnimation);
        transaction.replace(R.id.fragment_container, fragment, tag).addToBackStack(tag);
        transaction.commit();
    }

    public static void replaceFragmentNoBackStack(final FragmentManager fragmentManager, final Fragment fragment, final String tag) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
//        transaction.setCustomAnimations(FragmentTransaction.TRANSIT_FRAGMENT_OPEN, FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
        transaction.replace(R.id.fragment_container, fragment, tag);
        transaction.commit();
    }

}
