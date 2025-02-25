package com.gwabs.testproject.Utils

import android.content.Context

class AppPreference {

    companion object{
        const val PREF_NAME = "APP"
        const val REFRESH_TOKEN = "refresh_token"
        const val ACCESS_TOKEN = "access_token"
    }

        fun setAccessToken(context: Context, token : String) {
            val sharedPreferences =
                context.getSharedPreferences(PREF_NAME, 0)
            val editor = sharedPreferences.edit()
            editor.putString(ACCESS_TOKEN, token)
            editor.apply()
            editor.commit()
        }


    fun getAccessToken(context : Context) : String? {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, 0)
        return sharedPreferences.getString(ACCESS_TOKEN, "")
    }

    fun setReffreshToken(context: Context, token : String){
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, 0)
        val preferenceEditor = sharedPreferences.edit()
        preferenceEditor.putString(REFRESH_TOKEN, token)
        preferenceEditor.apply()
    }



}