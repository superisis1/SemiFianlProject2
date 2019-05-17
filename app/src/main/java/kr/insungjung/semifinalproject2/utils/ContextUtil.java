package kr.insungjung.semifinalproject2.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class ContextUtil {

    private static final String prefName = "SemiFinalProject2Pref";

    private static final String USER_INPUT_ID = "USER_INPUT_ID";
    private static final String USER_INPUT_PW = "USER_INPUT_PW";
    private static final String USER_TOKEN = "USER_TOKEN";
    private static final String AUTO_LOGIN = "AUTO_LOGIN";

    /* 아이디 */
    public static void setUserInputId(Context context, String inputId) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(USER_INPUT_ID, inputId).apply();
    }

    public static String getUserInputId(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return pref.getString(USER_INPUT_ID, "");
    }

    /* 비번 */
    public static void  setUserInputPw(Context context, String inputPw) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(USER_INPUT_PW, inputPw).apply();
    }

    public  static String getUserInputPw(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return  pref.getString(USER_INPUT_PW, "");
    }

    /* 토큰 */
    public static void setUserToken(Context context, String inputToken) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putString(USER_TOKEN, inputToken).apply();
    }

    public static String getUserToken(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return  pref.getString(USER_TOKEN, "");
    }

    /* 자동로그인 체크여부 */
    public static void setAutoLogin(Context context, boolean autoLogin) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        pref.edit().putBoolean(AUTO_LOGIN, autoLogin).apply();
    }

    public static Boolean getAutoLogin(Context context) {
        SharedPreferences pref = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return  pref.getBoolean(AUTO_LOGIN, false);
    }


}
