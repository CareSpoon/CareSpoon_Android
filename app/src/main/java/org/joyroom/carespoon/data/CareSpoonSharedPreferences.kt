package org.joyroom.carespoon.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

object CareSpoonSharedPreferences {
    private lateinit var preferences: SharedPreferences
    private const val PREFERENCES_NAME = "EASY_PEASY_PREFERENCES"
    private const val KEY_ACCESS_TOKEN = "KEY_ACCESS_TOKEN"
    private const val KEY_USER_NAME = "KEY_USER_NAME"
    private const val KEY_EMAIL = "KEY_EMAIL"
    private const val KEY_UUID = "KEY_UUID"


    fun init(context: Context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun set(token: String?){
        preferences.edit {
            if (token == null) remove(KEY_ACCESS_TOKEN)
            else putString(KEY_ACCESS_TOKEN, token)
        }
    }

    fun setUserName(token: String?){
        preferences.edit {
            if (token == null) {
                remove(KEY_USER_NAME)
            } else {
                putString(KEY_USER_NAME, token)
            }
        }
    }

    fun getUserName(): String? = preferences.getString(KEY_USER_NAME, null)

    // 개선 사항 email은 여기서 빼고 viewModel로 처리할 것
    fun setUserEmail(token: String?){
        preferences.edit {
            if (token == null) {
                remove(KEY_EMAIL)
            } else {
                putString(KEY_EMAIL, token)
            }
        }
    }

    fun getUserEmail(): String? = preferences.getString(KEY_EMAIL, null)

    fun setUUID(token: String?){
        preferences.edit {
            if (token == null) {
                remove(KEY_UUID)
            } else {
                putString(KEY_UUID, token)
            }
        }
    }

    fun getUUID(): String? = preferences.getString(KEY_UUID, null)

}