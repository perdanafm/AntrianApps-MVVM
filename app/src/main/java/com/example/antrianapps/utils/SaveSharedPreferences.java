package com.example.antrianapps.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.example.antrianapps.utils.PreferencesUtils.*;

public class SaveSharedPreferences {
    static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    /**
     * Set the Current Antrian
     *
     * @param context
     * @param noAntrian
     */
    public static void setCurrentAntrian(Context context, int noAntrian) {
        SharedPreferences.Editor editor = getPreferences(context).edit();
        editor.putInt(CURRENT_ANTRIAN, noAntrian);
        editor.apply();
    }
    /**
     * Get the Current Antrian
     *
     * @param context
     * @return int: no antrian
     */
    public static int getCurrentAntrian(Context context) {
        return getPreferences(context).getInt(CURRENT_ANTRIAN, 0);
    }

}
