package com.shaunhossain.imcomingdear.data.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

/**
 * Created by adriaboschsaez on 11/02/2018.
 */

public class Utils {

    public static float convertDpToPixel(float dp) {

        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float px = dp * metrics.density;
        return px;
    }


    public static float convertPixelsToDp(float px) {

        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        float dp = px / metrics.density;
        return dp;
    }

    public static boolean isValidEmail(String email) {

        //I dont' know how to mock this
        //return Patterns.EMAIL_ADDRESS.matcher(email).matches();

        return email.matches("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+");
    }

    public static boolean isValidPassword(String password) {

        if (password.length() < 6) {
            System.out.println("pass too short or too long");
            return false;
        }

        if (!password.matches(".*//d.*")) {
            System.out.println("no digits found");
            return false;
        }

        if (!password.matches(".*[a-z].*")) {
            System.out.println("no lowercase letters found");
            return false;
        }
        if (!password.matches(".*[!@#$%^&*+=?-].*")) {
            System.out.println("no special chars found");
            return false;
        }

        return true;
    }
}
