package com.shasthosheba.doctor.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.shasthosheba.doctor.model.User;


public class PreferenceManager {
    private static final String PREFERENCE_STORAGE = "shasthosheba_pref_storage";

    public static enum PreferenceKey {
        USER("user"),
        INTERMEDIARY("intermediary"),
        CONNECTED("is_connected");

        private final String key;

        PreferenceKey(String key) {
            this.key = key;
        }

        public String getKey() {
            return key;
        }
    }

    private Context context;
    private SharedPreferences preferences;
    private Gson mGson = new Gson();

    public PreferenceManager(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(PREFERENCE_STORAGE, Context.MODE_PRIVATE);
    }

    public void setUser(User user) {
        preferences.edit().putString(PreferenceKey.USER.key, mGson.toJson(user)).apply();
    }

    public User getUser() {
        return mGson.fromJson(preferences.getString(PreferenceKey.USER.key, ""), User.class);
    }

    public void setConnected(boolean connected) {
        preferences.edit().putBoolean(PreferenceKey.CONNECTED.key, connected).apply();
    }

    public boolean isConnected() {
        return preferences.getBoolean(PreferenceKey.CONNECTED.key, false);
    }
}
