package com.darmstadt.diamonds_android.global.util;

import android.content.Context;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.widget.Toast;
import java.lang.reflect.Field;

public class UiUtil {

    public static void removeShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode");
        }
    }

    /**
     * Consider: https://stackoverflow.com/questions/7074713/should-a-utility-class-be-static
     * Pure utility classes should usually be static. When you have a class with well-defined input and output, no
     * side effects and no state, then by definition it should be a static class.
     * In general, don't add complexity (dependency injection in this case) before it's necessary and there's a
     * benefit in doing it.
     */

    public static void showToast(final Context context, final int message, final int duration) {
        // TODO: Find out reason for sporadic layoutinflation error
        try {
            Toast toast = Toast.makeText(context, message, duration);
            toast.show();
        } catch (Exception ignore) {}
    }

}
