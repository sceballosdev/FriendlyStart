package com.sceballosdev.friendly_start.preferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;

public class PreferencesManager {
    // variables de control
    private static final String PREF_NAME = "FriendlyChatPref";
    private static final String IS_LOGIN = "isLoggedIn";
    private static final String USER = "user";
    private static final String PASSWORD = "password";
    private static final String TOKEN_SESSION = "idUserTokenSession";

    private SharedPreferences mShared;
    private SharedPreferences.Editor mEditor;

    // Inicializamos nuestras variables de control
    public PreferencesManager(Context context) {
        mShared = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        mEditor = mShared.edit();
        mEditor.apply();
    }

    // Método que crea una sesión cada que un usuario se loguea correctamente
    public void createLoginSession(String user, String password, String token) {

        mEditor.putBoolean(IS_LOGIN, true);
        mEditor.putString(USER, user);
        mEditor.putString(PASSWORD, password);
        mEditor.putString(TOKEN_SESSION, token);
        mEditor.apply();
    }

    // Método para validar si el usuario tiene una sesión activa
    public boolean checkLogin() {
        return mShared.getBoolean(IS_LOGIN, false);
    }

    public String[] sessionData() {
        String[] data = {
                mShared.getString(USER, ""),
                mShared.getString(PASSWORD, ""),
        };
        return data;
    }

    public String getToken() {
        return mShared.getString(TOKEN_SESSION,"null");
    }

    public void setToken(String token){
        mEditor.putString(TOKEN_SESSION, token);
        mEditor.apply();
    }

    // Método para destruir la sesión
    public void logout() {
        mEditor.putBoolean(IS_LOGIN, false);
        mEditor.putString(USER, "");
        mEditor.putString(PASSWORD, "");
        mEditor.apply();
    }

    // Método para cambiar de actividad y poder destruir la anterior
    public void changeActivity(AppCompatActivity context, Class secondAct) {
        Intent intent = new Intent(context, secondAct);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
        context.finish();
    }
}
